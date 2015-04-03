package poolDeConexiones;

import java.sql.Connection;

public class ConexionMySQL implements IConexion{

	private  Connection con;
	public ConexionMySQL(Connection unCon){
		con = unCon;
	}
	public Connection getConnection(){
		return con;
	}
}
