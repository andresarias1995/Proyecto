import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Hashtable;

//******************************************************************************
// Clase que permite obtener los datos de la consulta de examen fisico e insertar 
// en el sistema los sistomas del paciente.

public class Historia extends HttpServlet
{
	//RegistroUsuarios registro = new RegistroUsuarios(); // Instancia la clase	
 	String login = "";
   	String password = "";
   	String url = "jdbc:mysql://localhost/sedegic";

   	Connection conn;
   	ServletContext context;
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

//******************************************************************************
// Permite pintar o llamar los formularios requeridos por las acciones de los 
//usuarios

   	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
   	{
   	   	
   		PrintWriter salida;		
   		String arregloCampos[], pagina="";
      	String tipo="", codigo="", info;
      	int i, numCampos;

		numCampos = Integer.parseInt(resp1.getParameter("campo0"));
      	tipo = resp1.getParameter("campo1");
      	codigo = resp1.getParameter("campo2");	
      	arregloCampos = new String[numCampos];
      	
      	for(int j=3; j<numCampos; j++)
      	{
      		arregloCampos[j-3]=resp1.getParameter("campo"+j);
      	}
     	
      	try
      	{          	 
      		int cont;
						
			Statement statement = conn.createStatement();
			
			info = "select max(his_codigo) from historia";
			ResultSet r = statement.executeQuery(info);
			r.next();
			if (r.getString(1)!=null)
				cont=Integer.parseInt(r.getString(1))+1;
			else
				cont=0;
			
			String consulta = "SELECT DISTINCT sintoma.sin_codigo FROM sintoma, regla, valorSintoma ";
			consulta+="WHERE regla.reg_tipo LIKE '%"+tipo+"%' AND regla.reg_codigo=valorSintoma.reg_codigo ";
			consulta+="AND valorSintoma.sin_codigo=sintoma.sin_codigo ORDER BY regla.reg_tipo, sintoma.sin_codigo";
			r = statement.executeQuery(consulta);		
			r.next();		
			for (int j=0; j<numCampos-3; j++)
			{				
				info = "INSERT INTO historia VALUES";
	        	info += "(0,"+cont+","+arregloCampos[j]+","+r.getString(1)+","+codigo+")";	        	
    	    	i = statement.executeUpdate(info);
    	    	r.next();
			}        	 	

	 	    pagina+="<form name='form' method='post' action='/SEDEGIC/servlet/MotorDeInferencia'>";
	        pagina+="<input type='hidden' name='codigo' value='"+codigo+"'>";        
	        pagina+="<input type='hidden' name='tipo' value='"+tipo+"'>";
	        pagina+="<input type='hidden' name='cont' value='"+cont+"'>";	    
	        pagina+="</form>";
	        pagina+="<script>document.form.submit()</script>";      	       	
	       	
	       	salida = resp2.getWriter();	       	
        	salida.println(pagina);        	
	    }
        catch(SQLException e)
        {}
        
        catch(IOException ex)
        {}   	   	 
   	}
  }