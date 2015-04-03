package grafica.controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import excepciones.ConfiguracionException;
import excepciones.LogicaException;
import excepciones.PersistenciaException;
import excepciones.PoolDeConexionesException;
import excepciones.VentanaException;
import persistencia.valueObjects.VODue�o;
import persistencia.valueObjects.VOMascota;
import settings.LoadSettings;
import logica.Fachada;
import logica.IFachada;


/* 
 * EN ESTA CLASE TENEMOS QUE PEDIR DE LA CONFIGURACI�N 
 * LOS DATOS PARA RMI
 * SER�AN IP, PUERTO Y EL NOMBRE DEL OBJETO.
 * */
public class Due�osController {
	
	private IFachada f;
	
	public Due�osController() throws RemoteException, VentanaException{
		try {
			//TODO: �?AQUI NO TENEMOS QUE CARGAR LA FACHADA USANDO?:
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			this.f = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
		} catch (MalformedURLException e) {
			throw new VentanaException("Error conectando con el servidor.",e);
		} catch (NotBoundException e) {
			throw new VentanaException("Error conectando con el servidor.",e);
		} catch (ConfiguracionException e) {
			throw new VentanaException(e.getMessage(),e);
		} 
	}
	
	/**
	 * @param vo
	 * @throws PersistenciaException
	 * @throws PoolDeConexionesException
	 */
	public void nuevoDue�o(VODue�o vo) throws VentanaException{
		
	//	this.f.nuevoDue�o(vo);
		try {
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			f.nuevoDue�o(vo);
		}  catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se creaba un due�o.",e);
		}  catch (LogicaException e) {
			throw new VentanaException(e.getMessage(),e);
		}
		
		
	}
	
	/**
	 * @param vod
	 * @param vom
	 * @throws PersistenciaException
	 * @throws PoolDeConexionesException
	 */
	public void nuevaMascota(VODue�o vod, VOMascota vom) throws VentanaException{
		
		//this.f.nuevaMascota(vom, vod.getCedula());
		try {
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			f.nuevaMascota(vom, vod.getCedula());
		} catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se creaba una mascota.",e);
		} catch (LogicaException e) {
			throw new VentanaException(e.getMessage(),e);
		}
		
	}
	
	/**
	 * @return
	 * @throws PoolDeConexionesException
	 * @throws PersistenciaException
	 */
	public ArrayList<VODue�o> listarDue�os() throws VentanaException{
		
		ArrayList<VODue�o> ret = new ArrayList<VODue�o>();
		//return this.f.listarDue�os();
		try {
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			ret = f.listarDue�os();
		} catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se listaban los due�os.",e);
		} catch (LogicaException e) {
			throw new VentanaException(e.getMessage(),e);
		}
		return ret;
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws PoolDeConexionesException
	 */
	public ArrayList<VOMascota> listarMascotas(VODue�o vo) throws VentanaException{
		ArrayList<VOMascota> ret = new ArrayList<VOMascota>();
		//return this.f.listarMascotas(vo.getCedula());
		try {
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			ret = f.listarMascotas(vo.getCedula());
		} catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se listaban las mascotas.",e);
		} catch (LogicaException e) {
			throw new VentanaException(e.getMessage(),e);
		}
		return ret;
		
	}
	
	public void borrarDue�oMascotas(VODue�o vo) throws VentanaException {
		//this.f.borrarDue�oMascotas(vo.getCedula());
		try {	
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			f.borrarDue�oMascotas(vo.getCedula());
		} catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se borraba un due�o de mascotas.",e);
		} catch (LogicaException e) {
			throw new VentanaException(e.getMessage(),e);
		}
	}

}
