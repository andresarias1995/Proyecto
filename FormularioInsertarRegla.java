import java.sql.*;
import java.io.*;

public class FormularioInsertarRegla
{
	ConstruirPagina formulario=null;	
	Conecciones conn=null;
	String c0,c1,c2,c3,c4,c5,c6,c7,c8,c9,cR; 
	String enfermedad, tipoRegla, valorSintoma;
	
	public FormularioInsertarRegla()
	{
		formulario = new ConstruirPagina();		
		conn = new Conecciones();
		conn.obtenerConexion();	
	}
	
	public void recibirParametros(String campo0, String campo1, String campo2, String campo3, String campo4, String campo5, String campo6, String campo7, String campo8, String campo9, String campoR)
	{
		this.c0 = campo0;	
		this.c1 = campo1;
		this.c2 = campo2;
		this.c3 = campo3;
		this.c4 = campo4;
		this.c5 = campo5;
		this.c6 = campo6;
		this.c7 = campo7;
		this.c8 = campo8;
		this.c9 = campo9;
		this.cR = campoR;
	}
	
	public void construirFormulario()
	{
		String consulta="";
		ResultSet rs=null;
		try
		{
			formulario.abrirEncabezado("", "html/text");
			String script = "\nfunction enviar()";
			script += "\n{";			
			script += "\ndocument.form0.action='/SEDEGIC/servlet/Regla';";
			script += "\ndocument.form0.submit();";
			script += "\n}";	
			script += "\n";
			script += "\nfunction finalizarRegla()";
			script += "\n{";			
			script += "\ndocument.form0.action='/SEDEGIC/servlet/ValorSintoma';";
			script += "\ndocument.form0.submit();";
			script += "\n}";
			script += "\n";			
	
			formulario.crearScript(script);
			
			formulario.cerrarEncabezado("#006699");
			
			
			formulario.abrirFormulario("0");
			
			formulario.abrirTabla(1,1);			
				formulario.abrirFilaTabla();										
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Monotype Corsiva", "6","#FFFFFF");
						formulario.crearLabel("Inserción de Nuevas Reglas");
					formulario.cerrarEspacioFilaTabla();
					formulario.cerrarFuente();
				formulario.cerrarFilaTabla();			
								
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("right", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();								
								
				formulario.abrirFilaTabla();					
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Para insertar nuevas reglas a SEDEGIC, debe tener en cuenta que es completamente necesario");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("suministrar.<BR><BR>");
						formulario.cerrarFuente();
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();			
				formulario.abrirFilaTabla();										
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Para regla sencilla:</strong> La enfermedad que se va a evaluar, los sintomas que se deben tener en cuenta en la ");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("regla, los valores correspondientes a cada uno de los sintomas contemplados. Tenga en cuenta que el");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("grado de certeza de la regla depende directamente de la veracidad de la información brindada.<BR><BR>");							
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();			
				formulario.abrirFilaTabla();										
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Para regla compuesta:</strong> En esta opción se deben tener en cuenta los mismos datos de la opción anterior");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("más el dignóstico de otra enfermedad que hace parte de la misma regla.<BR><BR>");						
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();
				formulario.abrirFilaTabla();										
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Inicialmente debe introducir la enfermedad que  diagnostica el sintoma y su valor correspondiente.");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Cuando lo haga, introduzca el tipo de la regla y presione el botón contiguo.<BR>");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Si ya existen sintomas y valores  para la enfermedad que introdujo, automáticamente se carga el número de síntomas.<BR>");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Debe introducir el nuevo síntoma o elegir alguno de la lista y presionar el botón contigüo.<BR>");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Luego introduzca el nuevo valor o elija alguno de la lista y presionar el botón contigüo..<BR>");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Si el tipo de regla que introdujo depende de un diagnóstico de una enfermedad previo aparecerá un campo con las opciones posibles<BR>");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("elija la enfermedad relacionada y presione el último botón.<BR>");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("Por último aparece un botón para que todos los datos suministrados queden almacenados.<BR>");
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();								
				
				formulario.cerrarFuente();
			formulario.cerrarTabla();
				
			formulario.abrirTabla(1,1);			
			
//////////////////			///////////////////////////////////////
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				

				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Ingrese la nueva enfermedad que diganostica la regla:</strong>");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c1==null) || (c1.equals(" ")))						
							formulario.crearCampoSencillo("text", "0" , "90", ""+c0);						
						else
							formulario.crearCampoSencilloActivable("text", "0" , "90", ""+c0);
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
			
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Seleccione la enfermedad que diagnostica la regla:</strong>");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c0==null) || (c0.equals(" ")))
						{
							formulario.abrirSelect("1");
							enfermedad=c1;
						}
						else
							formulario.abrirSelectActivable("1");
							consulta="SELECT * FROM enfermedad";							
							rs=conn.ejecutarQuery(consulta);	
							formulario.crearOpcionSelect(" "," ",false);											
							while(rs.next()) 
							{
								if (rs.getString(1).equals(c1))				
									formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),true);
								else	
									formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),false);
							}						
						formulario.cerrarSelect();
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("center", "#006699");						
						formulario.crearBoton("button", "Aceptar Enfermedad", "javascript:enviar()");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Ingrese un nuevo tipo para la regla:</strong>");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c3==null) || (c3.equals(" ")))					
						{	
							formulario.crearCampoSencillo("text", "2" , "10", ""+c2);
						}
						else
							formulario.crearCampoSencilloActivable("text", "2" , "10", ""+c2);						
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Seleccione el tipo de la regla creada:</strong>");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c2==null) || (c2.equals(" ")))
						{
							formulario.abrirSelect("3");
							tipoRegla=c3;
						}
						else
							formulario.abrirSelectActivable("3");						
						consulta="SELECT DISTINCT regla.reg_tipo, tipoNombre.tip_nombre FROM regla, tipoNombre ";
						consulta+="WHERE regla.reg_tipo = tipoNombre.reg_tipo";
						rs=conn.ejecutarQuery(consulta);
						formulario.crearOpcionSelect(" "," ",false);	
						while(rs.next()) 
						{
							if (rs.getString(1).equals(c3))				
								formulario.crearOpcionSelect(rs.getString(1)+" - "+rs.getString(2),rs.getString(1),true);
							else	
								formulario.crearOpcionSelect(rs.getString(1)+" - "+rs.getString(2),rs.getString(1),false);
						}						
						formulario.cerrarSelect();						
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
								
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("center", "#006699");						
						formulario.crearBoton("button", "Aceptar Tipo", "javascript:enviar()");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");						
						formulario.crearLabel("<strong>Ingrese el número de síntomas de la regla:</strong>");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						boolean obtenido=false;
						if ((enfermedad!=null && tipoRegla!=null))
						{
							consulta="SELECT reg_gradoCerteza FROM regla WHERE reg_tipo='"+tipoRegla+"' AND enf_codigo="+enfermedad+"";
	   						rs=conn.ejecutarQuery(consulta);
	   						if ((rs!=null) && (rs.next()))
	   						{
	   							c4=rs.getString(1);
	   							obtenido=true;
	   						}
						}
						int num;
						if (c4!=null && obtenido)  
							if (!c4.equals(" "))
								num=Integer.parseInt(c4)+1;
							else
								num=1;
						else
							num=1;
						formulario.crearCampoSencillo("text", "4" , "10", ""+num);
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("center", "#006699");						
						formulario.crearBoton("button", "Aceptar número de síntomas", "javascript:enviar()");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
////////////////////////////////////////
			
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
			
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Ingrese el nombre del nuevo síntoma de la nueva regla:</strong>");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c6==null) || (c6.equals(" ")))
							formulario.crearCampoSencillo("text", "5" , "90", ""+c5);
						else
							formulario.crearCampoSencilloActivable("text", "5" , "90", ""+c5);
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
						
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
								
				formulario.abrirFilaTabla();										
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Seleccione un síntoma para la nueva regla:</strong>");						
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
			
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");						
						if ((c5==null) || (c5.equals(" ")))
							formulario.abrirSelect("6");
						else
							formulario.abrirSelectActivable("6");
						
							consulta="SELECT * FROM sintoma";							
							rs=conn.ejecutarQuery(consulta);										
							formulario.crearOpcionSelect(" "," ",false);	
							while(rs.next()) 
							{	
								if (rs.getString(1).equals(c6))				
									formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),true);
								else	
									formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),false);
							}																		
						formulario.cerrarSelect();							
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();			
			
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("center", "#006699");						
						formulario.crearBoton("button", "Aceptar Síntoma", "javascript:enviar()");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();										
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Ingrese un nuevo valor para síntoma elegido o seleccionado:</strong>");						
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c8==null) || (c8.equals(" ")))
						{
							formulario.crearCampoSencillo("text", "7" , "90", ""+c7);
							valorSintoma=c7;
						}
						else
							formulario.crearCampoSencilloActivable("text", "7" , "90", ""+c7);
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();
			
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
						formulario.crearLabel("<strong>Seleccione un valor para el síntoma elegido o seleccionado:</strong>");
					formulario.cerrarEspacioFilaTabla();					
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("left", "#006699");
						if ((c7==null) || (c7.equals(" ")))
							formulario.abrirSelect("8");
						else
							formulario.abrirSelectActivable("8");
							consulta="SELECT * FROM valor";							
							rs=conn.ejecutarQuery(consulta);							
							formulario.crearOpcionSelect(" "," ",false);												
							while(rs.next()) 
							{					
								if (rs.getString(1).equals(c8))	
								{			
									formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),true);
									valorSintoma=c8;
								}
								else	
									formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),false);
							}											
						formulario.cerrarSelect();													
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				///////////////////// EMPIEZA				
				if (c3!=null && c3.equals("c"))
				{
					formulario.abrirFilaTabla();
						formulario.abrirEspacioFilaTabla("left", "#006699");
							formulario.establecerFuente("Arial, Helvetica, sans-serif", "3","#FFFFFF");
							formulario.crearLabel("<strong>Seleccione la enfermedad que debe ser previamente diagnosticada:</strong>");
						formulario.cerrarEspacioFilaTabla();					
					formulario.cerrarFilaTabla();
					
					
					formulario.abrirFilaTabla();
						formulario.abrirEspacioFilaTabla("left", "#006699");
							formulario.abrirSelect("9");							
								consulta="SELECT * FROM enfermedad where enf_nombre like '%Diarrea Intestino%'";
								rs=conn.ejecutarQuery(consulta);							
								formulario.crearOpcionSelect(" "," ",false);												
								while(rs.next()) 
								{					
									if (rs.getString(1).equals(c9))				
										formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),true);
									else	
										formulario.crearOpcionSelect(rs.getString(2),rs.getString(1),false);
								}											
							formulario.cerrarSelect();													
						formulario.cerrarEspacioFilaTabla();
					formulario.cerrarFilaTabla();
				}
				//////////////////// CIERRA
				
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
				
				formulario.abrirFilaTabla();
					formulario.abrirEspacioFilaTabla("center", "#006699");						
						formulario.crearBoton("button", "Aceptar Valor", "javascript:enviar()");
					formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();				
			
				formulario.abrirFilaTabla();
				formulario.abrirEspacioFilaTabla("left", "#006699");
				formulario.crearLabel("<BR>");
				formulario.cerrarEspacioFilaTabla();
				formulario.cerrarFilaTabla();
			
															
			formulario.cerrarFuente();
			formulario.cerrarTabla();
			
			if (valorSintoma!=null && !valorSintoma.equals(" "))
			{
				formulario.abrirTabla(1,1);
					formulario.abrirFilaTabla();					
						formulario.abrirEspacioFilaTabla("left", "#006699");
							formulario.crearBoton("button", "Finalizar Regla", "javascript:finalizarRegla()");
						formulario.cerrarEspacioFilaTabla();
					formulario.cerrarTabla();
				formulario.cerrarTabla();
			}
			
			formulario.crearCampoSencillo("hidden", "R" , "30", ""+cR); //Campo que contiene la regla ingresada			
						
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