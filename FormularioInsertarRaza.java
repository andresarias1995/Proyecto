import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class FormularioInsertarRaza extends HttpServlet
{
	Conecciones conn;
	String c0="", pagina="", consulta="";
	InsertarRaza raz;
	public void init(ServletConfig config) throws ServletException
   	{     		
		raz=new InsertarRaza();
	}
	
	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{
   		PrintWriter salida;	
   		c0=resp1.getParameter("campo1"); 
   		
   		raz.insertarRaza(c0);
   		
   		if (c0.equals(""))   		
   		{
   			pagina= "<body bgcolor='#006699' text='#FFFFFF'>";
			pagina+="<table width='535' border='0' cellpadding='0' cellspacing='0'>";
			pagina+="<tr>"; 
			pagina+="<td width='482' rowspan='2' valign='top'><p><font size='5' face='Monotype Corsiva'>ERROR!!!</font></p>";
  			pagina+="<p><font size='3' face='Arial, Helvetica, sans-serif'>Para realizar un registro ";
    		pagina+="correcto de razas , el campo en el formulario deben estar ";
    		pagina+="diligenciado. </font></p>";
  			pagina+="<p><font size='3' face='Arial, Helvetica, sans-serif'>Por favor suministre ";
    		pagina+="los datos faltantes e inténtelo de nuevo.</font></p>";
  			pagina+="<p align='right'><font size='5' face='Monotype Corsiva'>Gracias.</font></p>";
  			pagina+="<form name='form1' method='post' action='/SEDEGIC/CargarRaza.htm'>";
    		pagina+="<input name='aceptar' type='submit' id='aceptar' value='Aceptar'>";
  			pagina+="</form></td>";
			pagina+="<td width='9' height='45'>&nbsp;</td>";
			pagina+="<td width='44' valign='top'><div align='left'><img src='/SEDEGIC/imagenes1/error.gif' width='42' height='39'</div></td>";
			pagina+="</tr>";
			pagina+="<tr>";
			pagina+="<td height='169'>&nbsp;</td>";
			pagina+="<td>&nbsp;</td>";
			pagina+="</tr>";
			pagina+="<tr>"; 
			pagina+="<td height='1'></td>";
			pagina+="<td></td>";
			pagina+="<td></td>";
			pagina+="</tr>";
			pagina+="</table>";
			pagina+="</body>"; 
   		}
   		else
   		{
   		pagina += "<form name='form1' method='post' action='/SEDEGIC/mainAdmin.htm'>";
   		pagina += "</form>";
   		pagina += "<script>document.form1.submit();</script>";
   		}
   		
   		salida = resp2.getWriter();   		
   		salida.println(pagina);
   	}
}