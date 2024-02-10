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
			/*
			if(c.getMarca().isEmpty() && c.getModelo().isEmpty()) {
				System.out.println("La marca y el modelo no deben estar en blanco! ");
				modified = false;
			}
			*/
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
		List<Coche> listaCoches = daoCoche.toList();
		return listaCoches;
	}
	
	
	// MÉTODOS DE LOS PASAJEROS
	
	// ADD
	public boolean addPassenger(Pasajero p) {
		return daoCoche.addPassenger(p);
		
	}
	
	// DEL
		public boolean delPassenger(int id) {
			return daoCoche.delPassenger(id);
		}
	
	// CONSULTAR POR ID
	public Pasajero consultPassengerById (int id) {
		Pasajero p= daoCoche.consultPassenger(id);
		return p;
	}
	
	// LISTAR
		public List<Pasajero> toListPassengers() {
			List<Pasajero> passengerList = daoCoche.toListPassengers();
			return passengerList;
		}
	
	// AÑADIR PASAJERO A COCHE
	public boolean addPPassengerOfCar(int idPasajero, String idCoche) {
		return daoCoche.addPasajeroCoche(idPasajero, idCoche);
	}
	
	// BORRAR PASAJERO DE COCHE
	public boolean delPassengerOfCar(int idPasajero) {
		return daoCoche.delPasajeroCoche(idPasajero);
	}
	
	public List<Pasajero> toListPassengersOfCar (String idCoche) {
		List<Pasajero> passengerCarList = daoCoche.listarPasajerosCoche(idCoche);
		return passengerCarList;
		
	}
	
	
	





























}
