package poolDeConexiones;

import java.util.ArrayList;

import excepciones.PersistenciaException;
import utilidades.manejoDeArchivos.LecturaEscrituraArchivosTXT;

public class ConexionArchivo implements IConexion {
	
	public void escribirDatos(String ruta, String nombreArchivo, String[] datos) throws PersistenciaException{
		
		LecturaEscrituraArchivosTXT.escribirDatos(ruta, nombreArchivo, datos);
		
	}
	
	public void sobreescribirDatos(String ruta, String nombreArchivo, String[] datos) throws PersistenciaException{
		
		LecturaEscrituraArchivosTXT.sobreescribirDatos(ruta, nombreArchivo, datos);
		
	}
	
	public boolean existeUnArchivoEnDirectorio(String dir, String arch){
		
		return LecturaEscrituraArchivosTXT.existeUnArchivoEnDirectorio(dir, arch);
	}
	
	public static ArrayList<String> leerArchivo(String dir, String arch) throws PersistenciaException{
		
		return LecturaEscrituraArchivosTXT.leerArchivo(dir, arch);
		
	}
	
	public void borrarArchivo(String ruta, String archivo){
		
		LecturaEscrituraArchivosTXT.borrarArchivo(ruta, archivo);
	}
	
	public static ArrayList<String> leerDirectorio(String ruta){
		
		return LecturaEscrituraArchivosTXT.leerDirectorio(ruta);
		
	}
	
	public static boolean existeArchivoConNombreSubcadena(String dir, String subcadena){
		return LecturaEscrituraArchivosTXT.existeArchivoConNombreSubcadena(dir, subcadena);
	}

}
