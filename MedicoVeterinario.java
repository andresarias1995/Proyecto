
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

//******************************************************************************
// Clase que permite obtener los datos del usuario diligenciados en el formulario 
// para ser procesados por el sistema

public class MedicoVeterinario extends HttpServlet
{
	RegistroUsuarios registro = new RegistroUsuarios(); // Instancia la clase	
  
   	public void init(ServletConfig config) throws ServletException
   	{  
	}

//******************************************************************************
// Permite pintar los formularios con diferente informacion requerida por las 
// acciones de los usuarios

   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		PrintWriter salida;		
      	String nombreMed="",password="",coPass="",centro="",pagina="";
      	int cedula;
      
      	nombreMed = resp1.getParameter("nombre");
      	if (!resp1.getParameter("id").equals(""))
      		cedula = Integer.parseInt(resp1.getParameter("id"));
      	else
      		cedula = -1;
      	password = resp1.getParameter("pass");
      	coPass=resp1.getParameter("cPass");
      	centro=resp1.getParameter("centroVet");

//******************************************************************************
// Informa que el usuario ha sido registrado correctamente en el sistema.
      	try
      	{
      		if(password.equals(coPass)&&((!nombreMed.equals(""))&&(!password.equals(""))&&(!coPass.equals(""))&&(!centro.equals(""))))
      		{      			
      		        	
                registro.insertarRegistroUsuario(nombreMed, centro, cedula,password);      
                        
      			pagina="<body bgcolor='#006699' text='#FFFFFF'>";
				pagina+="<table width='481' border='0' cellpadding='0' cellspacing='0'>";  			  			
  				pagina+="<tr>"; 
    			pagina+="<td width='390' height='146' valign='top'><p><font size='5' face='Monotype Corsiva'>El usuario ha sido registrado exitosamente en el sistema.</font></p>";
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
      		    			
        	} 
        	
//******************************************************************************
// Si los datos presentan errores, el sistema no deja que el usuario se registre 
// en el sistema 
        	       	
        	else
        	{        		        		               	
				pagina= "<body bgcolor='#006699' text='#FFFFFF'>";
				pagina+="<table width='555' border='0' cellpadding='0' cellspacing='0'>";
  				pagina+="<tr>"; 
    			pagina+="<td width='7' height='33'>&nbsp;</td>";
    			pagina+="<td width='40'>&nbsp;</td>";
    			pagina+="<td width='26'>&nbsp;</td>";
    			pagina+="<td width='482' rowspan='3' valign='top'><p><font size='5' face='Monotype Corsiva'>ERROR!!!</font></p>";
      			pagina+="<p><font size='4' face='Monotype Corsiva'>El sistema no pudo registrar el"; 
        		pagina+=" usuario correctamente, debe suministrar nuevamente los datos y volverlo"; 
        		pagina+=" a intentar.</font></p>";
      			pagina+="<p align='right'><font size='5' face='Monotype Corsiva'>Gracias.</font></p>";
      			pagina+="<form name='form1' method='post' action='/SEDEGIC/RegistroMedicos.htm'>";
        		pagina+="<input name='aceptar' type='submit' id='aceptar' value='Aceptar'>";
      			pagina+="</form></td>";
  				pagina+="</tr>";
  				pagina+="<tr>";
    			pagina+="<td height='40'>&nbsp;</td>";
    			pagina+="<td valign='top'><img src='/SEDEGIC/imagenes1/error.gif' width='40' height='40'></td>";
    			pagina+="<td>&nbsp;</td>";
  				pagina+="</tr>";	
  				pagina+="<tr>"; 
    			pagina+="<td height='148'>&nbsp;</td>";
    			pagina+="<td>&nbsp;</td>";
    			pagina+="<td>&nbsp;</td>";
  				pagina+="</tr>";
				pagina+="</table>";
				pagina+="</body>";			
        	}
        	salida = resp2.getWriter();
        	salida.println(pagina);
        }
        catch(IOException ex)
        {
        }                    
   	}
  }
   

