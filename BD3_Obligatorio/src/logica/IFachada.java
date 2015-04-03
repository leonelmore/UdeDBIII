package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import persistencia.valueObjects.VODue�o;
import persistencia.valueObjects.VOMascota;
import excepciones.LogicaException;

public interface IFachada extends Remote
{

	public void nuevoDue�o(VODue�o vo) throws LogicaException, RemoteException;
	public void nuevaMascota(VOMascota vo, int ci) throws LogicaException, RemoteException;
	public ArrayList<VODue�o> listarDue�os() throws LogicaException, RemoteException;
	public ArrayList<VOMascota> listarMascotas(int ci) throws LogicaException, RemoteException;
	public void borrarDue�oMascotas(int ci) throws LogicaException, RemoteException;
	
	
}
