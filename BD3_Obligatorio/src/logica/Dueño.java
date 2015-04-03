package logica;

import java.sql.Connection;
import java.util.ArrayList;

import excepciones.PersistenciaException;
import poolDeConexiones.IConexion;

public class Dueño {
	//Dueños (cédula INT, nombre VARCHAR(45), apellido VARCHAR(45))
	private int cedula;
	private String nombre;
	private String apellido;
	//private DAOMascotas mascotas;
	public Dueño(int cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		//this.mascotas = new DAOMascotas(this.cedula);
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/*public boolean tieneMascota(IConexion con, String apodo) throws PersistenciaException{
		
		return mascotas.member(con, apodo);
		
	}
	
	public void addMascota(IConexion con, Mascota masc) throws PersistenciaException{
		mascotas.insert(con, masc);
	}
	
	public ArrayList<Mascota> listarMascotas(IConexion con) throws PersistenciaException{
		return mascotas.listarMascotas(con);
	}
	
	public void borrarMascotas(IConexion con) throws PersistenciaException{
		mascotas.borrarMascotas(con);
	}
	
*/
}
