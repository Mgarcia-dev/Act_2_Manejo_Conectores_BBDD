package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
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
			if (!cocheExiste(c.getId())) {
				
			
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
			
			} else {
				add = false;
				System.out.println("El coche con ID: " + c.getId() + " ya se encuentra en la base de datos");
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir: " + c);
			add = false;
			e.printStackTrace();
		} 
		return add;
	}

	// BORRRAR COCHE POR ID
	@Override
	public boolean delCoche(String id) {
		
		boolean deleted = false;
		
		String query = "DELETE FROM COCHES WHERE ID = ?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			if(cocheExiste(id)) {
				
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1, id);
				
				int numRowAfected = ps.executeUpdate();
				
				if(numRowAfected == 0) {
					deleted = false;
				} else {
					deleted = true;
				}
				
			} else {
				deleted = false;
				System.out.println("El coche con ID: " + id + " no se encuentra en la base de datos\n");
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido realizar la baja del coche con id: " + id);
			e.printStackTrace();
		} 
		return deleted;
	}

	
	// MODIFICAR COCHE 
	@Override
	public boolean modCoche(Coche c) {
		
		boolean modified = false;
		String query = "UPDATE COCHES SET MARCA=?, MODELO=?, AÑO_FABRICACION=?, KM=? WHERE ID=?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			if(cocheExiste(c.getId())) {
				
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
					System.out.println("coche modificado correctamente");
				}
			
			} else {
				System.out.println("El coche con ID: " + c.getId() + " no existe en la BBDD\n");
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
		} 
		return car;
	}

	
	// Listar coches
	@Override
	public List<Coche> listarCoches() {
		
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
			}
		
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de coches");
			e.printStackTrace();
		} 
		return carList;
	}

	
	// MÉTODOS PARA PASAJEROS
	
	
	@Override
	public boolean addPasajeroCoche(int idPasajero, String idCoche) {
		
		boolean add = false;
		
		String query = "UPDATE COCHES SET PASAJERO_ID = ? WHERE ID = ?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
				
				PreparedStatement ps = conexion.prepareStatement(query);
				
				ps.setInt(1, idPasajero);
				ps.setString(2, idCoche);
				
				
				int numFilasAfectadas = ps.executeUpdate();
				
				if(numFilasAfectadas == 0) {
					add = false;
				} else {
					add = true;
				}

		} catch (SQLException e) {
			System.out.println("Error al añadir: " + idPasajero);
			add = false;
			e.printStackTrace();
		}
		return add;
	}

	
	// QUERY MAL REDACTADA
	@Override
	public boolean delPasajeroCoche(int idPasajero) {
		
		boolean deleted = false;
		
		String query = "UPDATE COCHES SET PASAJERO_ID = NULL WHERE PASAJERO_ID = ?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			
			int numRowAfected = ps.executeUpdate();
			
			if(numRowAfected == 0) {
				deleted = false;
			} else {
				deleted = true;
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido realizar la baja del coche con id: " + idPasajero);
			e.printStackTrace();
		} 
		return deleted;
	}

	@Override
	public List<Pasajero> listarPasajerosCoche(String idCoche) {
		
		List<Pasajero> pasajeros = new ArrayList<>();
		
		String query = "SELECT * FROM PASAJEROS WHERE ID IN (SELECT PASAJERO_ID FROM COCHES WHERE ID = ?)";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.print("ID: ");
				System.out.print(rs.getString("ID"));
				System.out.print(" - Nombre: ");
				System.out.print(rs.getString("NOMBRE"));
				System.out.print(" - Edad: ");
				System.out.print(rs.getString("EDAD"));
				System.out.print(" - Peso: ");
				System.out.print(rs.getString("PESO"));
				System.out.println("\n");
			}
		
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de pasajeros");
			e.printStackTrace();
		}
		return pasajeros;
	}

	@Override
	public boolean addPasajero(Pasajero p) {
		
		boolean add = true;
		
		String query = "INSERT INTO PASAJEROS (ID, NOMBRE, EDAD, PESO) VALUES (?,?,?,?)";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			if(!pasajeroExiste(p.getId())) {
			
				PreparedStatement ps = conexion.prepareStatement(query);
				
				ps.setInt(1, p.getId());
				ps.setString(2, p.getNombre());
				ps.setInt(3, p.getEdad());
				ps.setDouble(4, p.getPeso());
				
				
				int numFilasAfectadas = ps.executeUpdate();
				
				if(numFilasAfectadas == 0) {
					add = false;
				} else {
					add = true;
				}
			} else {
				System.out.println("El pasajero con ID: " + p.getId() + " ya existe!\n");
				add = false;
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir: " + p);
			add = false;
			e.printStackTrace();
		} 
		return add;
	}

	@Override
	public boolean delPasajero(int id) {
		
		boolean deleted = false;
		
		String query = "DELETE FROM PASAJEROS WHERE ID = ?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			if(pasajeroExiste(id)) {
				
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setInt(1, id);
				
				int numRowAfected = ps.executeUpdate();
				
				if(numRowAfected == 0) {
					deleted = false;
				} else {
					deleted = true;
				}
			} else {
				System.out.println("No se puede borrar el pasajero con ID: " + id + " porque no existe en BBDD");
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido realizar la baja del pasajero con id: " + id);
			e.printStackTrace();
		} 
		return deleted;
	}

	@Override
	public Pasajero consultarPasajero(int id) {
		
		Pasajero p = null;
		
		String query = "SELECT * FROM PASAJEROS WHERE ID=?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setEdad(rs.getInt(3));
				p.setPeso(rs.getDouble(4));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al obtener el coche con id: " + id);
			e.printStackTrace();
		} 
		return p;
	}

	@SuppressWarnings("unused")
	@Override
	public List<Pasajero> listarPasajeros() {
		
		List<Pasajero> passengerList = new ArrayList<>();
		
		String query = "SELECT ID, NOMBRE, EDAD, PESO FROM PASAJEROS";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.print("ID: ");
				System.out.print(rs.getInt("ID"));
				System.out.print(" - Nombre: ");
				System.out.print(rs.getString("NOMBRE"));
				System.out.print(" - Edad: ");
				System.out.print(rs.getString("EDAD"));
				System.out.print(" - Peso: ");
				System.out.print(rs.getString("PESO"));
				System.out.println("\n");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de pasajeros");
			e.printStackTrace();
		} 
			if(passengerList == null) {
				System.out.println("Lista de pasajeros vacía");
			}
			return passengerList;
			
		
		
	}

	/**
	 * Método para comprobar si un coche ya existe en la base de datos
	 * Se utilizará en los métodos para los cuales necesitemos saber, en primer lugar, 
	 * si el coche existe para poder añadirlo, borrarlo o modificarlo.
	 * @param idCoche
	 * @return True si el coche existe, false en caso contrario
	 */
	@Override
	public boolean cocheExiste(String idCoche) {
		boolean existe = false;
		Coche car = null;
		
		String query = "SELECT * FROM COCHES WHERE ID=?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				car = new Coche();
				car.setId(rs.getString(1));
				car.setMarca(rs.getString(2));
				car.setModelo(rs.getString(3));
				car.setAnoFab(rs.getInt(4));
				car.setKm(rs.getInt(5));
			}
			if(car != null) {
				existe = true;
			} else {
				existe = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			existe = false;
		} 
		return existe;
		
		
	}

	@Override
	public boolean pasajeroExiste(int id) {
		
		boolean existe = false;
		Pasajero p = null;
		
		String query = "SELECT * FROM PASAJEROS WHERE ID=?";
		
		try (Connection conexion = DriverManager.getConnection(cadenaConexion, user, pass)){
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				p = new Pasajero();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setEdad(rs.getInt(3));
				p.setPeso(rs.getDouble(4));
				
			}
			if(p != null) {
				existe = true;
			} else {
				existe = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			existe = false;
		} 
		return existe;
	}
}
