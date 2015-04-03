package persistencia.valueObjects;

import java.io.Serializable;

public class VODue�o implements Serializable{
	
	private int cedula;
	private String nombre;
	private String apellido;
	public VODue�o(int cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public int getCedula() {
		return cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	
	

}
