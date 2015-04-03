package persistencia.factories;

import excepciones.PoolDeConexionesException;
import persistencia.daos.DAODue�osMySQL;
import persistencia.daos.DAOMascotasMySQL;
import persistencia.daos.IDAODue�os;
import persistencia.daos.IDAOMascotas;
import poolDeConexiones.IPoolConexiones;
import poolDeConexiones.PoolConexionesMySQL;

public class FabricaMySQL implements IFabrica {

	public IDAODue�os crearDAODue�os() {
		return new DAODue�osMySQL();
	}

	public IDAOMascotas crearDAOMascotas() {
		return new DAOMascotasMySQL();
	}

	public IPoolConexiones crearPoolConexiones() throws PoolDeConexionesException {
		return new PoolConexionesMySQL();
	}

}
