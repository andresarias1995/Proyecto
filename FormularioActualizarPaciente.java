import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite obtener los datos del paciente diligenciados en el formulario
// y ennviarla a la clase RegistroPaciente para ser procesada.

public class FormularioActualizarPaciente extends HttpServlet
{	
 	ActualizacionPaciente registro = new ActualizacionPaciente();
 	  
   	public void init(ServletConfig config) throws ServletException
   	{   		
   	}

//******************************************************************************
// Permite pintar los diferentes formularios segun la accion requerida por el 
// usuario    

   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		PrintWriter salida;		
      	String nomPaciente="",edadP="",sexoPac="",raza="",pagina="", propietario="", historia="";
      
     	
     	nomPaciente = resp1.getParameter("campo1");
      	edadP = resp1.getParameter("campo2");
      	sexoPac = resp1.getParameter("campo3");
      	raza=resp1.getParameter("campo4");
      	propietario=resp1.getParameter("campo5");
      	historia=resp1.getParameter("campo6");
      	
//******************************************************************************      	
// Si los datos no son diligenciados, el sistema no permite el ingreso del registro
// del paciente.

   		try
   		{   	
      		if((nomPaciente.equals(""))||(edadP.equals(""))||(sexoPac.equals(""))||(raza.equals(""))||(propietario.equals("")))
      		{	
      			pagina= "<body bgcolor='#006699' text='#FFFFFF'>";
				pagina+="<table width='535' border='0' cellpadding='0' cellspacing='0'>";
  				pagina+="<tr>"; 
    			pagina+="<td width='482' rowspan='2' valign='top'><p><font size='5' face='Monotype Corsiva'>ERROR!!!</font></p>";
      			pagina+="<p><font size='3' face='Arial, Helvetica, sans-serif'>Para realizar un registro ";
        		pagina+="correcto del paciente , todos los campos en el formulario deben estar ";
        		pagina+="diligenciados. </font></p>";
      			pagina+="<p><font size='3' face='Arial, Helvetica, sans-serif'>Por favor suministre ";
        		pagina+="los datos faltantes e intentelo de nuevo.</font></p>";
      			pagina+="<p align='right'><font size='5' face='Monotype Corsiva'>Gracias.</font></p>";
      			pagina+="<form name='form1' method='post' action='/SEDEGIC/servlet/InsertarPacientes'>";
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
        	
//******************************************************************************
// Si el formulario es correctamente diligenciado el sistema permite el ingreso del
// registro del paciente 

        	else
        	{
      	    	int edadPac=Integer.parseInt(edadP);
      	    	ResultSet rs = null;
      	            		
      			registro.actualizarRegistroPacientes(nomPaciente,edadPac,sexoPac,raza,propietario,historia);
      			rs = registro.devolverCodigoPac(historia);
      			rs.next();
           		
           		pagina="<body bgcolor='#006699' text='#FFFFFF'>";
				pagina+="<table width='481' border='0' cellpadding='0' cellspacing='0'>";  			  			
  				pagina+="<tr>"; 
    			pagina+="<td width='390' height='146' valign='top'><p><font size='5' face='Monotype Corsiva'>";
    			pagina+="El paciente <strong>"+rs.getString(2)+" </strong>ha sido registrado exitosamente en el sistema.";
    			pagina+=" Para futuras consultas sobre él, use el código: <strong> "+rs.getString(1)+"</strong></font></p>";
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
			salida = resp2.getWriter();
        	salida.println(pagina);       	       	
         
      	}  
      	catch(SQLException e)
		{
		}
		
       	catch(IOException ex)
       	{
       	}   
   }  

}