package logica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;





//import persistencia.accesoBD.AccesoBD;
//import persistencia.daos.DAODueños;
//import persistencia.daos.DAOMascotas;
import persistencia.valueObjects.VODueño;
import persistencia.valueObjects.VOMascota;
import poolDeConexiones.IConexion;
import poolDeConexiones.IPoolConexiones;

import excepciones.ConfiguracionException;
import excepciones.LogicaException;
import excepciones.PersistenciaException;
import excepciones.PoolDeConexionesException;

import persistencia.daos.DAODueñosArchivo;
import persistencia.daos.DAODueñosMySQL;
import persistencia.daos.DAOMascotasArchivo;
import persistencia.daos.DAOMascotasMySQL;
import persistencia.daos.IDAODueños;
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
	IDAODueños daoD;
	IDAOMascotas daoM;
	private static final long serialVersionUID = 1L;

	public Fachada() throws LogicaException, RemoteException {
		//AQUI SE AGREGA EL MANEJO DE LOS DOS TIPOS DE PERSISTENCIA
		try {
			fabrica = (IFabrica) Class.forName(LoadSettings.getData().nomFab).newInstance();
			System.out.println("Fachada - cargo fábrica");
			this.daoD = fabrica.crearDAODueños();
			this.daoM = fabrica.crearDAOMascotas();
			pool = fabrica.crearPoolConexiones();

		} catch (ConfiguracionException e) {
			throw new LogicaException(e.getMessage(), e);
		} catch (PoolDeConexionesException e) {
			throw new LogicaException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new LogicaException("Problema eligiendo el método de persistencia", e);
		} catch (IllegalAccessException e) {
			throw new LogicaException("Problema eligiendo el método de persistencia", e);
		} catch (ClassNotFoundException e) {
			throw new LogicaException("Problema eligiendo el método de persistencia", e);



		}

	}

	
	/**
	 * El método nuevoDueño ingresa un nuevo dueño al sistema, chequeando que no existiera.
	 * @param vo
	 * @throws PoolDeConexionesException 
	 */
	public void nuevoDueño(VODueño vo) throws LogicaException, RemoteException{

		IConexion icon = null;
		try {
			icon = pool.obtenerConexion(true);

			Dueño d = new Dueño(vo.getCedula(), vo.getNombre(), vo.getApellido());
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
	 * El método nuevaMascota ingresa una nueva mascota al sistema, chequeando que no 
	 * existiera previamente y también que su dueño esté registrado.
	 * @param vo
	 * @param ci
	 * @throws PersistenciaException, PoolDeConexionesException 
	 */
	public void nuevaMascota(VOMascota vo, int ci) throws LogicaException, RemoteException{
		
		IConexion icon = null;
		//this.daoM = new DAOMascotas(ci);

		try {
			icon = pool.obtenerConexion(true);
			this.daoM.setCedulaDueño(ci);

			Mascota m = new Mascota(vo.getApodo(), vo.getRaza(), vo.getCedulaDueño());
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
	public ArrayList<VODueño> listarDueños() throws LogicaException, RemoteException{
		//A nivel de arquitectura no sabía si utilizar los vo en AccesoBD.
		IConexion icon = null;
		ArrayList<VODueño> ret = new ArrayList<VODueño>(); 



		try {
			icon = pool.obtenerConexion(true);

			for (Dueño d : daoD.listarDueños(icon)){

				VODueño vo = new VODueño(d.getCedula(), d.getNombre(), d.getApellido());
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
			this.daoM.setCedulaDueño(ci);


			for (Mascota m : daoM.listarMascotas(icon)){
				VOMascota vo = new VOMascota(m.getApodo(), m.getRaza(), m.getCedulaDueño());
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
	public void borrarDueñoMascotas(int ci) throws LogicaException, RemoteException{
		IConexion icon = null; 
		//this.daoM = new DAOMascotas(ci);
		try {
			icon = pool.obtenerConexion(true);
			this.daoM.setCedulaDueño(ci);





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
