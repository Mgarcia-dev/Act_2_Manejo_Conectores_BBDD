package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

// Esta interface define un CRUD para el objeto coche

// CREATE, READ, UPDATE, DELETE

public interface DaoCoche {
	
	public boolean addCoche (Coche c);
	public boolean delCoche(String id);
	public boolean modCoche(Coche c);
	public Coche consultarCoche (String id);
	public List<Coche> toList();
	

}
