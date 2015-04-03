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
	
	private int cedulaDue�o;
	
	public DAOMascotasMySQL(){
	}
	
	public void setCedulaDue�o(int cedulaDue�o){
		this.cedulaDue�o = cedulaDue�o;
	}


	public int getCedulaDue�o() {
		return cedulaDue�o;
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
					pst.setInt(3, m.getCedulaDue�o());
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
				pst.setInt(1, this.cedulaDue�o);
				ResultSet rs = pst.executeQuery();
				while (rs.next()){
					Mascota m = new Mascota(rs.getString("apodo"), rs.getString("raza"), rs.getInt("cedulaDue�o"));
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
				PreparedStatement pst = con.prepareStatement(Consultas.strEliminarMascotasDeUnDue�o());
				pst.setInt(1, this.cedulaDue�o);
				int r = pst.executeUpdate();
			}
			catch (SQLException e){
				throw new PersistenciaException("Error accediendo a los datos de mascotas",e);
			}
	}
	
	

}
