package excepciones;

import settings.LoadSettings;

public class ConfiguracionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String mensaje;
	
	//public ConfiguracionException(String unMensaje){
		//mensaje = unMensaje;
	//}
	
	//public String darMensaje(){
		//return mensaje;
	//}
	public ConfiguracionException(String unMensaje){
		super("Error en la configuracion: " + unMensaje);
	}
	
	public ConfiguracionException(String unMensaje, Exception excepcionCausa)
	{
		super("Error en la configuracion: " + unMensaje, excepcionCausa);
	}
	
	/*public ConfiguracionException(Exception unaExcepcion){
		if (LoadSettings.getData().debugMode)
		{
			mensaje = unaExcepcion.getStackTrace().toString();
		}
		else
		{
			mensaje = "Error cargando la configuración";
		}
	}*/
		
}
