import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;


public class InsertarPacientes extends HttpServlet
{
	ConstruirPagina pag=null;
	Conecciones conn=null;
	PrintWriter salida;
	String tipo, servletAInvocar, codigoPaciente;
	String nomPaciente="",edadP="",sexoPac="",raza="",pagina="", propietario="", historia="";
	
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
	   		
	   		nomPaciente = resp1.getParameter("campo1");
	      	edadP = resp1.getParameter("campo2");
	      	sexoPac = resp1.getParameter("campo3");
	      	raza=resp1.getParameter("campo4");
	      	propietario=resp1.getParameter("campo5");
	      	historia=resp1.getParameter("campo6");
	   		
	   		pag.abrirEncabezado("Registro Pacientes", "html/text");
	   		pag.cerrarEncabezado("#006699");
	   		
	   		String script = "//***************************** VALIDACION PARA QUE LOS CAMPOS SEAN SOLO TEXTO***************************";
			script += "\nfunction validarCampos(num)";
			script += "\n{";
			script += "\nif ((num==1) && (!isNaN(document.form1.campo1.value))&&(document.form1.campo1.value!=''))";
			script += "\n{";
			script += "\nalert('El nombre del paciente debe ser escrito solo con caracteres.');";
			script += "\ndocument.form1.campo1.focus();";
			script += "\n}";
			script += "\n//***************************** VALIDACION PARA QUE LOS CAMPOS SEAN SOLO NUMERICOS***********************";
			
			script += "	\nif ((num==2) && (isNaN(document.form1.campo5.value))&&(document.form1.campo5.value!=''))";
			script += "	\n{";
			script += "	\nalert('La cédula del propietario debe ser escrita solo con números.');";
			script += "	\ndocument.form1.campo5.focus();";
			script += "\n}";
			script += "\nif ((num==3)&&(isNaN(document.form1.campo2.value)))  // Valida que la edad sea solo numerica";
			script += "\n{";
			script += "	\nalert('La edad del paciente debe ser escrita solo con números.');";
			script += "	\ndocument.form1.campo2.focus();";
			script += "	\n}";
			script += "\n}";
			pag.crearScript(script);
	   		
	   		pag.abrirTabla(0,0);
	   			pag.abrirFilaTabla();
	   				pag.abrirEspacioFilaTabla("left","#006699");
	   					pag.establecerFuente("Monotype Corsiva", "5", "white");
	   					pag.crearLabel("REGISTRO DE PACIENTES");	
	   					pag.cerrarFuente();
	   				pag.cerrarEspacioFilaTabla();	   						
	   			pag.cerrarFilaTabla();
	   		pag.cerrarTabla();
	   		
	   		pag.abrirFormulario("1");
	   		pag.abrirTabla(0,0);
	   			pag.abrirEspacioFilaTabla("left","#006699");
	   			//pag.abrirFilaTabla();
	   				pag.abrirTabla(0,0);
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.establecerFuente("Arial", "3", "white");
	   							pag.crearLabel("Código de historia");	
	   							pag.cerrarFuente();
	   						pag.cerrarEspacioFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.crearCampoComplejoTamaV("text", "6", "20", "javascript:validarCampos(6)", "10",historia);
	   						pag.cerrarEspacioFilaTabla();	   				
	   					pag.cerrarFilaTabla();
	   					
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.establecerFuente("Arial", "3", "white");
	   							pag.crearLabel("Nombre del Paciente");	
	   							pag.cerrarFuente();
	   						pag.cerrarEspacioFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.crearCampoComplejoTamaV("text", "1", "20", "javascript:validarCampos(1)","19",nomPaciente);
	   						pag.cerrarEspacioFilaTabla();	   				
	   					pag.cerrarFilaTabla();
	   					
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.establecerFuente("Arial", "3", "white");
	   							pag.crearLabel("Edad (Digite la edad en meses)");	
	   							pag.cerrarFuente();
	   						pag.cerrarEspacioFilaTabla();	   				
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.crearCampoComplejoTamaV("text", "2", "20", "javascript:validarCampos(3)","3",edadP);
	   						pag.cerrarEspacioFilaTabla();	   				
	   					pag.cerrarFilaTabla();
	   					
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.establecerFuente("Arial", "3", "white");
	   							pag.crearLabel("Sexo");	
	   							pag.cerrarFuente();
	   						pag.cerrarEspacioFilaTabla();	   				
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.abrirSelect("3");
	   								if (sexoPac!=null)
	   								{
		   								if (sexoPac.equals("Macho"))
		   								{
		   									pag.crearOpcionSelect("Macho", "Macho", true);
		   									pag.crearOpcionSelect("Hembra", "Hembra", false);
		   								}
		   								else
		   								{
		   									pag.crearOpcionSelect("Macho", "Macho", false);
		   									pag.crearOpcionSelect("Hembra", "Hembra", true);
		   								}
		   							}
		   							else
		   							{
		   								pag.crearOpcionSelect("Macho", "Macho", false);
		   								pag.crearOpcionSelect("Hembra", "Hembra", false);
		   							}
	   							pag.cerrarSelect();
	   						pag.cerrarEspacioFilaTabla();	   				
	   					pag.cerrarFilaTabla();
	   					
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.establecerFuente("Arial", "3", "white");
	   							pag.crearLabel("Raza");	
	   							pag.cerrarFuente();
	   						pag.cerrarEspacioFilaTabla();	   				
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.abrirSelect("4");
								String consulta = "Select raz_codigo, raz_nombre from raza";
								ResultSet rs = conn.ejecutarQuery(consulta);			
								while(rs.next())
								{		
									if (raza!=null)							
									{
										if (raza.equals(rs.getString(1)))
											pag.crearOpcionSelect(""+rs.getString(2), ""+rs.getString(1), true);
										else
											pag.crearOpcionSelect(""+rs.getString(2), ""+rs.getString(1), false);
									}
									else
										pag.crearOpcionSelect(""+rs.getString(2), ""+rs.getString(1), false);
								}					   		   										
	   							pag.cerrarSelect();
	   						pag.cerrarEspacioFilaTabla();
	   					pag.cerrarFilaTabla();
	   					
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.establecerFuente("Arial", "3", "white");
	   							pag.crearLabel("Cédula del propietario");	
	   							pag.cerrarFuente();
	   						pag.cerrarEspacioFilaTabla();	   				
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.crearCampoComplejoTamaV("text", "5", "20", "javascript:validarCampos(6)","10",propietario);
	   						pag.cerrarEspacioFilaTabla();	   				
	   					pag.cerrarFilaTabla();
	   					
	   					pag.abrirFilaTabla();
	   						pag.abrirEspacioFilaTabla("left","#006699");
	   							pag.crearBoton("submit","Ingresar","");
	   						pag.cerrarEspacioFilaTabla();	   					   	
	   					pag.cerrarFilaTabla();
	   					
	   				pag.cerrarTabla();
	   			//pag.cerrarFilaTabla();
	   			pag.cerrarEspacioFilaTabla();
	   			
		   		pag.abrirEspacioFilaTabla("left","#006699");
		   			pag.abrirTabla(0,0);
			   			pag.abrirFilaTabla();
			   				pag.abrirEspacioFilaTabla("center", "#006699");
								pag.crearImagen("../imagenes1/02can.jpg", "135", "120");
							pag.cerrarEspacioFilaTabla();	
			   			pag.cerrarFilaTabla();
		   			pag.cerrarTabla();
		   		pag.cerrarEspacioFilaTabla();
		   		
	   		pag.cerrarTabla();
	   		pag.establecerActionFormulario("1", "/SEDEGIC/servlet/Paciente");
	   		pag.cerrarFormulario();
	   		
	   		pag.cerrarPagina();
	        salida.println(pag.retornarPagina()); 
	    } 		
	    catch(SQLException e)
	    {}
   	}

}