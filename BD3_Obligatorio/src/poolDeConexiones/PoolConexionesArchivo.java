package poolDeConexiones;

import excepciones.PoolDeConexionesException;

public class PoolConexionesArchivo implements IPoolConexiones {

	private int cantidadLectores;
	private boolean hayAlguienEscribiendo;
	
	public PoolConexionesArchivo(){
		hayAlguienEscribiendo = false;
		cantidadLectores = 0;
	}
	
	public synchronized IConexion obtenerConexion(boolean update)
			throws PoolDeConexionesException {
		IConexion unaConexion = new ConexionArchivo();
		boolean tengoConexion = false;
		
		//Verifico integridad
		if (cantidadLectores > 0 && hayAlguienEscribiendo)
		{
			throw new PoolDeConexionesException("Ocurrió un error de sincronización del Pool de Conexiones.");
		}
		if (cantidadLectores < 0)
		{
			throw new PoolDeConexionesException("Ocurrió un error de sincronización del Pool de Conexiones.");
		}
		
		if (update){
			while (!tengoConexion){
				if (cantidadLectores == 0 && !hayAlguienEscribiendo)
				{
					hayAlguienEscribiendo = true;
					tengoConexion = true;
				}
				else
				{
					//tengo que esperar
					try {
						wait();
					} catch (InterruptedException e) {
						throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
					}
				}
			}
		}
		else {
			while (!tengoConexion){
				if (!hayAlguienEscribiendo)
				{
					cantidadLectores++;
					tengoConexion = true;
				}
				else
				{
					//tengo que esperar
					try {
						wait();
					} catch (InterruptedException e) {
						throw new PoolDeConexionesException("Error de sincronización wait-notify.",e);
					}
				}
			}
		}
		return unaConexion;
	}

	public synchronized void liberarConexion(IConexion conexion, boolean hacerCommit)
			throws PoolDeConexionesException {
		//Verifico integridad
		if (cantidadLectores > 0 && hayAlguienEscribiendo)
		{
			throw new PoolDeConexionesException("Ocurrió un error de sincronización del Pool de Conexiones.");
		}
		
		//Veo si es un escritor
		if (hayAlguienEscribiendo)
		{
			//Soy yo!
			hayAlguienEscribiendo = false;
		}
		else
		{
			//Si cantidadLectores = 0, hay un error en ejecución
			if (cantidadLectores == 0)
			{
				throw new PoolDeConexionesException("Ocurrió un error de sincronización del Pool de Conexiones.");
			}
			else
			{
				cantidadLectores--;
			}
		}
		notify();
	}

}
