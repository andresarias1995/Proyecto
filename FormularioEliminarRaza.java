import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class FormularioEliminarRaza extends HttpServlet
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
	   		String pagina, consulta;
	   		
	   		consulta = "SELECT * FROM raza";
	   		ResultSet rs = conn.ejecutarQuery(consulta);
	   		
	   		pagina="<body bgcolor='#006699' text='#FFFFFF'>";
			pagina+="<table width='481' border='0' cellpadding='0' cellspacing='0'>";  			  			
			pagina+="<tr>"; 
			pagina+="<td width='390' height='146' valign='top'><p><font size='5' face='Monotype Corsiva'>Elija la raza que desea eliminar.</font></p>";
  			pagina+="<p align='right'><font size='5' face='Monotype Corsiva'>Gracias.</font></p>";
  			pagina+="<form name='form1' method='post' action='/SEDEGIC/servlet/EliminarRaza'>";
  			pagina += "<select name ='campo1'>";
  			while (rs.next())
  			{
  				pagina += "<option value='"+rs.getString(1)+"'>"+rs.getString(2);
  			}
  			pagina += "</select>";
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
	   		
	   		salida = resp2.getWriter();
	        salida.println(pagina);
    	}
    	catch(IOException e)
    	{}
    	catch(SQLException e)
    	{}
   	}
}