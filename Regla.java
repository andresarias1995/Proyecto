import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class Regla extends HttpServlet
{
	FormularioInsertarRegla formulario=null;
	String c0,c1,c2,c3,c4,c5,c6,c7,c8,c9,cR;
	
	public void init(ServletConfig config) throws ServletException
   	{  
	}
	
	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{
   		PrintWriter salida;	
   		c0=resp1.getParameter("campo0");
   		c1=resp1.getParameter("campo1");
   		c2=resp1.getParameter("campo2");
   		c3=resp1.getParameter("campo3");
   		c4=resp1.getParameter("campo4");
   		c5=resp1.getParameter("campo5");
   		c6=resp1.getParameter("campo6");
   		c7=resp1.getParameter("campo7");
   		c8=resp1.getParameter("campo8");
   		c9=resp1.getParameter("campo9");
   		cR=resp1.getParameter("campoR");
   		
   		salida = resp2.getWriter();
   		formulario = new FormularioInsertarRegla();   		
   		formulario.recibirParametros(c0,c1,c2,c3,c4,c5,c6,c7,c8,c9,cR);
   		formulario.construirFormulario();
   		salida.println(formulario.devolverFormulario());
   	}
}