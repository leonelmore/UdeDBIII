package persistencia.factories;

import excepciones.PoolDeConexionesException;
import persistencia.daos.DAODueñosArchivo;
import persistencia.daos.DAOMascotasArchivo;
import persistencia.daos.IDAODueños;
import persistencia.daos.IDAOMascotas;
import poolDeConexiones.IPoolConexiones;
import poolDeConexiones.PoolConexionesArchivo;

public class FabricaArchivo implements IFabrica {

	public IDAODueños crearDAODueños() {
		return new DAODueñosArchivo();
	}

	public IDAOMascotas crearDAOMascotas() {
		return new DAOMascotasArchivo();
	}

	public IPoolConexiones crearPoolConexiones() throws PoolDeConexionesException {
		return new PoolConexionesArchivo();
	}

}
