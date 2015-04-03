package excepciones;

public class VentanaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaException(String unMensaje){
		super("Error en la capa gráfica: " + unMensaje);
	}
	
	public VentanaException(String unMensaje, Exception unaCausa){
		super("Error en la capa gráfica: " + unMensaje,unaCausa);
	}

}
