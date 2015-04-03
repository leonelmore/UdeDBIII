package logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;





//import persistencia.accesoBD.AccesoBD;
//import persistencia.daos.DAODue�os;
//import persistencia.daos.DAOMascotas;
import persistencia.valueObjects.VODue�o;
import persistencia.valueObjects.VOMascota;
import poolDeConexiones.IConexion;
import poolDeConexiones.IPoolConexiones;

import excepciones.ConfiguracionException;
import excepciones.LogicaException;
import excepciones.PersistenciaException;
import excepciones.PoolDeConexionesException;

import persistencia.daos.DAODue�osArchivo;
import persistencia.daos.DAODue�osMySQL;
import persistencia.daos.DAOMascotasArchivo;
import persistencia.daos.DAOMascotasMySQL;
import persistencia.daos.IDAODue�os;
import persistencia.daos.IDAOMascotas;
import persistencia.factories.IFabrica;
import poolDeConexiones.PoolConexionesArchivo;
import poolDeConexiones.PoolConexionesMySQL;
import settings.LoadSettings;



/**
 * @author usuario
 *
 */
public class Fachada extends UnicastRemoteObject implements IFachada{
	
	private IPoolConexiones pool;
	private IFabrica fabrica;
	IDAODue�os daoD;
	IDAOMascotas daoM;
	private static final long serialVersionUID = 1L;

	public Fachada() throws LogicaException, RemoteException {
		//AQUI SE AGREGA EL MANEJO DE LOS DOS TIPOS DE PERSISTENCIA
		try {
			fabrica = (IFabrica) Class.forName(LoadSettings.getData().nomFab).newInstance();
			System.out.println("Fachada - cargo f�brica");
			this.daoD = fabrica.crearDAODue�os();
			this.daoM = fabrica.crearDAOMascotas();
			pool = fabrica.crearPoolConexiones();

		} catch (ConfiguracionException e) {
			throw new LogicaException(e.getMessage(), e);
		} catch (PoolDeConexionesException e) {
			throw new LogicaException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new LogicaException("Problema eligiendo el m�todo de persistencia", e);
		} catch (IllegalAccessException e) {
			throw new LogicaException("Problema eligiendo el m�todo de persistencia", e);
		} catch (ClassNotFoundException e) {
			throw new LogicaException("Problema eligiendo el m�todo de persistencia", e);



		}

	}

	
	/**
	 * El m�todo nuevoDue�o ingresa un nuevo due�o al sistema, chequeando que no existiera.
	 * @param vo
	 * @throws PoolDeConexionesException 
	 */
	public void nuevoDue�o(VODue�o vo) throws LogicaException, RemoteException{

		IConexion icon = null;
		try {
			icon = pool.obtenerConexion(true);

			Due�o d = new Due�o(vo.getCedula(), vo.getNombre(), vo.getApellido());
			daoD.insert(icon, d);
			pool.liberarConexion(icon, true);

		} catch (PersistenciaException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);
		} catch (PoolDeConexionesException e) {
			try {

				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);

		}

	}
	
	
	/**
	 * El m�todo nuevaMascota ingresa una nueva mascota al sistema, chequeando que no 
	 * existiera previamente y tambi�n que su due�o est� registrado.
	 * @param vo
	 * @param ci
	 * @throws PersistenciaException, PoolDeConexionesException 
	 */
	public void nuevaMascota(VOMascota vo, int ci) throws LogicaException, RemoteException{
		
		IConexion icon = null;
		//this.daoM = new DAOMascotas(ci);

		try {
			icon = pool.obtenerConexion(true);
			this.daoM.setCedulaDue�o(ci);

			Mascota m = new Mascota(vo.getApodo(), vo.getRaza(), vo.getCedulaDue�o());
			daoM.insert(icon, m);
			pool.liberarConexion(icon, true);

		} catch (PersistenciaException e) {
			try {

				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);

		} catch (PoolDeConexionesException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);


		}

	}
	
	
	/**
	 * @return
	 * @throws PoolDeConexionesException 
	 */
	public ArrayList<VODue�o> listarDue�os() throws LogicaException, RemoteException{
		//A nivel de arquitectura no sab�a si utilizar los vo en AccesoBD.
		IConexion icon = null;
		ArrayList<VODue�o> ret = new ArrayList<VODue�o>(); 



		try {
			icon = pool.obtenerConexion(true);

			for (Due�o d : daoD.listarDue�os(icon)){

				VODue�o vo = new VODue�o(d.getCedula(), d.getNombre(), d.getApellido());
				ret.add(vo);
			}
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);
		} catch (PoolDeConexionesException e) {
			try {

				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);

		}
		return ret;	

	}

	
	/**
	 * @param ci
	 * @return
	 * @throws PoolDeConexionesException 
	 */
	public ArrayList<VOMascota> listarMascotas(int ci) throws LogicaException, RemoteException{

		//this.daoM = new DAOMascotas(ci);
		IConexion icon = null;
		ArrayList<VOMascota> ret = new ArrayList<VOMascota>();
		try {
			icon = pool.obtenerConexion(true);
			this.daoM.setCedulaDue�o(ci);


			for (Mascota m : daoM.listarMascotas(icon)){
				VOMascota vo = new VOMascota(m.getApodo(), m.getRaza(), m.getCedulaDue�o());
				ret.add(vo);
			}
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);
		} catch (PoolDeConexionesException e) {
			try {


				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				//NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);

		}
		return ret;

	}
	
	/**
	 * @param ci
	 * @throws PoolDeConexionesException 
	 */
	public void borrarDue�oMascotas(int ci) throws LogicaException, RemoteException{
		IConexion icon = null; 
		//this.daoM = new DAOMascotas(ci);
		try {
			icon = pool.obtenerConexion(true);
			this.daoM.setCedulaDue�o(ci);





			daoM.borrarMascotas(icon);
			daoD.delete(icon, ci);
			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			try {

				pool.liberarConexion(icon, false);
			} catch (PoolDeConexionesException e1) {
				// NO HACER NADA
			}
			throw new LogicaException(e.getMessage(),e);
		} catch (PoolDeConexionesException e) {
			throw new LogicaException(e.getMessage(),e);

		}

	}


}
