package persistencia.factories;

import excepciones.PoolDeConexionesException;
import persistencia.daos.IDAODueños;
import persistencia.daos.IDAOMascotas;
import poolDeConexiones.IPoolConexiones;

public interface IFabrica {
	public IDAODueños crearDAODueños();
	public IDAOMascotas crearDAOMascotas();
	public IPoolConexiones crearPoolConexiones() throws PoolDeConexionesException;
}
