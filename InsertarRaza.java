import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class InsertarRaza
{
	Conecciones conn;
	String consulta="";
	
	public InsertarRaza()
	{
		conn=new Conecciones();
		conn.obtenerConexion();
	}	
   	
   public void insertarRaza(String codigo)// boolean ban)
   {
      	consulta = "INSERT INTO raza VALUES(0,'"+codigo+"')";
   		conn.ejecutarUpdate(consulta);       	        
   		//conn.destroy();
   }
}