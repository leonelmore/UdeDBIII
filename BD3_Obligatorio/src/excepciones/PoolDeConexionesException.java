package excepciones;

import settings.LoadSettings;

public class PoolDeConexionesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private String mensaje;
	
	public PoolDeConexionesException(String unMensaje){
		//mensaje = unMensaje;
		super(unMensaje);
	}
	
	public String darMensaje(){
		return mensaje;
	}
	
	public PoolDeConexionesException(Exception unaExcepcion){
		if (LoadSettings.getData().debugMode)
		{
			mensaje = unaExcepcion.getStackTrace().toString();
		}
		else
		{
			mensaje = "Error en el pool de conexiones";
		}
	}*/
	public PoolDeConexionesException(String unMensaje){
		super("Error en el pool de conexiones: " + unMensaje);
	}
	
	public PoolDeConexionesException(String unMensaje, Exception unaCausa){
		super("Error en el pool de conexiones: " + unMensaje,unaCausa);
	}

}
