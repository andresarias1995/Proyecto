import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ServletFormularioDinamico extends HttpServlet
{
	ConstructorFormularios con=null;
	PrintWriter salida;
	String tipo, servletAInvocar, codigoPaciente;
	
	public void init(ServletConfig config) throws ServletException
   	{   			
   	}
   	
   	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{
   		servletAInvocar = "/SEDEGIC/servlet/Historia";
   		tipo = resp1.getParameter("campo1");
   		codigoPaciente = resp1.getParameter("campo2");   		
   		
   		con = new ConstructorFormularios(); 
   		salida = resp2.getWriter();
   		con.ConstruirFormularioExamenFisico(""+tipo,""+codigoPaciente,""+servletAInvocar);
   	//	salida.println("PAGINA");
        salida.println(con.devolverFormulario());  		
   	}

}