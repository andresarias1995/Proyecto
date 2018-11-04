import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

//******************************************************************************
// Clase que permite hacer un registro de la informacion de un paciente con los datos
// recogidos del formulario diligenciado.  La informacion del formulario se obtine
// por medio de la clase FormularioPaciente y esta la envia para ser procesada e
// ingresada al sistema

public class RegistroPacientes
{
	
   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ServletContext context;
      
//******************************************************************************   
// Constructor de la clase

   public RegistroPacientes()
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
// Metodo que permite ingresar al sistema los datos del paciente digitados en 
// el formulario.// 

	public void insertarRegistroPacientes(String nomPaciente, int edadPac,String sexoPac, String raza, String propietario, String historia)
	{
		try
		{
			String datosPaciente = "INSERT INTO paciente VALUES ";
        	datosPaciente = datosPaciente + "(0,'"+nomPaciente.toUpperCase()+"',"+edadPac+",'" +sexoPac.toUpperCase()+"','"+raza+"','"+propietario+"','"+historia+"')";
        	Statement statement = conn.createStatement();
           	int i = statement.executeUpdate(datosPaciente);	           	          	       	
        }
        catch(SQLException e)
        {
        } 
	}

//******************************************************************************
// Metodo que permite ingresar al sistema los datos del paciente digitados en 
// el formulario.// 

	public ResultSet devolverCodigoPac(String nombre,String cedulaPropi)
	{
		ResultSet rs = null;
		
		try
		{
			String codigo = "SELECT pac_codigo,pac_nombre FROM paciente WHERE pac_nombre='"+nombre+"'and pac_propietariocedula='"+cedulaPropi+"' ";        	
        	Statement statement = conn.createStatement();
           	rs = statement.executeQuery(codigo);	           	          	       	          	
        }
        catch(SQLException e)
        {
        } 
        return rs;
	}
	
	
//******************************************************************************
// Metodo que permite ingresar al sistema los datos del paciente digitados en 
// el formulario.// 

	public ResultSet validarHistoria(String historia)
	{
		ResultSet rs = null;
		
		try
		{
			String codigo = "SELECT * FROM paciente WHERE pac_historia='"+historia+"' ";        	
        	Statement statement = conn.createStatement();
           	rs = statement.executeQuery(codigo);	           	          	       	          	
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