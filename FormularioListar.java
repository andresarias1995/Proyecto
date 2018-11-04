
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite mostrar los registros de los pacientes registrados en el 
// sistema

public class FormularioListar extends HttpServlet
{
	Listar registro = new Listar(); // Instancia la clase

	public void init(ServletConfig config) throws ServletException
	{
	}

//******************************************************************************
// Permite pintar o llamar los formularios requeridos por las acciones de los 
//usuarios

   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		PrintWriter salida;		
      	String cedula="", pagina="",datos="";      	     	
      	
      	try
      	{
      		ResultSet rs = registro.listarR(); 
      		
      		pagina ="<body bgcolor='#006699' text='#FFFFFF'>";
			pagina+="<br><strong><div align='center'>REPORTE DE DATOS DE PACIENTES REGISTRADOS EN EL SISTEMA</div></strong>";
			pagina+="<br>";
			
				
			while(rs.next())
	        {	
	        	pagina+="<br><strong>Código del Paciente:  </strong>"+rs.getString(1);
		        pagina+="<br><strong>Nombre:  </strong>"+rs.getString(2);
		        pagina+="<br><strong>Edad:  </strong>"+rs.getString(3);
		        pagina+="<br><strong>Sexo:  </strong>"+rs.getString(4);
		        pagina+="<br><strong>Raza:  </strong>"+rs.getString(5);
		        pagina+="<br><strong>Cédula del propietario:  </strong>"+rs.getString(6);
		        pagina+="<br><strong>Código de la historia:  </strong>"+rs.getString(7);
		        pagina+="<br>";
	        	pagina+="<br><strong>******************************************</strong>";
		    }      	      	        
	    	salida = resp2.getWriter();
    		salida.println(pagina);                            	    	       		                            	    	
      	}
       	catch(IOException ex)
       	{
       	}
       	catch(SQLException e)
        {
        }       	
        
	}
}
   
   
