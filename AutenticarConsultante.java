import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;


public class AutenticarConsultante extends HttpServlet
{
	ConstruirPagina pag=null;
	Conecciones conn=null;
	PrintWriter salida;
	String tipo, servletAInvocar, codigoPaciente;
	
	public void init(ServletConfig config) throws ServletException
   	{ 
   		conn = new Conecciones();
		conn.obtenerConexion();  			
   	}
   	
   	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{  
   		try
   		{ 		
	   		pag = new ConstruirPagina();
	   		salida = resp2.getWriter();
	   		
	   		String script = "//***************************** VALIDACION PARA QUE LOS CAMPOS SEAN SOLO NUMERICOS***************************";
			script += "\nfunction validarCampos(num)";
			script += "\n{";	
			script += "\nif ((num==0)&&(isNaN(document.form0.campo2.value)))  // Valida que la edad sea solo numerica";
			script += "\n{";
			script += "	\nalert('La cédula del propietario debe ser escrita solo con números.');";
			script += "	\ndocument.form0.campo2.focus();";
			script += "	\n}";
			script += "	\n}";
			script += "\nfunction tipoConsulta(num)";
			script += "\n{";
			script += "\nvalidarCampos(0);";
			script += "\nif(num==1)  // Si es una consulta";
			script += "\n{";			
			script += "	\ndocument.form0.action='/SEDEGIC/servlet/servletAutenticarConsultante';";
			script += "	\n}";
			script += "\nif(num==2)  // Si es una consulta";
			script += "\n{";
			script += "	\ndocument.form0.action='/SEDEGIC/servlet/DiagnosticosPasados';";
			script += "	\n}";
			script += "	\n}";
			
	   		pag.abrirEncabezado("Autenticar consultante", "html/text");
	   		pag.cerrarEncabezado("#006699");
	   		
	   		pag.crearScript(script);
	   		
	   		pag.abrirFormulario("0");
	   		pag.abrirTabla(0,0);
	   			pag.abrirFilaTabla();
	   				pag.abrirEspacioFilaTabla("left","#006699");
	   					pag.establecerFuente("Arial", "3", "white");
	   						pag.crearLabel(" Para realizar consultas usando SEDEGIC, debe tener registrado \n un paciente. Si ya lo registró, digite en el siguiente campo la cédula \n del propietario del paciente y el código de este. ");
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();
	   			pag.cerrarFilaTabla();
	   		pag.cerrarTabla();
	   		
	   		pag.abrirTabla(0,0);
	   		pag.abrirFilaTabla();
	   				pag.abrirEspacioFilaTabla("center", "#006699");
						pag.crearImagen("../imagenes1/_dogs113.jpg", "135", "109");
					pag.cerrarEspacioFilaTabla();
	   		pag.cerrarFilaTabla();
	   		pag.cerrarTabla();
	   		
	   		pag.abrirTabla(0,0);
	   			pag.abrirFilaTabla();
	   				pag.abrirEspacioFilaTabla("center","#006699");
	   					pag.establecerFuente("Arial", "3", "white");
	   						pag.crearLabel(" Pacientes registrados ");
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();
	   				pag.abrirEspacioFilaTabla("center","#006699");
	   					pag.establecerFuente("Arial", "3", "white");
		   					pag.abrirSelect("1");
	   		   		
							String consulta = "Select pac_codigo, pac_nombre from paciente";
							ResultSet rs = conn.ejecutarQuery(consulta);
			
							while(rs.next())
							{
								pag.crearOpcionSelect(""+rs.getString(1)+" - "+rs.getString(2), ""+rs.getString(1), false);
							}				
	   		   		
	   						pag.cerrarSelect();   		
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();
	   			pag.cerrarFilaTabla();
	   			  			
	   			
	   			pag.abrirFilaTabla();
	   		   		pag.abrirEspacioFilaTabla("center","#006699");
	   					pag.establecerFuente("Arial", "3", "white");
	   						pag.crearLabel(" Cédula propietario ");
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();
	   				pag.abrirEspacioFilaTabla("center","#006699");
	   					pag.establecerFuente("Arial", "3", "white");
	   						pag.crearCampoComplejoTama("text", "2", "20", "javascript:validarCampos(0)","10");
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();
	   			pag.cerrarFilaTabla();
	   			
	   			pag.abrirFilaTabla();
	   				pag.abrirEspacioFilaTabla("center","#006699");
	   					pag.establecerFuente("Arial", "3", "white");
	   						pag.crearBoton("Submit", "Consulta nueva", "javascript:tipoConsulta(1)");
	   						pag.crearBoton("Submit", "Diagnosticos pasados", "javascript:tipoConsulta(2)");
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();
	   				pag.abrirEspacioFilaTabla("center","#006699");	   					
	   				pag.cerrarEspacioFilaTabla();
	   			pag.cerrarFilaTabla();	   	
				
	   		//	pag.establecerActionFormulario("0", "/SEDEGIC/servlet/servletAutenticarConsultante");
	   		pag.cerrarTabla();
	   		pag.cerrarFormulario();
	   		
	   		
	   		
	   		pag.cerrarPagina();
	        salida.println(pag.retornarPagina()); 
	    } 		
	    catch(SQLException e)
	    {}
   	}

}