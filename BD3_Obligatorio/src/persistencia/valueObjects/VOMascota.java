package persistencia.valueObjects;

import java.io.Serializable;

public class VOMascota implements Serializable{
	
	private String apodo;
	private String raza;
	private int cedulaDue�o;
	public VOMascota(String apodo, String raza, int cedulaDue�o) {
		super();
		this.apodo = apodo;
		this.raza = raza;
		this.cedulaDue�o = cedulaDue�o;
	}
	public String getApodo() {
		return apodo;
	}
	public String getRaza() {
		return raza;
	}
	public int getCedulaDue�o() {
		return cedulaDue�o;
	}
	
	

}
