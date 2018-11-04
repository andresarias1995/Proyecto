
public class ConstruirPagina
{
	String pagina, numeroForm;
	int totalFilasTabla, totalColumnasTabla;
	boolean creadaTabla, creadaFila, creadoFormulario, creadaFuente, creadoCampoFila;
	/**Constructor*/
	public ConstruirPagina()
	{
		pagina="";
		numeroForm="";
		totalFilasTabla=0;
		totalColumnasTabla=0;
		creadaTabla=false;
		creadaFila=false;
		creadoCampoFila=false;
		creadoFormulario=false;
		creadaFuente=false;
	}

	/**
	* Método abrirEncabezado
	* Construye el encabezdo de una página html
	* @param nombrePagina Nombre que aparecerá en el título de la ventana de la página
	*/	
	public void abrirEncabezado(String nombrePagina, String tipo)
	{
		pagina+="<HTML>\n";
		pagina+="<HEAD>\n";
		pagina+="<TITLE>"+nombrePagina+"</TITLE>\n";
		pagina+="<META HTTP-EQUIV='Content-Type' CONTENT='"+tipo+"; charset=iso-8859-1'>\n";
		pagina+="<link href='../CSS/EXPLORER.CSS' rel='stylesheet' type='text/css'>\n";
	}

	/**
	* Método cerrarEncabezado
	* Cierra el encabezado de una página html
	*/	
	public void cerrarEncabezado(String color)
	{
		pagina+="</HEAD>\n";
		pagina+="<BODY bgColor='"+color+"'>\n";
	}		

	/**
	* Método cerrarPagina
	* Cierra el cuerpo y la página misma
	*/
	public void cerrarPagina()
	{
		pagina+="</BODY></HTML>\n";
	}

	/**
	* Método abrirTabla
	* Abre el formato para crear una tabla
	* @param filas Entero que indica el número de filas que contiene la tabla
	* 	 columnas Entero que indica el número de casillas que contiene cada fila
	*/
	public void abrirTabla(int filas, int columnas)
	{
		totalFilasTabla= filas;
		totalColumnasTabla= columnas;
		pagina+="<TABLE ALIGN='center' VALIGN='middle' WIDTH='90%'>\n";		
	}

	/**
	* Método cerrarTabla
	* Cierra la tabla
	*/
	public void cerrarTabla()
	{
		pagina+="</TABLE>\n";			
	}

	/**
	* Método abrirFilaTabla
	* Abre el formato para crear una fila de la tabla
	* @param filas Entero que indica el número de filas que contiene la tabla
	* 	 columnas Entero que indica el número de casillas que contiene cada fila
	*/
	public void abrirFilaTabla()
	{
		pagina+="<TR WIDTH='100%'>\n";			
	}
	public void cerrarFilaTabla()
	{
		pagina+="</TR>\n";				
	}

	public void abrirEspacioFilaTabla(String posicion, String colorFondo)
	{
		pagina+="<TD BGCOLOR='"+colorFondo+"' ALIGN='"+posicion+"'>\n";
	}

	public void cerrarEspacioFilaTabla()
	{
		pagina+="</TD>\n";	
	}

	public void establecerFuente(String fuente, String tamaFuente, String colorFuente)
	{
		pagina+="<FONT FACE='"+fuente+"' SIZE='"+tamaFuente+"' COLOR='"+colorFuente+"'>\n";	
	}
	public void cerrarFuente()
	{	
			pagina+="</FONT>\n";	
	}
	public void abrirFormulario(String numero)
	{
		pagina+="<FORM NAME='form"+numero+"' METHOD='post'>\n";	
	}
	public void cerrarFormulario()
	{
		pagina+="</FORM>\n";		
	}
	public void crearLabel(String valor)
	{
		pagina+="<LABEL>"+valor+"</LABEL></FONT>\n";
	}
	public void crearCampoSencillo(String tipo, String numero, String tama, String valorInicial)
	{
		if (Integer.parseInt(tama)>50)
			tama="50";
		pagina+="<INPUT TYPE='"+tipo+"' NAME='campo"+numero+"' SIZE='"+tama+"' VALUE='"+valorInicial+"' MAXLENGTH='50'>\n";	
	}
	public void crearCampoSencilloActivable(String tipo, String numero, String tama, String valorInicial)
	{
		if (Integer.parseInt(tama)>50)
			tama="50";
		pagina+="<INPUT TYPE='"+tipo+"' NAME='campo"+numero+"' SIZE='"+tama+"' DISABLED='true' VALUE='"+valorInicial+"' MAXLENGTH='50'>\n";
	}
	
	public void crearCampoComplejo(String tipo, String numero, String tama, String accion)
	{
		if (Integer.parseInt(tama)>50)
			tama="50";
		pagina+="<INPUT TYPE='"+tipo+"' NAME='campo"+numero+"' SIZE='"+tama+"' ONBLUR='"+accion+"' MAXLENGTH='50'>\n";
	}
	
	public void crearCampoComplejoTama(String tipo, String numero, String tama, String accion, String tam)
	{
		pagina+="<INPUT TYPE='"+tipo+"' NAME='campo"+numero+"' SIZE='"+tama+"' ONBLUR='"+accion+"' MAXLENGTH='"+tam+"'>\n";
	}

	public void crearCampoComplejoTamaV(String tipo, String numero, String tama, String accion, String tam, String valorInicial)
	{
		if (valorInicial!=null)
			pagina+="<INPUT TYPE='"+tipo+"' NAME='campo"+numero+"' SIZE='"+tama+"' VALUE='"+valorInicial+"' ONBLUR='"+accion+"' MAXLENGTH='"+tam+"'>\n";
		else
			pagina+="<INPUT TYPE='"+tipo+"' NAME='campo"+numero+"' SIZE='"+tama+"' ONBLUR='"+accion+"' MAXLENGTH='"+tam+"'>\n";
	}
	
	public void abrirSelect(String numero)
	{
		pagina+="<SELECT NAME='campo"+numero+"'>\n";
	}
	
	public void abrirSelectActivable(String numero)
	{
		pagina+="<SELECT NAME='campo"+numero+"' DISABLED='true'>\n";
	}
	
	public void cerrarSelect()
	{
		pagina+="</SELECT>\n";
	}
	
	public void crearOpcionSelect(String texto, String valor, boolean codigo)
	{
		if (codigo)
		{
			pagina+="<OPTION SELECTED VALUE='"+valor+"'>"+texto+"</OPTION>\n";
		}
		else
		{
			pagina+="<OPTION VALUE='"+valor+"'>"+texto+"</OPTION>\n";
		}
	}
	
	public void crearBoton(String tipo, String nombre, String accion)
	{		
			pagina+="<INPUT TYPE='"+tipo+"' VALUE='"+nombre+"' NAME='"+nombre+"' ID='"+nombre+"' ONCLICK='"+accion+"'>\n";
	}
	public void establecerActionBoton(String numFormulario, String nombre, String accion)
	{
		if (numFormulario.equals(""))
			pagina+="<SCRIPT>document.getElementById('"+nombre+"').onClick="+accion+"</SCRIPT>\n";
		else
			pagina+="<SCRIPT>document.form"+numFormulario+"."+nombre+".onClick="+accion+"</SCRIPT>\n";
	}
	public void crearScript(String contenido)
	{
		pagina+="<SCRIPT>"+contenido+"</SCRIPT>\n";
	}
	public void establecerActionFormulario(String numero, String accion)
	{
		pagina+="<SCRIPT>document.form"+numero+".action='"+accion+"'</SCRIPT>\n";
	}
	public void establecerTargetFormulario(String numero, String destino)
	{
		pagina+="<SCRIPT>document.form"+numero+".target="+destino+"</SCRIPT>\n";
	}	

	public void crearEnlace(String nombre, String texto, String vinculo)
	{
		pagina+="<A NAME='"+nombre+"' STYLE='text-decoration: none' HREF='"+vinculo+"'>"+texto+"</A>\n";
	}
	
	public void crearImagen(String ruta, String ancho, String alto)
	{
		pagina+="<img src="+ruta+" width="+ancho+" height="+alto+">";
	}
	
	public String retornarPagina()
	{
		return pagina;
	}

	public int retornarTama()
	{
		return pagina.length();
	}
}