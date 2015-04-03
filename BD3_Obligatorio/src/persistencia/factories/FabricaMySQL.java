package persistencia.factories;

import excepciones.PoolDeConexionesException;
import persistencia.daos.DAODueñosMySQL;
import persistencia.daos.DAOMascotasMySQL;
import persistencia.daos.IDAODueños;
import persistencia.daos.IDAOMascotas;
import poolDeConexiones.IPoolConexiones;
import poolDeConexiones.PoolConexionesMySQL;

public class FabricaMySQL implements IFabrica {

	public IDAODueños crearDAODueños() {
		return new DAODueñosMySQL();
	}

	public IDAOMascotas crearDAOMascotas() {
		return new DAOMascotasMySQL();
	}

	public IPoolConexiones crearPoolConexiones() throws PoolDeConexionesException {
		return new PoolConexionesMySQL();
	}

}
