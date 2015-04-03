package persistencia.factories;

import excepciones.PoolDeConexionesException;
import persistencia.daos.IDAODue�os;
import persistencia.daos.IDAOMascotas;
import poolDeConexiones.IPoolConexiones;

public interface IFabrica {
	public IDAODue�os crearDAODue�os();
	public IDAOMascotas crearDAOMascotas();
	public IPoolConexiones crearPoolConexiones() throws PoolDeConexionesException;
}
