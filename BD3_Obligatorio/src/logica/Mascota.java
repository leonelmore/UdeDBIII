package logica;

public class Mascota {
	
	private String apodo;
	private String raza;
	private int cedulaDueño;
	public Mascota(String apodo, String raza, int cedulaDueño) {
		super();
		this.apodo = apodo;
		this.raza = raza;
		this.cedulaDueño = cedulaDueño;
	}
	public String getApodo() {
		return apodo;
	}
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public int getCedulaDueño() {
		return cedulaDueño;
	}
	public void setCedulaDueño(int cedulaDueño) {
		this.cedulaDueño = cedulaDueño;
	}
	

}
