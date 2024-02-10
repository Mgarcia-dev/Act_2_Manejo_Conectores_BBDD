package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
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
	
	





























}
