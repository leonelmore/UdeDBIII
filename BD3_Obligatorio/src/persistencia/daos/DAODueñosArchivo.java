package persistencia.daos;

import java.util.ArrayList;

import logica.Due�o;
import poolDeConexiones.ConexionArchivo;
import poolDeConexiones.IConexion;
import settings.LoadSettings;
import excepciones.ConfiguracionException;
import excepciones.PersistenciaException;

public class DAODue�osArchivo implements IDAODue�os {

	public void delete(IConexion icon, int cedula) throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		String ci = String.valueOf(cedula);
		if (member(con, cedula)){
			String archivo = String.valueOf(cedula)+".txt";
			try {
				con.borrarArchivo(LoadSettings.getData().rutaRaiz+"Due�os/", archivo);
			} catch (ConfiguracionException e) {
				throw new PersistenciaException(e.getMessage(),e);
			}
		}
	}

	public Due�o find(IConexion icon, int cedula) throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		String ci = String.valueOf(cedula);
		Due�o d = null;
		ArrayList<String> datos = new ArrayList<String>();
		
			if (member(con, cedula)){
				String s = String.valueOf(cedula)+".txt";
				
				try {
					datos = con.leerArchivo(LoadSettings.getData().rutaRaiz+"Due�os/", s);
				} catch (ConfiguracionException e) {
					throw new PersistenciaException(e.getMessage(),e);
				}
				if (datos.size() == 3){
					Object[] ds = datos.toArray();
					
					String nombre = (String) ds[1]; String apellido = (String) ds[2];
					d = new Due�o(cedula, nombre, apellido);
					
				}
			}
		
		//return con.existeUnArchivoEnDirectorio(LoadSettings.getData().rutaRaiz+"Due�os/", ci+".txt");
		
		return d;
	}

	public void insert(IConexion icon, Due�o y) throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		if (!member(con, y.getCedula())){
			//public static void escribirDatos(String ruta, String nombreArchivo, String[] datos){
			String nombreArchivo = String.valueOf(y.getCedula())+".txt";
			String datos[] = new String[3];
			datos[0] = String.valueOf(y.getCedula());
			datos[1] = y.getNombre(); datos[2] = y.getApellido();
			try {
				con.escribirDatos(LoadSettings.getData().rutaRaiz+"Due�os/", nombreArchivo, datos);
			} catch (ConfiguracionException e) {
				throw new PersistenciaException(e.getMessage(),e);
			}
		}
		else{
			throw new PersistenciaException("Este due�o ya existe.");
		}

	}

	public ArrayList<Due�o> listarDue�os(IConexion icon)
			throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		ArrayList<Due�o> ret = new ArrayList<Due�o>();
		try {
			for (String s : con.leerDirectorio(LoadSettings.getData().rutaRaiz+"Due�os/")){
				
				ArrayList<String> datos = con.leerArchivo(LoadSettings.getData().rutaRaiz+"Due�os/", s);
				if (datos.size() == 3){
					Object[] ds = datos.toArray();
					int cedula = Integer.parseInt((String) ds[0]);
					String nombre = (String) ds[1]; String apellido = (String) ds[2];
					Due�o d = new Due�o(cedula, nombre, apellido);
					ret.add(d);
				}
				
			}
		} catch (NumberFormatException e) {
			throw new PersistenciaException("Error de formato de n�mero listando los due�os.",e);
		} catch (ConfiguracionException e) {
			throw new PersistenciaException(e.getMessage(),e);
		}
		
		return ret;
	}

	public boolean member(IConexion icon, int cedula) throws PersistenciaException {
		ConexionArchivo con = (ConexionArchivo) icon;
		String ci = String.valueOf(cedula);
		boolean valorRetorno = false;
		try {
			valorRetorno = con.existeUnArchivoEnDirectorio(LoadSettings.getData().rutaRaiz+"Due�os/", ci+".txt");
		} catch (ConfiguracionException e) {
			throw new PersistenciaException(e.getMessage(),e);
		}
		return valorRetorno;
	}

}
