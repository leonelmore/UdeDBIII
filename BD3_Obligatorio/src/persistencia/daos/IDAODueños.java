package persistencia.daos;

import java.util.ArrayList;

import excepciones.ConfiguracionException;
import excepciones.PersistenciaException;
import logica.Due�o;
import poolDeConexiones.IConexion;

public interface IDAODue�os {
	public void delete(IConexion x,int y) throws PersistenciaException;
	public Due�o find(IConexion x,int y) throws PersistenciaException;
	public void insert(IConexion x,Due�o y) throws PersistenciaException;
	public ArrayList<Due�o> listarDue�os(IConexion x) throws PersistenciaException;
	public boolean member(IConexion x,int y) throws PersistenciaException;
}
