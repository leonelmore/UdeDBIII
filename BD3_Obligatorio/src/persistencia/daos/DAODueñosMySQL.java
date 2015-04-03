package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logica.Dueño;
import persistencia.consultas.Consultas;
import poolDeConexiones.ConexionMySQL;
import poolDeConexiones.IConexion;
import excepciones.PersistenciaException;


public class DAODueñosMySQL implements IDAODueños {
	
	
	public DAODueñosMySQL(){
	}
	
	public boolean member(IConexion icon, int cedula) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		try{
			PreparedStatement pst = con.prepareStatement(Consultas.strexisteDueño());
			pst.setInt(1, cedula);
			ResultSet rs = pst.executeQuery();
			boolean retorno = rs.first();
			return retorno;
		}
		 catch (SQLException e) {
				throw new PersistenciaException("Error accediendo a datos de dueño.", e);
		}
		
	}
	
	public void insert(IConexion icon, Dueño due) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		if (!member(icon, due.getCedula())){
			
			try {
				
				PreparedStatement pst = con.prepareStatement(Consultas.insertarDueño());
				pst.setInt(1, due.getCedula());
				pst.setString(2, due.getNombre());
				pst.setString(3, due.getApellido());
				int r = pst.executeUpdate();
			} catch (SQLException e) {
				throw new PersistenciaException("Error accediendo a datos de dueño.", e);
			}
		}
		else{
			throw new PersistenciaException("El dueño ya existe.");
		}
		
		
	}
	
	public Dueño find(IConexion icon, int cedula) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		try{
			PreparedStatement pst = con.prepareStatement(Consultas.strexisteDueño());
			pst.setInt(1, cedula);
			Dueño d = null;
			ResultSet rs = pst.executeQuery();
			if (rs.first()){
				d = new Dueño(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"));
			}
			return d;
		}
		 catch (SQLException e) {
				throw new PersistenciaException("Error accediendo a datos de dueño.", e);
		}
		
	}	
	
	public void delete(IConexion icon, int cedula) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		if (member(icon, cedula)){
			try{
				PreparedStatement pst = con.prepareStatement(Consultas.strEliminarUnDueño());
				System.out.println(Consultas.strEliminarUnDueño());
				pst.setInt(1, cedula);
				int r = pst.executeUpdate();
			}
			catch (SQLException e){
				throw new PersistenciaException("Error accediendo a datos de dueño.", e);
			}
			
		}
		
		
	}
	
	public ArrayList<Dueño> listarDueños(IConexion icon) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		ArrayList<Dueño> ret = new ArrayList<Dueño>();
		
		try {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(Consultas.strlistardueños());
				while (rs.next()){
					Dueño d = new Dueño(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"));
					ret.add(d);
				}
		} catch (SQLException e) {
			throw new PersistenciaException("Error accediendo a datos de dueño.", e);
		}
		
		return ret;
		
	} 
	
}
