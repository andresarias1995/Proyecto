
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//****************************************************************************
// Clase que permite eliminar un usuario del sistema 

public class EliminarUsuario
{
	
   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ResultSet rs;

//****************************************************************************   
// Constructor de la clase   
   
   public EliminarUsuario()
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
// Metodo que permite verificar si existe un registro de un usuario en el sistema,
// este metodo retorna los datos encontrados en la tabla correspondiente para 
// saber si el usuario se puede eliminar o no del sistema.   
   
   public ResultSet existeRegistro(String cedula)
   {
      	try
      	{
      		String datos = "SELECT * FROM registroUsuario WHERE reu_cedula='"+cedula+"'";      			
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
// Metodo que permite eliminar el registro de un usuario especifico del sistema.
// Solo se permite la eliminacion si el registro existe en el sistema, de lo 
// contrario se informa al usuario que no se puede eliminar los datos requeridos
// porque no existen en el sistema

   public void eliminarRegistro(String cedula)
   {
	   	try
	   	{
	   		Statement statement = conn.createStatement();      			      			
		    String datos = "DELETE FROM registroUsuario WHERE reu_cedula='"+cedula+"'"; 
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