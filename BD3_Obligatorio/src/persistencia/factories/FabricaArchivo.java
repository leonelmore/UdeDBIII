package persistencia.factories;

import excepciones.PoolDeConexionesException;
import persistencia.daos.DAODue�osArchivo;
import persistencia.daos.DAOMascotasArchivo;
import persistencia.daos.IDAODue�os;
import persistencia.daos.IDAOMascotas;
import poolDeConexiones.IPoolConexiones;
import poolDeConexiones.PoolConexionesArchivo;

public class FabricaArchivo implements IFabrica {

	public IDAODue�os crearDAODue�os() {
		return new DAODue�osArchivo();
	}

	public IDAOMascotas crearDAOMascotas() {
		return new DAOMascotasArchivo();
	}

	public IPoolConexiones crearPoolConexiones() throws PoolDeConexionesException {
		return new PoolConexionesArchivo();
	}

}
