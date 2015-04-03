package logica;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import settings.LoadSettings;
import excepciones.ConfiguracionException;
import excepciones.LogicaException;
import excepciones.PoolDeConexionesException;
import logica.Fachada;

public class ServidorFachada {

	public static void main (String [] args) throws LogicaException {
		//ipServidor = 127.0.0.1
		//puertoServidor = 1099
		Fachada f = null;
		try {
			f = new Fachada();
			LocateRegistry.createRegistry(1099);
			try {
				Naming.rebind("//"+LoadSettings.getData().ip+":"+LoadSettings.getData().port+"/"+LoadSettings.getData().obj, f);
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
				throw new LogicaException("Error inicialziando el servidor remoto",e);
			} catch (ConfiguracionException e) {
				System.out.println(e.getMessage());
				throw new LogicaException(e.getMessage(),e);
			}
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
			throw new LogicaException("Error del servidor remoto",e);
		} catch (LogicaException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
