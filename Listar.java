import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite mostrar el registro de todos los pacientes existentes en el
// sistema

public class Listar
{
	
   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ServletContext context;
   ResultSet rs;   
   
//******************************************************************************   
// Constructor de la clase

   public Listar()
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
// Metodo que permite obtener los datos de todos los registros de la base de datos
// y retornarlos a la calse FormularioListar para mostrarlos al usuario.

	public ResultSet listarR()
   	{
      	try
      	{
      		String datos = "SELECT * ";
      		datos = datos+"FROM paciente, raza WHERE paciente.raz_codigo = raza.raz_codigo ";      			      			
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