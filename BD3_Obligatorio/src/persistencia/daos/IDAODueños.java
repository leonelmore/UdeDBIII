package persistencia.daos;

import java.util.ArrayList;

import excepciones.ConfiguracionException;
import excepciones.PersistenciaException;
import logica.Dueño;
import poolDeConexiones.IConexion;

public interface IDAODueños {
	public void delete(IConexion x,int y) throws PersistenciaException;
	public Dueño find(IConexion x,int y) throws PersistenciaException;
	public void insert(IConexion x,Dueño y) throws PersistenciaException;
	public ArrayList<Dueño> listarDueños(IConexion x) throws PersistenciaException;
	public boolean member(IConexion x,int y) throws PersistenciaException;
}
