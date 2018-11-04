import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ActualizarPaciente extends HttpServlet
{
	ConstruirPagina pag=null;
	Conecciones conn=null;
	PrintWriter salida;
	String tipo, servletAInvocar, codigoPaciente, consulta;
	ResultSet rs = null;
	
	public void init(ServletConfig config) throws ServletException
   	{ 
   		conn = new Conecciones();
		conn.obtenerConexion();  			
   	}
   	
   	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2) throws ServletException,IOException
   	{  
   		try
   		{ 	
   			consulta="";
	   		pag = new ConstruirPagina();
	   		salida = resp2.getWriter();
	   		
	   		codigoPaciente = resp1.getParameter("campo1");
	   		
	   		pag.abrirEncabezado("Actualizar Pacientes", "html/text");
	   		pag.cerrarEncabezado("#006699");
	   		
	   		consulta += "SELECT * FROM paciente ";
	   		consulta += "WHERE pac_historia = '"+codigoPaciente+"'";
	   		rs = conn.ejecutarQuery(consulta);
	   		if (rs.next())
	   		{
		   	/*	String script = "//***************************** VALIDACION PARA QUE LOS CAMPOS SEAN SOLO TEXTO***************************";
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
				pag.crearScript(script);*/
		   		
		   		pag.abrirTabla(0,0);
		   			pag.abrirFilaTabla();
		   				pag.abrirEspacioFilaTabla("left","#006699");
		   					pag.establecerFuente("Monotype Corsiva", "5", "white");
		   					pag.crearLabel("ACTUALIZACIÓN DE REGISTRO DE PACIENTES");
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
		   							pag.establecerFuente("Arial", "3", "white");
		   							pag.crearLabel(""+rs.getString(7));
		   							pag.crearCampoSencillo("hidden", "6", "20", ""+rs.getString(7));
		   						pag.cerrarEspacioFilaTabla();	   				
		   					pag.cerrarFilaTabla();
		   					
		   					pag.abrirFilaTabla();
		   						pag.abrirEspacioFilaTabla("left","#006699");
		   							pag.establecerFuente("Arial", "3", "white");
		   							pag.crearLabel("Nombre del Paciente");	
		   							pag.cerrarFuente();
		   						pag.cerrarEspacioFilaTabla();
		   						pag.abrirEspacioFilaTabla("left","#006699");
		   							pag.crearCampoSencillo("text", "1", "20", ""+rs.getString(2));
		   						pag.cerrarEspacioFilaTabla();	   				
		   					pag.cerrarFilaTabla();
		   					
		   					pag.abrirFilaTabla();
		   						pag.abrirEspacioFilaTabla("left","#006699");
		   							pag.establecerFuente("Arial", "3", "white");
		   							pag.crearLabel("Edad (Digite la edad en meses)");	
		   							pag.cerrarFuente();
		   						pag.cerrarEspacioFilaTabla();	   				
		   						pag.abrirEspacioFilaTabla("left","#006699");
		   							pag.crearCampoSencillo("text", "2", "20", ""+rs.getInt(3));
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
		   								if ((rs.getString(4)).equals("Macho"))
		   									pag.crearOpcionSelect("Macho", "Macho", true);
		   								else
		   								{		   									
		   									if ((rs.getString(4)).equals("Hembra"))
		   										pag.crearOpcionSelect("Hembra", "Hembra", true);
		   									else		   										
		   										pag.crearOpcionSelect("Hembra", "Hembra", false);
		   									pag.crearOpcionSelect("Macho", "Macho", false);
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
									consulta = "SELECT raz_codigo, raz_nombre FROM raza";
									ResultSet rs2 = conn.ejecutarQuery(consulta);			
									while(rs2.next())
									{
										if (rs.getInt(5)==rs2.getInt(1))
											pag.crearOpcionSelect(""+rs2.getString(2), ""+rs2.getString(1), true);
										else
											pag.crearOpcionSelect(""+rs2.getString(2), ""+rs2.getString(1), false);
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
		   							pag.crearCampoSencillo("text", "5", "20", ""+rs.getString(6));
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
		   		pag.establecerActionFormulario("1", "/SEDEGIC/servlet/FormularioActualizarPaciente");
		   		pag.cerrarFormulario();
		   		
		   		pag.cerrarPagina();
		   	}
		   	else
		   	{	
		   		pag.crearLabel("NO EXISTE");	
		   	}
	        salida.println(pag.retornarPagina());
	        
	    } 		
	    catch(SQLException e)
	    {}
   	}

}