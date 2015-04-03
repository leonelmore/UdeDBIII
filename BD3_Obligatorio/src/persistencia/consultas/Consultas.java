package persistencia.consultas;





public class Consultas {

	public static String crearBD(){
		
		return "CREATE DATABASE dueñosymascotas";
		
	}
	

//Duenos (cédula INT, nombre VARCHAR(45), apellido VARCHAR(45)) 

	public static String createTablaDueños(){
		return "CREATE TABLE Dueños("
				+ "cedula int PRIMARY KEY,"
				+ "nombre varchar(45) NOT NULL,"
				+ "apellido varchar(45) NOT NULL)";
	}
	
	public static String insertarDueño(){
		return "INSERT INTO Dueños(cedula, nombre, apellido)"
				+ "VALUES(?,?,?)";
	}
	
	//Mascotas (apodo VARCHAR(45), raza VARCHAR(45), cédulaDueño INT) 
	public static String createTablaMascotas(){
		return "CREATE TABLE Mascotas("
				+ "apodo varchar(45) PRIMARY KEY,"
				+ "raza varchar(45),"
				+ "cedulaDueño int,"
				+ "FOREIGN KEY fk_cedulaDueño (cedulaDueño) REFERENCES Dueños(cedula))";
		
	}
	
	public static String strborrarmascota()
	{
		return "Delete from mascotas where ceduladueño = ?";
	}
	
	public static String strborrardueño()
	{
		return "Delete from dueños where cedula = ?";
	}
	
	public static String strexisteMascota()
	{
		return "Select * from mascotas where apodo = ?";
	}
	
	public static String strexisteDueño()
	{
		return "Select * from dueños where cedula = ?";
	}
	
	public static String strnuevodueño()
	{
		return "insert into dueños(cedula, nombre, apellido) values (?,?,?)";
	}
	
	public static String strnuevamascota()
	{
		return("insert into mascotas(apodo, raza, cedulaDueño) values (?,?,?)");
	}
	
	public static String strlistardueños()
	{
		return("Select * from dueños");
	}
	
	public static String strlistarmascotas()
	{
		return("Select * from mascotas where mascotas.ceduladueño=?");
	}
	
	public static String strEliminarMascotasDeUnDueño(){
		return "DELETE FROM Mascotas WHERE cedulaDueño = ?";
	}
	
	public static String strEliminarUnDueño(){
		return "DELETE FROM Dueños WHERE cedula = ?";
	}


	
}
