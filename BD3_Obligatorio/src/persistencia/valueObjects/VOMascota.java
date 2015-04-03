package persistencia.valueObjects;

import java.io.Serializable;

public class VOMascota implements Serializable{
	
	private String apodo;
	private String raza;
	private int cedulaDueño;
	public VOMascota(String apodo, String raza, int cedulaDueño) {
		super();
		this.apodo = apodo;
		this.raza = raza;
		this.cedulaDueño = cedulaDueño;
	}
	public String getApodo() {
		return apodo;
	}
	public String getRaza() {
		return raza;
	}
	public int getCedulaDueño() {
		return cedulaDueño;
	}
	
	

}
