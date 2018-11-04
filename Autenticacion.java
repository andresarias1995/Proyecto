
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite mostrar el registro de todos los pacientes existentes en el
// sistema

public class Autenticacion
{
	
   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ServletContext context;
   ResultSet rs;   
   
//******************************************************************************   
// Constructor de la clase

   public Autenticacion()
   {
   		conectar();
   }

//******************************************************************************
//Permite abrir la conexion con la base de datos

	public void conectar()
	{
	  	try
      	{
         	Class.forName("org.gjt.mm.mysql.Driver").newInstance();
         	conn = DriverManager.getConnection(url,login,password);
         	conn.setAutoCommit(false);        	
      	}
      	catch(Exception ex)
      	{ 
         	System.out.println("Error al abrir la conexión con la base de datos. "+ex);
      	}		
	}
	
//******************************************************************************
// Permite hacer la verificacion del usuario que pretende entrar al sistema

	
	public ResultSet existeRegistro(String nombre, String clave)
   	{
      	try
      	{
      		String datos = "SELECT * FROM registroUsuario WHERE reu_nombre='"+nombre+"' and reu_password='"+clave+"'";      				      		
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
// Permite cerrar la conexion.

   public void destroy()
   {
   		try
      	{
         	conn.close();
      	}
      	catch(Exception ex)
      	{
         	System.out.println("Error al cerrar la conexión con la base de datos. "+ex);
      	}
   }
}