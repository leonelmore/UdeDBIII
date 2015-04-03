package persistencia.daos;

import java.util.ArrayList;

import logica.Mascota;
import excepciones.PersistenciaException;
import poolDeConexiones.IConexion;

public interface IDAOMascotas {
	public void borrarMascotas(IConexion x) throws PersistenciaException;
	public int getCedulaDueño() throws PersistenciaException;
	public void insert(IConexion x, Mascota y) throws PersistenciaException;
	public ArrayList<Mascota> listarMascotas(IConexion x) throws PersistenciaException;
	public boolean member(IConexion x, String y) throws PersistenciaException;
	public void setCedulaDueño(int x) throws PersistenciaException;
}
