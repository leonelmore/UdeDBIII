package settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import excepciones.ConfiguracionException;

/*
 * Ejemplo de uso:
 *  String url = LoadSettings.getData.url;
 *  String password = LoadSettings.getData.password;
 */
public class LoadSettings {
	
	static LoadSettings instancia;
	
	public String url;
	public String urlBase;
	public String driver;
	public String user;
	public String password;
	public int tamanioPoolDeConexiones;
/*
	 * 
	 *(from java.sql.Connection)
	 *|-------------------------------------- 
	 *| Value | Constant_Field              
	 *|--------------------------------------
	 *| 0 	  | TRANSACTION_NONE
	 *| 2 	  | TRANSACTION_READ_COMMITTED
	 *| 1  	  | TRANSACTION_READ_UNCOMMITTED
	 *| 4 	  | TRANSACTION_REPEATABLE_READ
	 *| 8 	  | TRANSACTION_SERIALIZABLE
	 */
	public int nivelTransaccionalidadPoolDeConexiones;
	public boolean debugMode;
	public String metodoDePersistencia; //ya no es necesario luego de agregar nomFab
	public String rutaRaiz; //Usada para guardar cuando es tipo de persistencia Archivos
	public String ip;
	public String port;
	public String obj; //no es necesario creo.
	public String nomFab; //esta variable referencia al tipo de persistencia en realidad, no metodoDePersistencia
	//VARIABLE NUEVA, agrega una linea aqui
	
	public static LoadSettings getData() throws ConfiguracionException{
		if (instancia == null)
			instancia = new LoadSettings();
			
		return instancia;
	}
	
	private LoadSettings() throws ConfiguracionException{
			Properties p = new Properties();
			try {
				p.load(new FileInputStream("conf/config.properties"));
				
				//VARIABLE NUEVA, agrega una linea aqui
				url = p.getProperty("url");
				urlBase = p.getProperty("urlBase");
				driver = p.getProperty("driver");
				user = p.getProperty("user");
				password = p.getProperty("password");
				tamanioPoolDeConexiones = Integer.parseInt(p.getProperty("tamanioPoolDeConexiones"));
				nivelTransaccionalidadPoolDeConexiones = Integer.parseInt(p.getProperty("nivelTransaccionalidadPoolDeConexiones"));
				ip = p.getProperty("ipserver");
				port = p.getProperty("port");
				obj = p.getProperty("obj");
				debugMode = Boolean.parseBoolean(p.getProperty("debugMode"));
				metodoDePersistencia = p.getProperty("metodoDePersistencia");
				rutaRaiz = p.getProperty("rutaRaiz");
				nomFab = p.getProperty("nomFab");
			}
			catch (FileNotFoundException e){
				throw new ConfiguracionException("Error accediendo al archivo de configuracion.",e);
			}
			catch (NumberFormatException e){
				throw new ConfiguracionException("Error en el formato del archivo de configuracion.",e);
			}
			catch (Exception e){
				throw new ConfiguracionException("Ha ocurrido una excepcion generica.",e);				
			}
	}

}
