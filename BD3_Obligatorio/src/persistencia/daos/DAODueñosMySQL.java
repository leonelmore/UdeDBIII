package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logica.Due�o;
import persistencia.consultas.Consultas;
import poolDeConexiones.ConexionMySQL;
import poolDeConexiones.IConexion;
import excepciones.PersistenciaException;


public class DAODue�osMySQL implements IDAODue�os {
	
	
	public DAODue�osMySQL(){
	}
	
	public boolean member(IConexion icon, int cedula) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		try{
			PreparedStatement pst = con.prepareStatement(Consultas.strexisteDue�o());
			pst.setInt(1, cedula);
			ResultSet rs = pst.executeQuery();
			boolean retorno = rs.first();
			return retorno;
		}
		 catch (SQLException e) {
				throw new PersistenciaException("Error accediendo a datos de due�o.", e);
		}
		
	}
	
	public void insert(IConexion icon, Due�o due) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		if (!member(icon, due.getCedula())){
			
			try {
				
				PreparedStatement pst = con.prepareStatement(Consultas.insertarDue�o());
				pst.setInt(1, due.getCedula());
				pst.setString(2, due.getNombre());
				pst.setString(3, due.getApellido());
				int r = pst.executeUpdate();
			} catch (SQLException e) {
				throw new PersistenciaException("Error accediendo a datos de due�o.", e);
			}
		}
		else{
			throw new PersistenciaException("El due�o ya existe.");
		}
		
		
	}
	
	public Due�o find(IConexion icon, int cedula) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		try{
			PreparedStatement pst = con.prepareStatement(Consultas.strexisteDue�o());
			pst.setInt(1, cedula);
			Due�o d = null;
			ResultSet rs = pst.executeQuery();
			if (rs.first()){
				d = new Due�o(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"));
			}
			return d;
		}
		 catch (SQLException e) {
				throw new PersistenciaException("Error accediendo a datos de due�o.", e);
		}
		
	}	
	
	public void delete(IConexion icon, int cedula) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		if (member(icon, cedula)){
			try{
				PreparedStatement pst = con.prepareStatement(Consultas.strEliminarUnDue�o());
				System.out.println(Consultas.strEliminarUnDue�o());
				pst.setInt(1, cedula);
				int r = pst.executeUpdate();
			}
			catch (SQLException e){
				throw new PersistenciaException("Error accediendo a datos de due�o.", e);
			}
			
		}
		
		
	}
	
	public ArrayList<Due�o> listarDue�os(IConexion icon) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		ArrayList<Due�o> ret = new ArrayList<Due�o>();
		
		try {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(Consultas.strlistardue�os());
				while (rs.next()){
					Due�o d = new Due�o(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"));
					ret.add(d);
				}
		} catch (SQLException e) {
			throw new PersistenciaException("Error accediendo a datos de due�o.", e);
		}
		
		return ret;
		
	} 
	
}
