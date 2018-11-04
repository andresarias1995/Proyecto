import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class ListarReglas extends HttpServlet
{
	Conecciones conn;
	ResultSet rs=null, rs2=null, rs3=null;
	
	public void init(ServletConfig config) throws ServletException
	{
		conn=new Conecciones();
		conn.obtenerConexion();			
	}
	
	public void doPost(HttpServletRequest resp1, HttpServletResponse resp2)
	{
		PrintWriter salida;	
    	String pagina="", consulta="", consulta2="", consulta3="", temp2="";
    	int temp=0;
    	
		consulta = "SELECT sintoma.sin_nombre, valor.val_nombre, enfermedad.enf_nombre, valorSintoma.reg_codigo, tipoNombre.tip_nombre, valorSintoma.vas_codigo, regla.reg_tipo ";
		consulta += "FROM sintoma, valor, valorSintoma, regla, enfermedad, tipoNombre ";
		consulta += "WHERE sintoma.sin_codigo = valorSintoma.sin_codigo ";
		consulta += "AND valor.val_codigo = valorSintoma.val_codigo ";
		consulta += "AND regla.reg_codigo = valorSintoma.reg_codigo ";
		consulta += "AND regla.enf_codigo = enfermedad.enf_codigo ";
		consulta += "AND tipoNombre.reg_tipo = regla.reg_tipo ";
		consulta += "AND valorSintoma.reg_codigo > 1 ";
		consulta += "ORDER BY tipoNombre.tip_nombre, valorSintoma.reg_codigo"; 
				
		consulta2 = "SELECT DISTINCT reg_codigo FROM valorSintoma ";
		consulta2 += "WHERE valorSintoma.reg_codigo > 1 ";
		consulta2 += "ORDER BY reg_codigo";
				    	
    	pagina += "<html><head>";
    	pagina += "\n<script>";
    	pagina += "\nfunction cancelar()";
    	pagina += "\n{";
    	pagina += "\ndocument.form1.action='/SEDEGIC/mainAdmin.htm';";
    	pagina += "\ndocument.form1.submit();";
    	pagina += "\n}";
    	pagina += "\n</script>";
    	pagina += "</head>";
    	pagina += "<body bgcolor='#006699' text='#FFFFFF'>";    
    	pagina += "<br><strong><div align='top'>LISTADO DE REGLAS EXISTENTES</div></strong></td>";
    	pagina += "<form name='form1' method='post' action='/SEDEGIC/servlet/EliminarRegla'>";
    	    	   	
    	try
    	{
    		rs2=conn.ejecutarQuery(consulta2);
	    	pagina += "<br><br>Elija la regla que desea eliminar <select name=campo1>";	 
	    	pagina += "<OPTION VALUE='0'></OPTION>";   	
	    	while (rs2.next())
	    		pagina += "<OPTION VALUE='"+rs2.getInt(1)+"'>"+rs2.getInt(1)+"</OPTION>";
	    	pagina += "</select>";
	    	pagina += "<input type='submit' value='Eliminar'>";
	    	pagina += "<input type='button' value='Cancelar' onClick='javascript:cancelar()'>";
	    	pagina += "</form>";
	    	
	    	rs=conn.ejecutarQuery(consulta);
	    	while (rs.next())
	    	{
	    		if (!temp2.equals(rs.getString(5)))
	    		{	    			
	    			pagina += "<br><br><b>REGLAS DE "+rs.getString(5)+"</b>";	    		
	    		}
	    		if (temp!=rs.getInt(4))	    		
	    			pagina += "<br><br><b>Regla "+rs.getInt(4)+"</b> El paciente tiene <b>"+rs.getString(3)+"</b>";
	    		
	    		if ((rs.getString(7).equals("c"))&&(temp!=rs.getInt(4)))
	    		{
	    			consulta3 = "SELECT enfermedad.enf_nombre FROM sintomaEnfermedad, enfermedad ";
					consulta3 += "WHERE sintomaEnfermedad.vas_codigo = '"+rs.getString(6)+"' ";
					consulta3 += "AND enfermedad.enf_codigo=sintomaEnfermedad.enf_codigo";
					rs3=conn.ejecutarQuery(consulta3);
					if (rs3.next())
						pagina += "<br>Si presentó "+rs3.getString(1);
	    		}
	    		
	    		pagina += "<br>Si "+rs.getString(1)+" = "+rs.getString(2);
	    			    			    			    		
	    		temp=rs.getInt(4);
	    		temp2=rs.getString(5);
	    		
	    	}	    	
	    	pagina += "<body>";	    	
	    	salida = resp2.getWriter();
	        salida.println(pagina); 
	    }
	    catch(IOException e)
	    {}
	    catch(SQLException e)
	    {}
	}	
}