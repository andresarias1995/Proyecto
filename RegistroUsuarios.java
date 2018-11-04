import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

//******************************************************************************
// Clase que permite realizar el registro de los datos de un paciente en el sistema

public class RegistroUsuarios
{
	
   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;

//****************************************************************************   
// Constructor de la clase   
   
   public RegistroUsuarios()
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
// Metodo que inserta los datos del paciente segun la informacion suministrada
// en el formulario
   
   public void insertarRegistroUsuario(String nombreMed, String centro, int cedula,String password)
   {
      	try
      	{
			String datos = "INSERT INTO registroUsuario VALUES ";
       		datos = datos + "('"+nombreMed.toUpperCase()+"','" +centro.toUpperCase()+"',"+cedula+",'"+password+"')";
       		Statement statement = conn.createStatement();
       		int i = statement.executeUpdate(datos);      
       	}      	   
       	catch(SQLException e)
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