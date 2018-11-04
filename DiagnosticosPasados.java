
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Date;
import java.text.DateFormat;

public class DiagnosticosPasados extends HttpServlet
{

   String login = "";
   String password = "";
   String url = "jdbc:mysql://localhost/sedegic";

   Connection conn;
   ServletContext context;

//******************************************************************************
//Permite abrir la conexion haciendo uso del conector para mysql

   public void init(ServletConfig config) throws ServletException
   {
   		context = config.getServletContext();

      	try
      	{
         	Class.forName("org.gjt.mm.mysql.Driver").newInstance();
         	conn = DriverManager.getConnection(url,login,password);
         	conn.setAutoCommit(false);
        	// System.out.println("entro");
      	}
      	catch(Exception ex)
      	{ 
         	context.log(ex.toString());
      	}
   }
   
   public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   {
   	   	
   		PrintWriter salida=null;		
   		ResultSet rs;
   		String codigo, consulta, pagina, fecha, temp="", pacNombre="";
   		Date fec = new Date();
   		boolean termino = false, nuevoDia = true;
   		int cont=0;
		
		fecha = DateFormat.getDateTimeInstance().format(fec);
   		
   		codigo= resp1.getParameter("campo1");
   		
   		try
   		{
   		
	   		String nombrePac = "SELECT pac_nombre FROM paciente where pac_codigo='"+codigo+"'";
		    Statement stat = conn.createStatement();
		    ResultSet resp = stat.executeQuery(nombrePac);
		    resp.next();
		    pacNombre = resp.getString(1);
   		
   			consulta = "SELECT enfermedad.enf_nombre, diagnostico.dia_tipo, diagnostico.dia_fecha ";
   			consulta += "FROM diagnostico, enfermedad ";
   			consulta += "WHERE diagnostico.enf_codigo = enfermedad.enf_codigo ";
   			consulta += "AND diagnostico.pac_codigo = '"+codigo+"' ";
   			//consulta += "AND diagnostico.dia_estado='U'";   			   			
   			
   			Statement statement = conn.createStatement();
		    rs= statement.executeQuery(consulta);
		    
		    pagina = "<body bgcolor='#006699' text='#FFFFFF'>";
		    pagina+= "<br><strong><div align='top'>DIAGNÓSTICO GENERAL DEL PACIENTE</div></strong>";
		    pagina+= "<br>El paciente <b>"+pacNombre+"</b> con el código <b>"+codigo+"</b> presentó el siguiente diagnóstico";
		    while(rs.next())
		    {		    	
		    	if ((rs.getString(2).equals("a")) && (nuevoDia))
		    	{
		    		pagina += "<br><br>--------------"+rs.getString(3)+"-----------------------";
		    		nuevoDia=false;
		    		cont+=1;
		    	}
		    	else
		    	{
		    		if (!(rs.getString(2).equals("a")))
		    			nuevoDia=true;
		    	}
		    	pagina += "<br>"+rs.getString(1);
		    }
		    if (cont==0)
		    	pagina+= "<br><b>No existen diagnósticos anteriores</b>";
		    else
		    	pagina+= "<br><b>En total fueron "+cont+" diagnósticos en la base de datos</b>";
		    pagina+= "<form name='form1' method='post' action='/SEDEGIC/main.htm'>";
	        pagina+= "<input type='submit' name='Submit' value='Aceptar'>";
		    pagina+= "</body>";
		    
		    salida = resp2.getWriter();
	        salida.println(pagina); 
		    
   		}
   		catch(SQLException ex)
   		{}
   		catch(IOException ex)
   		{}
   	}
}