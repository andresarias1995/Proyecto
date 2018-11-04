import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;;

public class Conecciones
{	
	Connection conn = null;
	String login = "";
   	String password = "";
   	String url = "jdbc:mysql://localhost/sedegic";
   	
	public Conecciones()
	{}
	
	public void obtenerConexion()
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
	
	public ResultSet ejecutarQuery(String consulta)
	{
		try
		{
			Statement statement = conn.createStatement();
        	ResultSet rs = statement.executeQuery(consulta);
        	return rs;
    	}
    	catch(SQLException e)
    	{
    	}
    	return null;
	}
	
	public int ejecutarUpdate(String consulta)
	{
		try
		{
			Statement statement = conn.createStatement();
    	    int rs = statement.executeUpdate(consulta);
        	return rs;
        }
        catch(SQLException e)
        {}
        return 0;
	}
	
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