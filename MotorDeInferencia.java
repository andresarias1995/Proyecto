
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Date;
import java.text.DateFormat;

//******************************************************************************
// Clase que permite hacer el proceso de inferencia (llegar a un diagnostico) de
// reglas dependiendo de la clasificación de estas, tipo A, b, C, D

public class MotorDeInferencia extends HttpServlet
{

   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ServletContext context;

//******************************************************************************
//Permite abrir la conexion haciendo uso del conector para mysql

   public void init(ServletConfig config) throws ServletException
   {
   		context = config.getServletContext();

      	try
      	{
         	Class.forName("org.gjt.mm.mysql.Driver").newInstance();
         	conn = DriverManager.getConnection(url,login,password);
         	conn.setAutoCommit(false);
        	// System.out.println("entro");
      	}
      	catch(Exception ex)
      	{ 
         	context.log(ex.toString());
      	}
   }

//******************************************************************************
// Permite obtener el tipo de regla que se esta evaluando,el nombre del paciente
// sobre el que se esta haciendo la consulta y su correspondiente identificador 
// en la base de datos.     

   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		PrintWriter salida=null;		
   		ResultSet rs;
   		String info="", pagina="", codigo="", tipo="", cont="", pacNombre="";
   		
   		tipo = resp1.getParameter("tipo"); 
   		cont = resp1.getParameter("cont");

//*****************************************************************************   		
// Hace la inferencia correspondiente a las reglas de tipo A
// Significacion del examen fisico
   		try
	   	{	
	   		boolean hayDiagnostico = false;
	   		Date fec = new Date();
		    String fecha = DateFormat.getDateTimeInstance().format(fec);
		    
		    codigo = resp1.getParameter("codigo"); 
		    
		    String nombrePac = "SELECT pac_nombre FROM paciente where pac_codigo='"+codigo+"'";
		    Statement stat = conn.createStatement();
		    ResultSet resp = stat.executeQuery(nombrePac);
		    resp.next();
		    pacNombre = resp.getString(1);
		    
	   		if ((tipo.equals("a")) || (tipo.equals("d")))
	   		{
	   			codigo = resp1.getParameter("codigo");   	
	   		
		   		info= "SELECT enfermedad.enf_nombre, enfermedad.enf_codigo ";
				info= info + "FROM enfermedad, historia, valorSintoma, regla ";
				info= info + "WHERE historia.pac_codigo="+codigo+" ";
				info= info + "AND historia.his_codigo="+cont+" ";
				info= info + "AND valorSintoma.sin_codigo=historia.sin_codigo ";
				info= info + "AND valorSintoma.val_codigo=historia.val_codigo ";
				info= info + "AND valorSintoma.reg_codigo=regla.reg_codigo ";
				info= info + "AND valorSintoma.reg_codigo > 1 ";
				info= info + "AND regla.enf_codigo=enfermedad.enf_codigo ";	
		   		   		
		   		Statement statement = conn.createStatement();
		        rs= statement.executeQuery(info);
		        	
//******************************************************************************
// Muestra al usuario el diagnostico obtenido por el motor de inferencia
		           	
		        pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
		        if (tipo.equals("a"))
					pagina+= "<br><strong><div align='top'>DIAGNÓSTICO PARA EL EXAMEN FISICO</div></strong>";
				else
					pagina+= "<br><strong><div align='top'>DIAGNÓSTICO PARA EL HEMOLEUCOGRAMA</div></strong>";
				pagina+= "<br>";	        	      		      		
		    	pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/labrador1.jpg' width='152' height='102'></td>";	  			
		    	pagina+= "<br>";	
		    	pagina+= "<br><strong>El paciente "+pacNombre+" con código "+codigo+" presenta el siguiente cuadro clinico:  </strong>";
		    	pagina+= "<br>";	   
		  		pagina+= "<tr>";
		    	pagina+= "<td height='97'>&nbsp;</td>";
		  		pagina+= "</tr>";				
				pagina+= "</body>";
										        	
				info = "UPDATE diagnostico SET dia_estado='A' WHERE dia_tipo='"+tipo+"' AND pac_codigo='"+codigo+"' ";
	  			statement.executeUpdate(info);
	  			
		        while(rs.next())
				{	
					hayDiagnostico = true;			
					pagina+=" "+rs.getString(1)+";";					
					
					String inf = "INSERT INTO diagnostico VALUES ";		      		
	        		inf += "(0,"+rs.getString(2)+","+codigo+",NOW(),NOW(),'"+tipo+"','U')";
	        		int i = statement.executeUpdate(inf);
			    }
			    pagina+= "<br>"+fecha;		    
			    
			    if (!hayDiagnostico)
			    	pagina += "<br>No hay enfermedad presente";
			    	        		    	
			    pagina+= "<form name='form1' method='post' action='/SEDEGIC/servlet/ServletFormularioDinamico'>";   //Consultas.htm'>";			    	
			    if (tipo.equals("d"))
			    {
			    	pagina+= "<input type='hidden' name='campo1' value='c'>";
			    	info = "UPDATE diagnostico SET dia_estado='A' WHERE dia_tipo='c' AND pac_codigo='"+codigo+"' ";
	  				statement.executeUpdate(info);       
			    }	  			
			    else
			    {
			    	pagina+= "<input type='hidden' name='campo1' value='b'>";        
			    	info = "UPDATE diagnostico SET dia_estado='A' WHERE dia_tipo='d' AND pac_codigo='"+codigo+"' ";
	  				statement.executeUpdate(info);       
			    }
			    pagina+= "<input type='hidden' name='campo2' value='"+codigo+"'>";		        
			    pagina+= "<input type='submit' name='submit' value='Aceptar'>";
			    pagina+= "</form>"; 
			    
			    if (tipo.equals("d"))
			    {
			    	pagina+= "<form name='form2' method='post' action='/SEDEGIC/servlet/DiagnosticoFinal'>";
	        		pagina+= "<input type='hidden' name='campo1' value='"+codigo+"'>";
	        		pagina+= "<input type='submit' name='Submit' value='Diagnóstico final'>";
	        		pagina+= "</form></td>";     
	        	}
			}          
	   		
//*****************************************************************************   		
// Hace la inferencia correspondiente a las reglas de tipo B 
// Diarrea de intestino grueso o Diarrea de intestino delagado
	   		
	   		if(tipo.equals("b"))
	   		{	   		
		   		info= "SELECT valorSintoma.reg_codigo, enfermedad.enf_nombre, enfermedad.enf_codigo, count(*) as campo, regla.reg_gradoCerteza ";
				info= info + "FROM historia, valorSintoma, enfermedad, regla ";
				info= info + "WHERE historia.pac_codigo="+codigo+" ";
				info= info + "AND historia.his_codigo="+cont+" ";
				info= info + "AND valorSintoma.sin_codigo=historia.sin_codigo ";
				info= info + "AND valorSintoma.val_codigo=historia.val_codigo ";
				info= info + "AND valorSintoma.reg_codigo=regla.reg_codigo ";
				info= info + "AND regla.enf_codigo=enfermedad.enf_codigo ";	
				info= info + "AND regla.reg_codigo > 1 ";
				info= info + "GROUP BY valorSintoma.reg_codigo ";
				info= info + "ORDER BY campo DESC";
		   				   			
		   		Statement statement = conn.createStatement();
		        rs= statement.executeQuery(info);
		        	          	
		        if(rs.next())
				{
					float certeza = ((Integer.parseInt(rs.getString(4))*100)/(Integer.parseInt(rs.getString(5))));
					
					pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
					pagina+= "<br><strong><div align='top'>DIAGNÓSTICO DE DIARREAS</div></strong>";
					pagina+= "<br>";			
			    	pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/labrador1.jpg' width='152' height='102'></td>";	  			
			    	pagina+= "<br>";	
			    	pagina+= "<br><strong>El paciente "+pacNombre+" con el código "+codigo+" presenta el siguiente cuadro clinico:  </strong>"+rs.getString(2);
			    	pagina+= "<br>"+fecha;	   
			    	pagina+= "<br><strong>Con un grado de certeza de "+certeza+" % </strong>";
			  		pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/info.gif' width='39' height='38'></td>";
			  		pagina+= "<br><strong><div align='top'>                   Si desea obtener un diagnóstico mas profundo usando examenes clínicos, proporcione los datos correspondientes al </div></strong>";		  							
					pagina+= "<br><strong><div align='top'>                   exámen desado. </div></strong>";		  							
					pagina+= "</body>";	
					pagina+= "<form name='form1' method='post' action='/SEDEGIC/servlet/ServletFormularioDinamico'>";
			        pagina+= "<input type='hidden' name='campo1' value='d'>";        
			        pagina+= "<input type='hidden' name='campo2' value='"+codigo+"'>";		        			        
		        	pagina+= "<input type='submit' name='Submit' value='Hemoleucograma'>";
		      		pagina+= "</form></td>";	
		      		pagina+= "<form name='form2' method='post' action='/SEDEGIC/servlet/ServletFormularioDinamico'>";
			        pagina+= "<input type='hidden' name='campo1' value='c'>";        
			        pagina+= "<input type='hidden' name='campo2' value='"+codigo+"'>";		        			        
		        	pagina+= "<input type='submit' name='Submit' value='Coprologico'>";
		      		pagina+= "</form></td>";
		      		
		      		info = "UPDATE diagnostico SET dia_estado='A' WHERE dia_tipo='"+tipo+"' AND pac_codigo='"+codigo+"' ";
	  				statement.executeUpdate(info);
		      		
		      		info = "INSERT INTO diagnostico VALUES ";		      		
	        		info += "(0,"+rs.getString(3)+","+codigo+",NOW(),NOW(),'"+tipo+"','U')";
	        		int i = statement.executeUpdate(info);	        		
			    } 			    
			}  	
			
//**************CCCAMMMMMMBBBBBIIIIIIIAAAAAARRRRRR***************************************************************   		
// Hace la inferencia correspondiente a las reglas de tipo C 		
		
			if(tipo.equals("c"))
			{			
				info= "SELECT enf_codigo, dia_codigo ";
				info+= "FROM diagnostico ";
				info+= "WHERE pac_codigo="+codigo+" ";
				info+= "AND dia_tipo='b' ";
				info+= "ORDER BY dia_codigo DESC";
				       
				
				Statement statement = conn.createStatement();
		        rs= statement.executeQuery(info);
		        rs.next();
				
				info= "SELECT COUNT(*) as campo, enfermedad.enf_nombre, regla.reg_gradoCerteza, enfermedad.enf_codigo ";
				info+= "FROM enfermedad, historia, valorSintoma, sintomaEnfermedad, regla ";
				info+= "WHERE historia.pac_codigo="+codigo+" ";
				info+= "AND historia.his_codigo="+cont+" ";
				info+= "AND valorSintoma.sin_codigo=historia.sin_codigo ";
				info+= "AND valorSintoma.val_codigo=historia.val_codigo ";
				info+= "AND valorSintoma.vas_codigo=sintomaEnfermedad.vas_codigo ";
				info+= "AND valorSintoma.reg_codigo=regla.reg_codigo ";
				info+= "AND sintomaEnfermedad.enf_codigo="+rs.getString(1)+" ";
				info+= "AND sintomaEnfermedad.sie_codres=enfermedad.enf_codigo ";
				info+= "GROUP BY valorSintoma.reg_codigo ";
				info+= "ORDER BY campo DESC";
				
				rs= statement.executeQuery(info);
				
				pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
				pagina+= "<br><strong><div align='top'>DIAGNÓSTICO FINAL DE DIARREAS</div></strong>";
				pagina+= "<br>";	        	      		      		
			    pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/labrador1.jpg' width='152' height='102'></td>";	  			
			    pagina+= "<br>";	
			    
			    if(rs.next())
				{			
					float certeza = ((Integer.parseInt(rs.getString(1))*100)/(Integer.parseInt(rs.getString(3))));
			    	pagina+= "<br><strong>El paciente "+nombrePac+" con el código "+codigo+" presenta el siguiente cuadro clinico:  </strong>"+rs.getString(2);
			    	pagina+= "<br>"+fecha;
			    	pagina+= "<br><strong>Con un grado de certeza de "+certeza+" % </strong>";
			    	
			    /*	info = "UPDATE diagnostico SET dia_estado='A' WHERE dia_tipo='"+tipo+"' AND pac_codigo='"+codigo+"' ";
	  				statement.executeUpdate(info);*/
	  			
	  				info= "INSERT INTO diagnostico VALUES ";	      		
        			info = info + "(0,"+rs.getString(4)+","+codigo+",NOW(),NOW(),'"+tipo+"','U')";
        			statement.executeUpdate(info);
				}
				else
				{
					pagina+= "<br><strong>El paciente "+codigo+" no presenta enfermedad</strong>";
				}
		    	pagina+= "<br>";	   
		  		pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/info.gif' width='39' height='38'></td>";
		  		pagina+= "<br><strong><div align='top'>                   Si desea obtener un diagnóstico mas profundo usando examenes clínicos, proporcione los datos correspondientes al </div></strong>";		  							
				pagina+= "<br><strong><div align='top'>                   exámen desado. </div></strong>";		  							
				pagina+= "</body>";		
	        	pagina+= "<form name='form2' method='post' action='/SEDEGIC/servlet/DiagnosticoFinal'>";
	        	pagina+= "<input type='hidden' name='campo1' value='"+codigo+"'>";
	        	pagina+= "<input type='submit' name='Submit' value='Diagnóstico final'>";
	      		pagina+= "</form></td>";		  		
	  				        
			}
	      	salida = resp2.getWriter();
	        salida.println(pagina);        							
		}
	    catch(IOException ex)
	    {
	    	System.out.println("Error2 "+ex);
	    }
	    		catch(SQLException e)
		{ 
	    	System.out.println("Error "+e);
	    }
   }
   
//******************************************************************************
// Permite cerrar la conexion.

   public void destroy()
   {
   		try
      	{
         	conn.close();
      	}
      	catch(Exception ex)
      	{
         	context.log(ex.toString());
      	}
   }

}