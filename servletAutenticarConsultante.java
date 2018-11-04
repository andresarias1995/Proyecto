import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;


public class servletAutenticarConsultante extends HttpServlet
{
	ConstruirPagina pag=null;
	Conecciones conn=null;
	PrintWriter salida;
	String tipo, servletAInvocar, codigoPaciente, cedulaPropietario="";
	
	public void init(ServletConfig config) throws ServletException
   	{ 
   		conn = new Conecciones();
		conn.obtenerConexion();  			
   	}
   	
   	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{  
   		try
   		{ 
   			String pagina = "";
   			pag = new ConstruirPagina();
   			
   			codigoPaciente = resp1.getParameter("campo1");
   			cedulaPropietario = resp1.getParameter("campo2"); 
   			
   			if (cedulaPropietario.equals(""))//)||(cedulaPropietario!=null))
   			{
   				pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
   				pagina+= "<br><strong><div align='top'>Debe llenar el campo de la cédula para realizar consultas</div></strong>";	
   				pagina+= "<form name='form1' method='post' action='/SEDEGIC/servlet/AutenticarConsultante'>";
				pagina+= "<input type='submit' name='Submit' value='Aceptar'>";
			    pagina+= "</form>";
			    pagina+= "</body>";
   			}
   			else
   			{      							
   				if (cedulaPropietario!=null)
   				{	
		   			String consulta = "SELECT pac_codigo FROM paciente WHERE pac_codigo = "+codigoPaciente+" ";
		   			consulta += "AND pac_propietariocedula = '"+cedulaPropietario+"'";   			
		   			ResultSet rs = conn.ejecutarQuery(consulta);
		   			
		   			if (rs.next())
		   			{   				
		   				pagina+= "<form name='form1' method='post' action='/SEDEGIC/servlet/ServletFormularioDinamico'>";
					    pagina+= "<input type='hidden' name='campo1' value='a'>";        
					    pagina+= "<input type='hidden' name='campo2' value='"+rs.getString(1)+"'>";
				      	pagina+= "</form>";
				        pagina+="<script>document.form1.submit()</script>";
		   			}
		   			else
		   			{   				
		   				pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
						pagina+= "<br><strong><div align='top'>La cédula del propietario no corresponde</div></strong>";
						pagina+= "<br>";	        	      		      		
					    pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/error.gif' width='32' height='32'></td>";	  			
					    pagina+= "<br>";
		   				pagina+= "<form name='form1' method='post' action='/SEDEGIC/main.htm'>";
					    pagina+= "<BR><B></B>";
					    pagina+= "<input type='submit' name='Submit' value='Aceptar'>";
				      	pagina+= "</form>";
				      	pagina+= "</body>";
		   			}
		   		}
		   		else
		   		{
			   		pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
	   				pagina+= "<br><strong><div align='top'>El campo cédula debe contener solo números</div></strong>";	
	   				pagina+= "<form name='form1' method='post' action='/SEDEGIC/servlet/AutenticarConsultante'>";
					pagina+= "<input type='submit' name='Submit' value='Aceptar'>";
				    pagina+= "</form>";
				    pagina+= "</body>";	
		   		}
	   		}
   			salida = resp2.getWriter();
   			salida.println(pagina);
   		}
   		catch(SQLException ex)
   		{
   		}
   	}
}