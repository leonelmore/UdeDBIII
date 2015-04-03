package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import persistencia.valueObjects.VODueño;
import persistencia.valueObjects.VOMascota;
import excepciones.LogicaException;

public interface IFachada extends Remote
{

	public void nuevoDueño(VODueño vo) throws LogicaException, RemoteException;
	public void nuevaMascota(VOMascota vo, int ci) throws LogicaException, RemoteException;
	public ArrayList<VODueño> listarDueños() throws LogicaException, RemoteException;
	public ArrayList<VOMascota> listarMascotas(int ci) throws LogicaException, RemoteException;
	public void borrarDueñoMascotas(int ci) throws LogicaException, RemoteException;
	
	
}
