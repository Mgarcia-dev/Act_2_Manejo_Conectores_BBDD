package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySQL;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoches {

	private DaoCoche daoCoche = new DaoCocheMySQL();
	
	// ADD
	public boolean addCoche(Coche c) {
		return daoCoche.addCoche(c);
		
	}
	
	// DEL
	public boolean delCoche (String id) {
		return daoCoche.delCoche(id);
	}

	// MODIFY
	public boolean modify (Coche c) {
			boolean modified = daoCoche.modCoche(c);
			if(modified) {
				return true;
			} else {
				return false;
			}
	}
	
	// OBTENER
	public Coche obtener (String id) {
		Coche coche = daoCoche.consultarCoche(id);
		return coche;
	}
	
	// LISTAR
	public List<Coche> toList() {
		List<Coche> listaCoches = daoCoche.listarCoches();
		return listaCoches;
	}
	
	// ______________________________________________________________________
	
	// MÉTODOS DE LOS PASAJEROS
	
	// ADD
	public boolean addPasajero(Pasajero p) {
		return daoCoche.addPasajero(p);
		
	}
	
	// DEL
		public boolean delPasajero(int id) {
			return daoCoche.delPasajero(id);
		}
	
	// CONSULTAR POR ID
	public Pasajero consultarPasajeroById (int id) {
		Pasajero p = daoCoche.consultarPasajero(id);
		return p;
	}
	
	// LISTAR
		public List<Pasajero> toListPassengers() {
			List<Pasajero> passengerList = daoCoche.listarPasajeros();
			return passengerList;
		}
	
	// AÑADIR PASAJERO A COCHE
	public boolean addPasajeroCoche(int idPasajero, String idCoche) {
		return daoCoche.addPasajeroCoche(idPasajero, idCoche);
	}
	
	// BORRAR PASAJERO DE COCHE
	public boolean delPasajeroCoche(int idPasajero) {
		return daoCoche.delPasajeroCoche(idPasajero);
	}
	
	public List<Pasajero> toListPassengersOfCar (String idCoche) {
		List<Pasajero> passengerCarList = daoCoche.listarPasajerosCoche(idCoche);
		return passengerCarList;
		
	}
	
	public boolean cocheExiste(String idCoche) {
		return daoCoche.cocheExiste(idCoche);
	}
	
	public boolean pasajeroExiste(int id) {
		return daoCoche.pasajeroExiste(id);
	}

}
