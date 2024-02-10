package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySQL implements DaoCoche{
	
	// CONEXION A LA BBDD COCHE
	
	private Connection conexion;
	
	// Parámetros para la conexion a la BBDD
	String cadenaConexion = "jdbc:mysql://localhost:3306/coche";
	String user = "root";
	String pass = "";
	{
		
	// CONEXION A BBDD
	try {
		conexion = DriverManager.getConnection(cadenaConexion, user, pass);
	} catch (SQLException e) {
		System.out.println("No se ha podido establecer la conexión");
		e.printStackTrace();
		}
	}
	
	/**
	 * Método para cerrar la conexión a la base de datos
	 * @return True si se ha cerrado la conexión
	 */
	public boolean closeConnection() {
		try {
			conexion.close();
			System.out.println("Conexión cerrada");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	// MÉTODOS DEL DAOCOCHE CON LAS QUERYS
	

	// AÑADIR COCHE A LA BBDD
	@Override
	public boolean addCoche(Coche c) {
		
		boolean add = true;
		
		String query = "INSERT INTO COCHES (ID, MARCA, MODELO, AÑO_FABRICACION, KM) VALUES (?,?,?,?,?)";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, c.getId());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getAnoFab());
			ps.setInt(5, c.getKm());
			
			int numFilasAfectadas = ps.executeUpdate();
			
			if(numFilasAfectadas == 0) {
				add = false;
			} else {
				add = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Error al añadir: " + c);
			add = false;
			e.printStackTrace();
		} /*finally {
			closeConnection();
		}
		*/
		return add;
	}

	// BORRRAR COCHE POR ID
	@Override
	public boolean delCoche(String id) {
		/*
		if(!openConnection()) {
			return false;
		}
		*/
		boolean deleted = false;
		
		String query = "DELETE FROM COCHES WHERE ID = ?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, id);
			
			int numRowAfected = ps.executeUpdate();
			
			if(numRowAfected == 0) {
				deleted = false;
			} else {
				deleted = true;
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido realizar la baja del coche con id: " + id);
			e.printStackTrace();
		} /*finally {
			closeConnection();
		}
		*/
		return deleted;
	}

	
	// MODIFICAR COCHE 
	@Override
	public boolean modCoche(Coche c) {
		/*
		if(!openConnection()) {
			return false;
		}
		*/
		
		boolean modified = false;
		String query = "UPDATE COCHES SET MARCA=?, MODELO=?, AÑO_FABRICACION=?, KM=? WHERE ID=?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAnoFab());
			ps.setInt(4, c.getKm());
			ps.setString(5, c.getId());
			
			int numRowAfected = ps.executeUpdate();
			
			if(numRowAfected == 0) {
				modified = false;
			} else {
				modified = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al modificar el coche: " + c);
			modified=false;
			e.printStackTrace();
		}
		
		return modified;
	}

	
	// Consultar coche por ID
	@Override
	public Coche consultarCoche(String id) {
		/*
		if(!openConnection()) {
			return null;
		}
		*/
		Coche car = null;
		
		String query = "SELECT * FROM COCHES WHERE ID=?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				car = new Coche();
				car.setId(rs.getString(1));
				car.setMarca(rs.getString(2));
				car.setModelo(rs.getString(3));
				car.setAnoFab(rs.getInt(4));
				car.setKm(rs.getInt(5));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al obtener el coche con id: " + id);
			e.printStackTrace();
		} /*finally {
			closeConnection();
		}
		*/
		return car;
	}

	
	// Listar coches
	@Override
	public List<Coche> toList() {
		/*
		if(!openConnection()) {
			return null;
		}
		*/
		List<Coche> carList = new ArrayList<>();
		
		String query = "SELECT ID, MARCA, MODELO, AÑO_FABRICACION, KM FROM COCHES";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.print("ID: ");
				System.out.print(rs.getString("ID"));
				System.out.print(" - Marca: ");
				System.out.print(rs.getString("MARCA"));
				System.out.print(" - Modelo: ");
				System.out.print(rs.getString("MODELO"));
				System.out.print(" - Año de fabricación: ");
				System.out.print(rs.getString("AÑO_FABRICACION"));
				System.out.print(" - Kms: ");
				System.out.print(rs.getString("KM"));
				System.out.println("\n");
				/*
				Coche coche = new Coche();
				coche.setId(rs.getString(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAnoFab(rs.getInt(4));
				coche.setKm(rs.getInt(5));
				
				carList.add(coche);
				*/
			}
		
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de coches");
			e.printStackTrace();
		} /*finally {
			closeConnection();
		}
		*/
		return null;
	}

	
	
}
