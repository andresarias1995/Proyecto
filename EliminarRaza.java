import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class EliminarRaza extends HttpServlet
{
	Conecciones conn = new Conecciones();
	public void init(ServletConfig config) throws ServletException
   	{  
   		conn.obtenerConexion();
	}
		
	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   	{
	   	try
	   	{
	   		PrintWriter salida;	
	   		String pagina, consulta, raza;
	   		
	   		pagina="<body bgcolor='#006699' text='#FFFFFF'>";
			pagina+="<table width='481' border='0' cellpadding='0' cellspacing='0'>";  			  			
			pagina+="<tr>"; 
			pagina+="<td width='390' height='146' valign='top'><p><font size='5' face='Monotype Corsiva'>La raza seleccionada ha sido eliminada.</font></p>";
  			pagina+="<p align='right'><font size='5' face='Monotype Corsiva'>Gracias.</font></p>";
  			pagina+="<form name='form1' method='post' action='/SEDEGIC/mainAdmin.htm'>";
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
	   		
	   		raza = resp1.getParameter("campo1");	   		
		    String datos = "DELETE FROM raza WHERE raz_codigo='"+raza+"'"; 
		    conn.ejecutarUpdate(datos);
		    
		    salida = resp2.getWriter();
        	salida.println(pagina);
	   	}
	   	catch(IOException ex)
	   	{
	   	}	  	  	
   	}
}