
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite obtener los datos del formulario para eliminar el registro
// requerido.

public class FormularioEliminarPaciente extends HttpServlet
{
	EliminarPaciente registro = new EliminarPaciente(); // Instancia la clase	
  
   	public void init(ServletConfig config) throws ServletException
   	{  
	}

//******************************************************************************
// Permite pintar o llamar los formularios requeridos por las acciones de los 
//usuarios

   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		PrintWriter salida;		
      	String pagina="",codigo="";
      
      	codigo = resp1.getParameter("codigoPac");
      	      
      	try
      	{      	
      		if((codigo.equals("")))
      		{
      			pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
				pagina+= "<table width='587' border='0' cellpadding='0' cellspacing='0'>";
  				pagina+= "<tr>";
    			pagina+= "<td width='482' rowspan='2' valign='top'><p><font size='4' face='Georgia, Times New Roman, Times, serif'>ERROR!!!</font></p>";
      			pagina+= "<p align='justify'><font size='4'>Para eliminar alg&uacute;n registro debe "; 
        		pagina+= "digitar el código, por favor suministre los datos correctamente e ";
        		pagina+= "intentelo de nuevo</font>.</p>";
      			pagina+= "<p align='right'><font size='4' face='Georgia, Times New Roman, Times, serif'>Gracias.</font></p>";
      			pagina+= "<form name='form1' method='post' action='/SEDEGIC/eliminarPacientes.htm'>";
        		pagina+= "<input name='aceptar' type='submit' id='aceptar' value='Aceptar'>";
      			pagina+= "</form></td>";
    			pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/error.gif' width='50' height='42'></td>";
  				pagina+= "</tr>";
  				pagina+= "<tr>";
    			pagina+= "<td height='97'>&nbsp;</td>";
  				pagina+= "</tr>";
				pagina+= "</table>";
				pagina+= "</body>";	
      		
        	}        	
        	else
        	{               	
				ResultSet rs = registro.existeRegistro(codigo); 
				
				if(!rs.next())
	        	{	        	
	        		pagina= "<body bgcolor='#006699' text='#FFFFFF'>";
					pagina+= "<table width='576' border='0' cellpadding='0' cellspacing='0'>";
	  				pagina+= "<tr>";
		    		pagina+= "<td width='482' rowspan='3' valign='top'><p><font size='4'>ERROR!!!</font></p>";
	    	  		pagina+= "<p><font size='4'>El código digitado no se encuentra resgistrado "; 
	        		pagina+= "en el sistema. Por favor verifique los datos e intente de nuevo</font></p>";
	      			pagina+= "<p align='right'><font size='4'>GRACIAS</font></p>";
	      			pagina+= "<form name='form1' method='post' action='/SEDEGIC/eliminarPacientes.htm'>";
		        	pagina+= "<input name='aceptar' type='submit' id='aceptar' value='Aceptar'>";
	    	  		pagina+= "</form></td>";
	    			pagina+= "<td width='9' height='18'></td>";
	    			pagina+= "<td width='51'></td>";
	    			pagina+= "<td width='34'></td>";
	  				pagina+= "</tr>";
		  			pagina+= "<tr>";
	    			pagina+= "<td height='40'></td>";
	    			pagina+= "<td valign='top'><div align='center'><img src='/SEDEGIC/imagenes1/error.gif' width='45' height='40'></div></td>";
	  				pagina+= "<td></td>";
	  				pagina+= "</tr>";
	  				pagina+= "<tr>";
		    		pagina+= "<td height='116'></td>";
	    			pagina+= "<td>&nbsp;</td>";
	    			pagina+= "<td></td>";
	  				pagina+= "</tr>";
	  				pagina+= "<tr>"; 
		    		pagina+= "<td height='8'></td>";
	    			pagina+= "<td></td>";
	    			pagina+= "<td></td>";
	    			pagina+= "<td></td>";
	  				pagina+= "</tr>";
					pagina+= "</table>";
					pagina+= "</body>";
	        	} 
	        	else
	        	{
	        		registro.eliminarRegistro(codigo);
	        		
	        		pagina+="<body bgcolor='#006699' text='#FFFFFF'>";
					pagina+="<table width='449' border='0' cellpadding='0' cellspacing='0'>";
					pagina+="<tr>"; 
					pagina+="<td width='25' height='30'>&nbsp;</td>";
					pagina+="<td width='121'>&nbsp;</td>";
					pagina+="<td width='66'>&nbsp;</td>";
					pagina+="<td width='121'>&nbsp;</td>";
					pagina+="<td width='22'>&nbsp;</td>";
					pagina+="<td width='93'>&nbsp;</td>";
					pagina+="<td width='1'>&nbsp;</td>";
					pagina+="</tr>";
					pagina+="<tr>"; 
					pagina+="<td height='16'></td>";
					pagina+="<td colspan='3' rowspan='2' valign='top'><div align='justify'>"; 
					pagina+="<p><font size='4'>El registro ha sido eliminado correctamente del sistema.</font></p>";
					pagina+="<p align='right'><font size='4'>GRACIAS.</font></p>";
					pagina+="</div></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="</tr>";
					pagina+="<tr>"; 
					pagina+="<td height='74'></td>";
					pagina+="<td></td>";
					pagina+="<td rowspan='2' valign='top'><img src='/SEDEGIC/imagenes1/duke4.gif' width='74' height='93'></td>";
					pagina+="<td></td>";
					pagina+="</tr>";
					pagina+="<tr>";
					pagina+="<td height='19'></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="</tr>";
					pagina+="<tr>";
					pagina+="<td height='24'></td>";
					pagina+="<td>&nbsp;</td>";
					pagina+="<td valign='top'><form name='form1' method='post' action='/SEDEGIC/main.htm'>";
					pagina+="<input type='submit' name='Submit' value='Aceptar'>";
					pagina+="</form></td>";
					pagina+="<td>&nbsp;</td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="<td></td>";
					pagina+="</tr>";
					pagina+="</table>";
					pagina+="</body>";
	        	}
        	}
        	salida = resp2.getWriter();
        	salida.println(pagina);
        }
        catch(IOException ex)
        {
        }                    
        catch(SQLException ex)
        {
        }
   	}  	
    	
}