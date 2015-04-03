package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logica.Mascota;
import persistencia.consultas.Consultas;
import poolDeConexiones.ConexionMySQL;
import poolDeConexiones.IConexion;
import excepciones.PersistenciaException;

public class DAOMascotasMySQL implements IDAOMascotas {
	
	private int cedulaDueño;
	
	public DAOMascotasMySQL(){
	}
	
	public void setCedulaDueño(int cedulaDueño){
		this.cedulaDueño = cedulaDueño;
	}


	public int getCedulaDueño() {
		return cedulaDueño;
	}

	
	public boolean member(IConexion icon, String apodo) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		try{
			PreparedStatement pst = con.prepareStatement(Consultas.strexisteMascota());
			pst.setString(1, apodo);
			ResultSet rs = pst.executeQuery();
			boolean exito = rs.first();
			return exito;
		}
		 catch (SQLException e) {
			 throw new PersistenciaException("Error accediendo a los datos de mascotas",e);
			}
		
	}
	
	public void insert(IConexion icon, Mascota m) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
			if(!member(icon, m.getApodo())){
				try{
					PreparedStatement pst = con.prepareStatement(Consultas.strnuevamascota());
					pst.setString(1, m.getApodo());
					pst.setString(2, m.getRaza());
					pst.setInt(3, m.getCedulaDueño());
					int r = pst.executeUpdate();
				}
				catch (SQLException e){
					throw new PersistenciaException("Error accediendo a los datos de mascotas",e);
				}
				
			}
			else{
				throw new PersistenciaException("Ya existe una mascota con este nombre.");
			}
		
	}
	
	public ArrayList<Mascota> listarMascotas(IConexion icon) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		ArrayList<Mascota> ret = new ArrayList<Mascota>();
		
		try {
				PreparedStatement pst = con.prepareStatement(Consultas.strlistarmascotas());
				pst.setInt(1, this.cedulaDueño);
				ResultSet rs = pst.executeQuery();
				while (rs.next()){
					Mascota m = new Mascota(rs.getString("apodo"), rs.getString("raza"), rs.getInt("cedulaDueño"));
					ret.add(m);
				}
		} catch (SQLException e) {
			throw new PersistenciaException("Error accediendo a los datos de mascotas",e);
		}
		
		return ret;
		
	}
	
	public void borrarMascotas(IConexion icon) throws PersistenciaException{
		ConexionMySQL con1 = (ConexionMySQL) icon;
		Connection con = con1.getConnection();
		try {
				PreparedStatement pst = con.prepareStatement(Consultas.strEliminarMascotasDeUnDueño());
				pst.setInt(1, this.cedulaDueño);
				int r = pst.executeUpdate();
			}
			catch (SQLException e){
				throw new PersistenciaException("Error accediendo a los datos de mascotas",e);
			}
	}
	
	

}
