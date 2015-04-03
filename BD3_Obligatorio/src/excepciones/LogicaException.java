package excepciones;

import settings.LoadSettings;

public class LogicaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private String mensaje;
	
	public LogicaException(String unMensaje){
		mensaje = unMensaje;
	}
	
	public String darMensaje(){
		return mensaje;
	}
	
	public LogicaException(Exception unaExcepcion){
		if (LoadSettings.getData().debugMode)
		{
			mensaje = unaExcepcion.getStackTrace().toString();
		}
		else
		{
			mensaje = "Error en la capa lógica";
		}
	}*/
	public LogicaException(String unMensaje, Exception unaCausa){
		super("Error en la Logica: " + unMensaje, unaCausa);
	}
	
	public LogicaException(String unMensaje){
		super("Error en la Logica: " + unMensaje);
	}
}
