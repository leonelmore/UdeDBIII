package excepciones;

import settings.LoadSettings;

public class PersistenciaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private String mensaje;
	
	public PersistenciaException(String unMensaje){
		mensaje = unMensaje;
	}
	
	public String darMensaje(){
		return mensaje;
	}
	
		public PersistenciaException(Exception unaExcepcion){
		if (LoadSettings.getData().debugMode)
		{
			mensaje = unaExcepcion.getStackTrace().toString();
		}
		else
		{
			mensaje = "Error de persistencia de datos";
		}
	}*/
	public PersistenciaException(String unMensaje, Exception unaCausa){
		super("Error en la persistencia: " + unMensaje, unaCausa);
	}
	
	public PersistenciaException(String unMensaje){
		super("Error en la persistencia: " + unMensaje);
	}
	
}
