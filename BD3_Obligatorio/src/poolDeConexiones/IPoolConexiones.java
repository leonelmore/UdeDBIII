package poolDeConexiones;

import excepciones.PoolDeConexionesException;

public interface IPoolConexiones {
	public abstract IConexion obtenerConexion(boolean update) throws PoolDeConexionesException ;
	public abstract void liberarConexion(IConexion conexion, boolean hacerCommit) throws PoolDeConexionesException ;
}
