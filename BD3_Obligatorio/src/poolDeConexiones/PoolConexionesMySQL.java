package poolDeConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import settings.LoadSettings;
import excepciones.ConfiguracionException;
import excepciones.PoolDeConexionesException;

public class PoolConexionesMySQL implements IPoolConexiones{
	
	private String driver, url, user, password;
	private int nivelTransaccionalidad;
	private IConexion[] conexiones;
	private int tamanio, creadas, tope;
	
	public PoolConexionesMySQL() throws PoolDeConexionesException {
		//Cargar del properties los datos de url, driver, user y password
		try {
			this.url = LoadSettings.getData().url;
		} catch (ConfiguracionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			this.driver = LoadSettings.getData().driver;
		} catch (ConfiguracionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		try {
			this.user = LoadSettings.getData().user;
		} catch (ConfiguracionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.password = LoadSettings.getData().password;
		} catch (ConfiguracionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
		}
		try {
			this.tamanio = LoadSettings.getData().tamanioPoolDeConexiones;
		} catch (ConfiguracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.creadas = 0;
		this.tope = 0;
		this.conexiones = new ConexionMySQL[tamanio];
		for (int i = 0; i < this.tamanio; i++){
			this.conexiones[i] = null;
		}
		try {
			this.nivelTransaccionalidad = LoadSettings.getData().nivelTransaccionalidadPoolDeConexiones;
		} catch (ConfiguracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public synchronized IConexion obtenerConexion(boolean update) throws PoolDeConexionesException {
		IConexion ret = null;
		Connection con;
		try{
			boolean tengoConexion = false;
			while (!tengoConexion){
				////if (creadas == 0){
				////	con = DriverManager.getConnection(url, user, password);
				////	ret = new ConexionMySQL(con);
				////	creadas++;
					////tope++;
				////	tengoConexion = true;
				////}else 
				if (tope > 0){
					ret = conexiones[tope-1];
					////conexiones[tope-1] = null;
					tope--;
					tengoConexion = true;
				} else if (creadas < tamanio){
					con = DriverManager.getConnection(url, user, password);
					ret = new ConexionMySQL(con);
					creadas++;
					tengoConexion = true;
				} else {
					try {	
						wait();
					} catch (InterruptedException e) {
						throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
					}
				}	
			}
		//	con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			//Connection.TRANSACTION_SERIALIZABLE
			((ConexionMySQL)ret).getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			//System.out.println("llega poolDeConexiones.");
			((ConexionMySQL)ret).getConnection().setAutoCommit(false);
		}
		catch (SQLException e) {
			throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
		}
		return ret;
	}

	public synchronized void liberarConexion(IConexion conexion, boolean hacerCommit) throws PoolDeConexionesException {
		// TODO Auto-generated method stub
		if (hacerCommit){
			try {
				((ConexionMySQL) conexion).getConnection().commit();
			} catch (SQLException e) {
				throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
			}
		} else {
			try {
				((ConexionMySQL) conexion).getConnection().rollback();
			} catch (SQLException e) {
				throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
			}
		}
		conexiones[tope] = ((ConexionMySQL)conexion);
		notify();
		tope++;
	}

}
