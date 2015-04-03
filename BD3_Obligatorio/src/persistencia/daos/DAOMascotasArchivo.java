package persistencia.daos;

import java.util.ArrayList;

import logica.Dueño;
import logica.Mascota;
import poolDeConexiones.ConexionArchivo;
import poolDeConexiones.IConexion;
import settings.LoadSettings;
import utilidades.manejoDeArchivos.LecturaEscrituraArchivosTXT;
import excepciones.ConfiguracionException;
import excepciones.PersistenciaException;

public class DAOMascotasArchivo implements IDAOMascotas {
	
	private int cedulaDueño;

	public void borrarMascotas(IConexion icon) throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		ArrayList<Mascota> ret = new ArrayList<Mascota>();
		String cedula = String.valueOf(this.cedulaDueño);
		try {
			for (String s : con.leerDirectorio(LoadSettings.getData().rutaRaiz+"Mascotas/")){
				String[] paraChequear = s.split("-");
				
				if (paraChequear.length == 2){
					String cedulaParaChequear = paraChequear[1].replace(".txt", "");
					if(cedulaParaChequear.equals(cedula)){
						con.borrarArchivo(LoadSettings.getData().rutaRaiz+"Mascotas/", s);
					}
				}
					
				
			}
		} catch (ConfiguracionException e) {
			throw new PersistenciaException(e.getMessage(),e);
		}
		

	}

	public int getCedulaDueño() throws PersistenciaException {
		return this.cedulaDueño;
	}

	public void insert(IConexion icon, Mascota m) throws PersistenciaException {
		//fifi-1234567.txt
		ConexionArchivo con = (ConexionArchivo) icon;
		if(!member(icon, m.getApodo())){
			String nombreArchivo = String.valueOf(m.getApodo())+"-"+String.valueOf(this.cedulaDueño)+".txt";
			String datos[] = new String[3];
			datos[2] = String.valueOf(this.cedulaDueño);
			datos[0] = m.getApodo(); datos[1] = m.getRaza();
			try {
				con.escribirDatos(LoadSettings.getData().rutaRaiz+"Mascotas/", nombreArchivo, datos);
			} catch (ConfiguracionException e) {
				throw new PersistenciaException(e.getMessage(),e);
			}
		}
		else{
			throw new PersistenciaException("Ya existe una mascota con este nombre.");
		}
		

	}

	public ArrayList<Mascota> listarMascotas(IConexion icon)
			throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		ArrayList<Mascota> ret = new ArrayList<Mascota>();
		String cedula = String.valueOf(this.cedulaDueño);
		try {
			for (String s : con.leerDirectorio(LoadSettings.getData().rutaRaiz+"Mascotas/")){
				String[] paraChequear = s.split("-");
				if (paraChequear.length == 2){
					String cedulaParaChequear = paraChequear[1].replace(".txt", "");
					if(cedulaParaChequear.equals(cedula)){
				
						ArrayList<String> datos = con.leerArchivo(LoadSettings.getData().rutaRaiz+"Mascotas/", s);
						if (datos.size() == 3){
							Object[] ds = datos.toArray();
							String apodo = (String) ds[0]; String raza = (String) ds[1];
							Mascota m = new Mascota(apodo, raza, this.cedulaDueño);
							ret.add(m);
						}
					}
				}
				
			}
		} catch (ConfiguracionException e) {
			throw new PersistenciaException(e.getMessage(),e);
		}
		return ret;
	}

	public boolean member(IConexion icon, String y) throws PersistenciaException {
		//fifi-1234567.txt
		ConexionArchivo con = (ConexionArchivo) icon;
		String ruta = "";
		try {
			ruta = LoadSettings.getData().rutaRaiz+"Mascotas/";
		} catch (ConfiguracionException e) {
			throw new PersistenciaException(e.getMessage(),e);
		}
		//String archivo = y+"-"+String.valueOf(this.cedulaDueño)+".txt";
		String archivo = y;
		return con.existeArchivoConNombreSubcadena(ruta, archivo);	
	}

	public void setCedulaDueño(int x) throws PersistenciaException {
		this.cedulaDueño = x;
	}
}
