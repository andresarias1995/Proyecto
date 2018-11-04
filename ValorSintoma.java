import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

public class ValorSintoma extends HttpServlet
{
	//RegistroUsuarios registro = new RegistroUsuarios(); // Instancia la clase	
 	String login = "";
   	String password = "";
   	String url = "jdbc:mysql://localhost/sedegic";
   	String c0,c1,c2,c3,c4,c5,c6,c7,c8,c9,consulta="";
	String valor, sintoma, enfermedad, tipoRegla, numSintomas, regla, valorSintoma;

   	Connection conn;
   	ServletContext context;
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

   	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   	{
   	   	
    	PrintWriter salida;	
    	String pagina="";
    	
   		c0=resp1.getParameter("campo0");
   		c1=resp1.getParameter("campo1");
   		c2=resp1.getParameter("campo2");
   		c3=resp1.getParameter("campo3");
   		c4=resp1.getParameter("campo4");
   		c5=resp1.getParameter("campo5");
   		c6=resp1.getParameter("campo6");
   		c7=resp1.getParameter("campo7");
   		c8=resp1.getParameter("campo8");
   		c9=resp1.getParameter("campo9");
   		regla=resp1.getParameter("campoR");
   		
   		try
   		{
   			Statement statement = conn.createStatement();
   			ResultSet rs=null;
		
	   		pagina+="<html><head>";
	   
	   		pagina+="</head>";
	   		pagina+="<body bgcolor='#006699' text='#FFFFFF'>";
	   		
	   		if(((c0==null) && (c1==null))||((c2==null) && (c3==null))||((c4==null))||((c5==null) && (c6==null))||((c7== null) && (c8==null)))	   		 
	   		{
	   			pagina+="<table width='481' border='0' cellpadding='0' cellspacing='0'>";  			  			
  				pagina+="<tr>"; 
    			pagina+="<td width='390' height='146' valign='top'><p><font size='5' face='Monotype Corsiva'>";
    			pagina+="Los datos no fueron ingresados correctamente, por favor ";
    			pagina+=" digitelos nuevamente e intentelo de nuevo.";
      			pagina+="<p align='right'><font size='5' face='Monotype Corsiva'>Gracias.</font></p>";
      			pagina+="<form name='form1' method='post' action='/SEDEGIC/servlet/FormularioInsertarRegla'>";
        		pagina+="<input name='aceptar' type='submit' id='aceptar' value='Aceptar'>";
      			pagina+="</form></td>";
    			pagina+="<td width='88' valign='top'><img src='/SEDEGIC/imagenes1/duke4.gif' width='76' height='88'></td>";;
    			pagina+="<td width='3'>&nbsp;</td>";
  				pagina+="</tr>";
  				pagina+="<tr>";
    			pagina+="<td height='2'></td>"; 
    			pagina+="<td></td>";
    			pagina+="<td></td>";
  				pagina+="</tr>";
				pagina+="</table>";
				pagina+="</body>";		 
	   		}
	   		else
	   		{
		   		if (c0!=null) //Quiere decir que se ingresó una nueva enfermedad
		   		{	   			
		   			consulta="INSERT INTO enfermedad VALUES "; //Inserta la nueva enfermedad
		   			consulta+="(0,'"+c0+"')";
		   			statement.executeUpdate(consulta);	   			
		   			
		   			consulta="SELECT MAX(enf_codigo) FROM enfermedad"; //Calcula el código de la enfermedad
		   			rs = statement.executeQuery(consulta);
		   			rs.next();
		   			
		   			enfermedad=rs.getString(1);	   			
		   		}
		   		else // Si no es nueva enfermedad
		   		{
		   			enfermedad=c1;	   			
		   		}
		   		
		   		if (c2!=null) //Quiere decir que se ingresó un nuevo tipo de regla
		   		{
		   			tipoRegla=c2;
		   		}
		   		else //Si se eligió un tipo de regla existente
		   		{
		   			tipoRegla=c3;
		   		}	
		   		
		   		if (c4!=null) // Se establece el número de síntomas que tendrá la regla
		   		{	   		
		   			numSintomas = c4;
		   		}
		   		else
		   		{
		   			numSintomas = "0";
		   		}
		   				
		   		if (regla.equals(" ")) //Si no hay una regla elegida busca alguna
		   		{
		   			consulta="SELECT reg_codigo FROM regla WHERE reg_tipo='"+tipoRegla+"' AND enf_codigo="+enfermedad+"";
		   			
		   			rs = statement.executeQuery(consulta);
		   			
		   			if (rs.next()) //Si encontro alguna regla actualiza su número de síntomas
		   			{
		   				regla=rs.getString(1);
		   				consulta="UPDATE regla SET reg_gradoCerteza="+numSintomas+" WHERE reg_codigo="+regla+"";
						statement.executeUpdate(consulta);				
					
						rs.next();
		   			}
		   			else // Si no encontró la crea
		   			{
		   				consulta="INSERT INTO regla VALUES "; //Inserta el nuevo valor
			   			consulta+="(0,'"+tipoRegla+"',"+numSintomas+","+enfermedad+")";
			   			statement.executeUpdate(consulta);
			   			
			   			consulta="SELECT MAX(reg_codigo) FROM regla"; //Calcula el código de la enfermedad
			  			rs = statement.executeQuery(consulta);
			  			rs.next();
			  					 
						regla=rs.getString(1);
		   			}
		   		}
		   		else // Si ya hay una regla elegida actualiza su número de sintomas
		   		{
		   			consulta="UPDATE regla SET reg_gradoCerteza="+numSintomas+" WHERE reg_codigo="+regla+"";
					statement.executeUpdate(consulta);
		   		}
				
				if (c5!=null) //Si se ingresó un nuevo síntoma para la regla
				{
					consulta="INSERT INTO sintoma VALUES ";
					consulta+="(0,'"+c5+"')";
					statement.executeUpdate(consulta);
							
					consulta="SELECT MAX(sin_codigo) FROM sintoma";
					rs = statement.executeQuery(consulta);
					rs.next();
							
					sintoma=rs.getString(1);
				}
				else //Si elige un síntoma existente
				{
					sintoma = c6;
				}
							
				if (c7!=null) //Si ingresa un nuevo valor para sintomas
				{
					consulta="INSERT INTO valor VALUES ";
					consulta+="(0,'"+c7+"')";
					statement.executeUpdate(consulta);
						
					consulta="SELECT MAX(val_codigo) FROM valor";
					rs = statement.executeQuery(consulta);
					rs.next();
								
					valor=rs.getString(1);
				}
				else //Si elige un síntoma de la lista
				{
					valor=c8;
				}
				
				consulta="INSERT INTO valorSintoma VALUES ";
				consulta+="(0,"+sintoma+","+valor+","+regla+")";
				statement.executeUpdate(consulta);
				
				consulta="SELECT MAX(vas_codigo) FROM valorSintoma";
				rs = statement.executeQuery(consulta);
				rs.next();
						
				valorSintoma = rs.getString(1);
				
				if (tipoRegla.equals("c"))
				{
					consulta="INSERT INTO sintomaEnfermedad VALUES ";
					consulta+="("+valorSintoma+","+c9+","+enfermedad+")";
					statement.executeUpdate(consulta);
				}
			   	
		   		pagina+="\n<form name='form1' method='post' action='/SEDEGIC/servlet/Regla'>";
				pagina+="\n<input type='hidden' name='campo0' value=''>";
				pagina+="\n<input type='hidden' name='campo1' value='"+enfermedad+"'>";
				pagina+="\n<input type='hidden' name='campo2' value=''>";
				pagina+="\n<input type='hidden' name='campo3' value='"+tipoRegla+"'>";
				pagina+="\n<input type='hidden' name='campo4' value='"+numSintomas+"'>";
				pagina+="\n<input type='hidden' name='campo5' value=''>";
				pagina+="\n<input type='hidden' name='campo6' value='"+sintoma+"'>";
				pagina+="\n<input type='hidden' name='campo7' value=''>";
				pagina+="\n<input type='hidden' name='campo8' value='"+valor+"'>";
				pagina+="\n<input type='hidden' name='campo9' value='"+valorSintoma+"'>";
				pagina+="\n<input type='hidden' name='campoR' value='"+regla+"'>";
				pagina+="\n</form>";			
				pagina+="\n<script>document.form1.submit</script>";
				pagina+="\n</body>\n</html>";	
			}
			salida = resp2.getWriter();
			salida.println(pagina);
	   	}
	   	catch(SQLException e)
        {
        	context.log(e.toString());
        }
        catch(IOException ex)
        {
        	context.log(ex.toString());
        } 
   	}
  }