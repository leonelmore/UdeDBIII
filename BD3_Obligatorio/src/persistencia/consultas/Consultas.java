package persistencia.consultas;





public class Consultas {

	public static String crearBD(){
		
		return "CREATE DATABASE due�osymascotas";
		
	}
	

//Duenos (c�dula INT, nombre VARCHAR(45), apellido VARCHAR(45)) 

	public static String createTablaDue�os(){
		return "CREATE TABLE Due�os("
				+ "cedula int PRIMARY KEY,"
				+ "nombre varchar(45) NOT NULL,"
				+ "apellido varchar(45) NOT NULL)";
	}
	
	public static String insertarDue�o(){
		return "INSERT INTO Due�os(cedula, nombre, apellido)"
				+ "VALUES(?,?,?)";
	}
	
	//Mascotas (apodo VARCHAR(45), raza VARCHAR(45), c�dulaDue�o INT) 
	public static String createTablaMascotas(){
		return "CREATE TABLE Mascotas("
				+ "apodo varchar(45) PRIMARY KEY,"
				+ "raza varchar(45),"
				+ "cedulaDue�o int,"
				+ "FOREIGN KEY fk_cedulaDue�o (cedulaDue�o) REFERENCES Due�os(cedula))";
		
	}
	
	public static String strborrarmascota()
	{
		return "Delete from mascotas where ceduladue�o = ?";
	}
	
	public static String strborrardue�o()
	{
		return "Delete from due�os where cedula = ?";
	}
	
	public static String strexisteMascota()
	{
		return "Select * from mascotas where apodo = ?";
	}
	
	public static String strexisteDue�o()
	{
		return "Select * from due�os where cedula = ?";
	}
	
	public static String strnuevodue�o()
	{
		return "insert into due�os(cedula, nombre, apellido) values (?,?,?)";
	}
	
	public static String strnuevamascota()
	{
		return("insert into mascotas(apodo, raza, cedulaDue�o) values (?,?,?)");
	}
	
	public static String strlistardue�os()
	{
		return("Select * from due�os");
	}
	
	public static String strlistarmascotas()
	{
		return("Select * from mascotas where mascotas.ceduladue�o=?");
	}
	
	public static String strEliminarMascotasDeUnDue�o(){
		return "DELETE FROM Mascotas WHERE cedulaDue�o = ?";
	}
	
	public static String strEliminarUnDue�o(){
		return "DELETE FROM Due�os WHERE cedula = ?";
	}


	
}
