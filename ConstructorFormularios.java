import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ConstructorFormularios
{
   	Conecciones conn=null;   
   	ResultSet rs=null, rs1=null, rs2=null;	
   	ConstruirPagina formulario=null;
   	String tipo, servletAInvocar, pac_codigo;
   	
	public ConstructorFormularios()
	{
		conn = new Conecciones();
		conn.obtenerConexion();	
		formulario = new ConstruirPagina();
	}
	
	public void ConstruirFormularioExamenFisico(String tipo, String pac_codigo, String servletAInvocar)
	{	
		int contCampos=1;
		this.tipo=tipo;
		this.pac_codigo=pac_codigo;
		this.servletAInvocar=servletAInvocar;		
	
		try
		{
			formulario.abrirEncabezado("", "html/text");
			formulario.cerrarEncabezado("#006699");
			
			formulario.abrirFormulario("0");
			
			formulario.abrirTabla(1,1);
			
				formulario.crearCampoSencillo("hidden", ""+contCampos , "5", ""+tipo);
				contCampos+=1;
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("center", "#006699");
						formulario.crearImagen("../imagenes1/goldenretriever.jpg", "74", "107");
					formulario.cerrarEspacioFilaTabla();				
					formulario.abrirEspacioFilaTabla("center", "#006699");
						formulario.abrirSelect(""+contCampos);
							contCampos+=1;
							String consultaPaciente="SELECT pac_codigo, pac_nombre FROM paciente";
							rs1=conn.ejecutarQuery(consultaPaciente);
						while(rs1.next()) 
						{					
							if (rs1.getString(1).equals(pac_codigo))
								formulario.crearOpcionSelect(rs1.getString(2),rs1.getString(1),true);
							/*else
								formulario.crearOpcionSelect(rs1.getString(2),rs1.getString(1),false);*/
						}											
						formulario.cerrarSelect();											
					formulario.cerrarEspacioFilaTabla();					
	
				formulario.cerrarFilaTabla();		
			formulario.cerrarTabla();

			String consultaTitulo = "SELECT reg_tipo,tip_nombre FROM tipoNombre WHERE reg_tipo LIKE '%"+tipo+"%'";
			rs1 = conn.ejecutarQuery(consultaTitulo);
			while(rs1.next())
			{						
				formulario.abrirTabla(1,1);
					formulario.abrirFilaTabla();					
						formulario.abrirEspacioFilaTabla("left", "#006699");
							formulario.establecerFuente("Monotype Corsiva", "5","#FFFFFF");
							formulario.crearLabel("<B><i>"+rs1.getString(2)+"</i></B>");
							formulario.cerrarFuente();
						formulario.cerrarEspacioFilaTabla();
						formulario.abrirEspacioFilaTabla("center", "#006699");
					//		formulario.crearImagen("../imagenes1/goldenretriever.jpg", "74", "107");
						formulario.cerrarEspacioFilaTabla();					
					formulario.cerrarFilaTabla();		

				//Consulta para llenar los label de los sintomas segun la base de datos
				String consulta = "SELECT DISTINCT sintoma.sin_nombre, sintoma.sin_codigo FROM sintoma, regla, valorSintoma ";
				consulta+="WHERE regla.reg_tipo='"+rs1.getString(1)+"' AND regla.reg_codigo=valorSintoma.reg_codigo ";
				consulta+="AND valorSintoma.sin_codigo=sintoma.sin_codigo ORDER BY sintoma.sin_codigo";		
				rs = conn.ejecutarQuery(consulta);	//	salida.println(consulta);						
							
				while(rs.next())
				{
				//	if (contCampos%2==0)								
						formulario.abrirFilaTabla();
						
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial", "3","#FFFFFF");
						formulario.crearLabel(""+rs.getString(1));
						formulario.cerrarFuente();
					formulario.cerrarEspacioFilaTabla();		
					
					formulario.abrirEspacioFilaTabla("left", "#006699");								
						//Consulta para llenar los selectOption segun la base de datos
						formulario.abrirSelect(""+contCampos);
							consulta="SELECT DISTINCT valor.val_nombre, valor.val_codigo FROM valor, valorSintoma ";
							consulta+="WHERE valorSintoma.sin_codigo = '"+rs.getString(2)+"' ";
							consulta+="AND valorSintoma.val_codigo = valor.val_codigo";
							rs2=conn.ejecutarQuery(consulta);											
						while(rs2.next()) 
						{					
							formulario.crearOpcionSelect(rs2.getString(1),rs2.getString(2),false);
						}											
						formulario.cerrarSelect();											
					formulario.cerrarEspacioFilaTabla();
					contCampos+=1;
				//	if (contCampos%2==0)
						formulario.cerrarFilaTabla();					
				}									
			//	formulario.cerrarTabla();	
			}	
			formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("right", "#006699");
					formulario.crearLabel("");	
				formulario.cerrarEspacioFilaTabla();			
				formulario.abrirEspacioFilaTabla("right", "#006699");
					formulario.crearLabel("");	
				formulario.cerrarEspacioFilaTabla();			
			formulario.cerrarFilaTabla();
			formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("right", "#006699");
					formulario.crearBoton("submit", "Diagnosticar", "");
				formulario.cerrarEspacioFilaTabla();			
				formulario.abrirEspacioFilaTabla("left", "#006699");
					formulario.crearBoton("submit", "Salir", "");
				formulario.cerrarEspacioFilaTabla();
			formulario.cerrarFilaTabla();
			formulario.cerrarTabla();
			formulario.establecerActionFormulario("0",""+servletAInvocar);
			formulario.crearCampoSencillo("hidden", "0" , "5", ""+contCampos);						
			formulario.cerrarFormulario();
			formulario.cerrarPagina();
		}
		catch(SQLException e)
		{}	
	}	
	
	public String devolverFormulario()
	{
		return formulario.retornarPagina();
	}
}