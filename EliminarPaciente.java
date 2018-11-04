
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

//******************************************************************************
// Clase que permite eliminar el registro de un paciente especifico del sistema

public class EliminarPaciente
{
	
   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ResultSet rs;

//****************************************************************************   
// Constructor de la clase   
   
   public EliminarPaciente()
   {
   		conectarBD();
   }
   
//****************************************************************************   
// Permite establecer la conexion con la base de datos

   public void conectarBD()
   {
   		try
      	{
         	Class.forName("org.gjt.mm.mysql.Driver").newInstance();
         	conn = DriverManager.getConnection(url,login,password);
         	conn.setAutoCommit(false);        	
      	}
      	catch(Exception ex)
      	{ 
         	System.out.println("Error en la conexión con la base de datos. "+ex);
      	}
   }

//****************************************************************************   
// Metodo que permite verificar la existencia de un registro de un paciente en 
// el sistema 
   
   public ResultSet existeRegistro(String codigo)
   {
      	try
      	{
      		String datos = "SELECT * FROM paciente WHERE pac_codigo='"+codigo+"'";      			
		    Statement statement = conn.createStatement();
			rs= statement.executeQuery(datos); 
				 
			return rs;	    	
	          	   
       	}      	   
       	catch(SQLException e)
        {
        }
        return rs;	
   }
   
//******************************************************************************   
// Metodo que permite elimar un registro espcifico del sistema, si el registro
// existe se elimina, de lo contrario se informa al usuario que el registro no se
// puede eliminar porque no existe.

   public void eliminarRegistro(String codigo)
   {
	   	try
	   	{
	   		Statement statement = conn.createStatement();      			      			
		    String datos = "DELETE FROM paciente WHERE pac_codigo='"+codigo+"'"; 
		    statement.executeUpdate(datos);      			
	   	}
	   	catch(SQLException ex)
	   	{
	   	}	  	  	
   }

//******************************************************************************
//Permite cerrar la conxion

   public void destroy()
   {
   		try
      	{
         	conn.close();
      	}
      	catch(Exception ex)
      	{
         	System.out.println("Error al cerrar la conexion con la base de datos. "+ ex);
      	}
   }
}