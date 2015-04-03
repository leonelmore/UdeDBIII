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
import persistencia.valueObjects.VODueño;
import persistencia.valueObjects.VOMascota;
import settings.LoadSettings;
import logica.Fachada;
import logica.IFachada;


/* 
 * EN ESTA CLASE TENEMOS QUE PEDIR DE LA CONFIGURACIÓN 
 * LOS DATOS PARA RMI
 * SERÍAN IP, PUERTO Y EL NOMBRE DEL OBJETO.
 * */
public class DueñosController {
	
	private IFachada f;
	
	public DueñosController() throws RemoteException, VentanaException{
		try {
			//TODO: ¿?AQUI NO TENEMOS QUE CARGAR LA FACHADA USANDO?:
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
	public void nuevoDueño(VODueño vo) throws VentanaException{
		
	//	this.f.nuevoDueño(vo);
		try {
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			f.nuevoDueño(vo);
		}  catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se creaba un dueño.",e);
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
	public void nuevaMascota(VODueño vod, VOMascota vom) throws VentanaException{
		
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
	public ArrayList<VODueño> listarDueños() throws VentanaException{
		
		ArrayList<VODueño> ret = new ArrayList<VODueño>();
		//return this.f.listarDueños();
		try {
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			ret = f.listarDueños();
		} catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se listaban los dueños.",e);
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
	public ArrayList<VOMascota> listarMascotas(VODueño vo) throws VentanaException{
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
	
	public void borrarDueñoMascotas(VODueño vo) throws VentanaException {
		//this.f.borrarDueñoMascotas(vo.getCedula());
		try {	
			//IFachada fa = (IFachada)Naming.lookup("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj);
			f.borrarDueñoMascotas(vo.getCedula());
		} catch (RemoteException e) {
			throw new VentanaException("Error conectando con el servidor remoto cuando se borraba un dueño de mascotas.",e);
		} catch (LogicaException e) {
			throw new VentanaException(e.getMessage(),e);
		}
	}

}
