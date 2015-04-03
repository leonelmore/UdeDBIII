package utilidades.manejoDeArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import excepciones.PersistenciaException;
import persistencia.valueObjects.VODueño;

public class LecturaEscrituraArchivosTXT {

	

	
	/**
	 * Escribe cada dato almacenado en datos
	 * en una línea del archivo ruta/nombreArchivo siempre y cuando
	 * el archivo no exista.
	 * @param ruta
	 * @param nombreArchivo
	 * @param datos
	 * @throws PersistenciaException 
	 */
	public static void escribirDatos(String ruta, String nombreArchivo, String[] datos) throws PersistenciaException{
		
		
		 FileWriter fichero = null;
		 PrintWriter pw = null;
	        try
	        {
	        	
	            
	            if (LecturaEscrituraArchivosTXT.existeUnArchivoEnDirectorio(ruta, nombreArchivo)){
	            	  System.out.println("El archivo " + ruta + nombreArchivo + " existe");
	            }
	            	else
	            	{
	            		fichero = new FileWriter(ruta+nombreArchivo);
			            pw = new PrintWriter(fichero);
			            for (String s : datos){
			            	 pw.println(s);
			            }

	            	}
	 
	        } catch (IOException e) {
				throw new PersistenciaException("Error guardando datos.",e);
			} finally {
	           if (fichero != null)
				try {
					fichero.close();
				} catch (IOException e) {
					throw new PersistenciaException("Error guardando datos.",e);
				}
	        }
		
	}
	/**
	 * Escribe cada dato almacenado en datos
	 * en una línea del archivo ruta/nombreArchivo.
	 * @param ruta
	 * @param nombreArchivo
	 * @param datos
	 * @throws PersistenciaException 
	 */
	public static void sobreescribirDatos(String ruta, String nombreArchivo, String[] datos) throws PersistenciaException{
		
		
		 FileWriter fichero = null;
		 PrintWriter pw = null;
	        try
	        {

        		fichero = new FileWriter(ruta+nombreArchivo);
	            pw = new PrintWriter(fichero);
	            for (String s : datos){
	            	 pw.println(s);
	            }

	 
	        } catch (IOException e) {
	        	throw new PersistenciaException("Error guardando datos.",e);
	        } finally {
	           try {
	           if (fichero != null)
	              fichero.close();
	           } catch (IOException e2) {
	        	   throw new PersistenciaException("Error guardando datos.",e2);
	           }
	        }
		
	}
	
	
	
	/**
	 * Retorna true si el archivo arch existe en el directorio dir
	 * @param dir
	 * @param arch
	 * @return
	 */
	public static boolean existeUnArchivoEnDirectorio(String dir, String arch){
		
		File f = new File(dir);
		boolean encontre = false;
		if (f.exists()){
			File[] archivos = f.listFiles();
			for (int i = 0 ; i < archivos.length && !encontre; i++){
			 // System.out.println(archivos[i].getName());
			  encontre = arch.equals(archivos[i].getName());
			}
		}
		
		
		return encontre;
		
	}
	
	/**
	 * Retorna true si existe un archivo con nombre conteniendo la cadena subcadena
	 * @param dir
	 * @param subcadena
	 * @return
	 */
	public static boolean existeArchivoConNombreSubcadena(String dir, String subcadena){
		boolean encontre = false;
		File f = new File(dir);
		if (f.exists()){
			File[] archivos = f.listFiles();
			for (int i = 0 ; i < archivos.length && !encontre; i++){
			  encontre = archivos[i].getName().contains(subcadena);
			}
		}
		return encontre;
	}
	
	/**
	 * Returna una lista con toda la información del archivo.
	 * @param dir
	 * @param arch
	 * @return
	 * @throws PersistenciaException 
	 */
	public static ArrayList<String> leerArchivo(String dir, String arch) throws PersistenciaException{
		ArrayList<String> ret = new ArrayList<String>();
		 File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;
		if (LecturaEscrituraArchivosTXT.existeUnArchivoEnDirectorio(dir, arch)){
			archivo = new File (dir+arch);
	        try {
				fr = new FileReader (archivo);
			
	        br = new BufferedReader(fr);
	        String linea = "";
	       
			while((linea=br.readLine()) != null)
				    ret.add(linea);
	        }
			 catch (IOException e) {
				 throw new PersistenciaException("Error leyendo datos.",e);
			}
	        finally{

	            try{                    
	               if( null != fr ){   
	                  fr.close();     
	               }                  
	            }catch (IOException e2){ 
	            	throw new PersistenciaException("Error leyendo datos.",e2);
	            }
	         }
	         
		}
		return ret;
	}
	
	public static ArrayList<String> leerDirectorio(String ruta){
		
		File f = new File(ruta);
		ArrayList<String> ret = new ArrayList<String>();
		if (f.exists()){
			File[] archivos = f.listFiles();
			for (int i = 0 ; i < archivos.length; i++){
			 // System.out.println(archivos[i].getName());
			  ret.add((archivos[i].getName()));
			}
		}
		
		
		return ret;
		
		
	}
	
	
	/**
	 * Borra el arhivo ruta+archivo.
	 * @param ruta
	 * @param archivo
	 */
	public static void borrarArchivo(String ruta, String archivo){
		
		File f = new File(ruta+archivo);
		if (f.delete()){
			
		}
		else{
			System.out.println("No se pudo borrar el archivo (habría que mandar excepción).");
		}
		
		
	}
	
	
}
