
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite mostrar los registros de los pacientes registrados en el 
// sistema

public class FormularioAutenticacionAdmon extends HttpServlet
{
	Autenticacion registro = new Autenticacion(); // Instancia la clase

	public void init(ServletConfig config) throws ServletException
	{
	}

//******************************************************************************
// Permite pintar o llamar los formularios requeridos por las acciones de los 
// usuarios

   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		      	
   		PrintWriter salida;		
      	String nombre="",clave="", pagina="", datos="";
      	ResultSet rs;
      	      	      	      	
      	nombre = resp1.getParameter("nombre");
      	clave = resp1.getParameter("clave");    
      	
      	     	
		try
      	{     	
			if((nombre.equals(""))||(clave.equals("")))
   			{
   				pagina ="<body bgcolor='#006699' text='#FFFFFF'>";
   				pagina+= "<table width='565' border='0' cellpadding='0' cellspacing='0'>";  
  				pagina+="<tr>"; 
			    pagina+="<td width='17' height='28'>&nbsp;</td>";
			    pagina+="<td width='482'>&nbsp;</td>";
			    pagina+="<td width='36'></td>";
			    pagina+="<td width='30'></td>";
			  	pagina+="</tr>";
				pagina+="<tr>";
			    pagina+="<td height='36'>&nbsp;</td>";
			    pagina+="<td rowspan='2' valign='top'><p><font face='Arial, Helvetica, sans-serif'>ERROR!!!</font></p>";
		      	pagina+="<p><font face='Arial, Helvetica, sans-serif'>Para ingresar al modulo de ";
        		pagina+="administraci&oacute;n debe ingresar los datos requeridos por el sistema,"; 
        		pagina+="de lo contrario éste no podr&aacute; prestar el servicio requerido por "; 
        		pagina+="usted.</font></p>";
      			pagina+="<p><font face='Arial, Helvetica, sans-serif'>Por favor, digite los datos ";
        		pagina+="correctamente e intentelo de nuevo.</font></p>";
      			pagina+="<p align='right'><font face='Arial, Helvetica, sans-serif'>GRACIAS.</font></p></td>";
    			pagina+="<td valign='top'><img src='/SEDEGIC/imagenes1/error.gif' width='32' height='32'></td>";
			  	pagina+="<td>&nbsp;</td>";
				pagina+="</tr>";
				pagina+="<tr>";
    			pagina+="<td height='129'>&nbsp;</td>";
    			pagina+="<td>&nbsp;</td>";
    			pagina+="<td>&nbsp;</td>";
  				pagina+="</tr>";
				pagina+="</table>";
			}
			else
      		{	
      			rs = registro.existeRegistro(nombre, clave); 	      
	      		      			
      			if(!rs.next())
      			{
      				pagina+="<body bgcolor='#006699' text='#FFFFFF'>";
					pagina+= "<table width='587' border='0' cellpadding='0' cellspacing='0'>";
	  				pagina+= "<tr>";
	    			pagina+= "<td width='482' rowspan='2' valign='top'><p><font size='4' face='Georgia, Times New Roman, Times, serif'>ERROR!!!</font></p>";
	      			pagina+= "<p align='justify'><font size='4'>El nombre de usuario y la clave que digitó "; 
	        		pagina+= "son incorrectos, o no se encuentran registrados en el sistema. Por favor escribalos correctamente e intentelo de nuevo</font>.</p>";	        		
	      			pagina+= "<p align='right'><font size='4' face='Georgia, Times New Roman, Times, serif'>Gracias.</font></p>";
	      			pagina+= "<form name='form1' method='post' action='/SEDEGIC/Administrador.htm'>";
	        		pagina+= "<input name='aceptar' type='submit' id='aceptar' value='Aceptar'>";
	      			pagina+= "</form></td>";
	    			pagina+= "<td width='105' height='96' valign='top'><img src='/SEDEGIC/imagenes1/error.gif' width='46' height='42'></td>";
	  				pagina+= "</tr>";
	  				pagina+= "<tr>";
	    			pagina+= "<td height='97'>&nbsp;</td>";
	  				pagina+= "</tr>";
					pagina+= "</table>";
					pagina+= "</body>";	
      			}
      			else
      			{           			
	      			if(rs.next())
	      			{
	      				pagina= "";
	      			}  		
                                
	        		//pagina+="<form name='formulario' target='blank' method='post' action='../Administracion.htm'></form>";
	        		pagina+="<script>window.open('../Administracion.htm','Administración','width=1024, height=768, location=no, menubar=yes, status=yes, toolbar=yes, scrollbars=yes, resizable=yes')</script>"; 
    	       		//pagina+="<script>document.formulario.submit();</script>"; 
	    		} 
            	salida = resp2.getWriter();
	       		salida.println(pagina);    	           	
    		}
    	}
        catch(IOException ex)
        {
        }      
        catch(SQLException e)
        {        		
        }
	} 
}