import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class EliminarRegla extends HttpServlet
{
	Conecciones conn;
	String c0="", pagina="", consulta="";
	public void init(ServletConfig config) throws ServletException
   	{  
   		conn=new Conecciones();
		conn.obtenerConexion();
	}
	
	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{
   		PrintWriter salida;	
   		c0=resp1.getParameter("campo1"); 
   		
   		consulta = "DELETE FROM regla WHERE reg_codigo="+c0+"";
   		conn.ejecutarUpdate(consulta);
   		
   		consulta = "DELETE FROM sintomaEnfermedad, valorSintoma ";
   		consulta += "WHERE sintomaEnfermedad.vas_codigo=valorSintoma.vas_codigo ";
   		consulta += "AND valorSintoma.reg_codigo = "+c0+"";
   		conn.ejecutarUpdate(consulta);
   		
   		consulta = "DELETE FROM valorSintoma WHERE reg_codigo="+c0+"";
   		conn.ejecutarUpdate(consulta);
   		
   		pagina += "<form name='form1' method='post' action='/SEDEGIC/servlet/ListarReglas'>";
   		pagina += "</form>";
   		pagina += "<script>document.form1.submit();</script>";
   		
   		salida = resp2.getWriter();   		
   		salida.println(pagina);
   	}
}