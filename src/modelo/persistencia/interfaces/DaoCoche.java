package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

// Esta interface define un CRUD para el objeto coche

// CREATE, READ, UPDATE, DELETE

public interface DaoCoche {
	
	// Métodos para coches
	public boolean addCoche (Coche c);
	public boolean delCoche(String id);
	public boolean modCoche(Coche c);
	public Coche consultarCoche (String id);
	public List<Coche> listarCoches();
	
	// Métodos para pasajeros
	public boolean addPasajero(Pasajero p);
	public boolean delPasajero(int id);
	public Pasajero consultarPasajero (int id);
	public List<Pasajero> listarPasajeros();
	public boolean addPasajeroCoche(int idPasajero, String idCoche);
	public boolean delPasajeroCoche(int idPasajero);
	public List<Pasajero> listarPasajerosCoche(String idCoche);
	
	

}
