package modelo.entidad;

import java.io.Serializable;
import java.util.List;

public class Coche implements Serializable{
	
	private static final long serialVersionUID = -601266914082551664L;
	
	// ATRIBUTOS
	private String id;
	private String marca;
	private String modelo;
	private int anoFab;
	private int km;
	
	private List<Pasajero> pasajeros;
	
	// CONSTRUCTOR
	public Coche(String id, String marca, String modelo, int anoFab, int km, List<Pasajero> pasajeros) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFab = anoFab;
		this.km = km;
		this.pasajeros = pasajeros;
	}
	

	public Coche() {
		
	}

	// GETTERS Y SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnoFab() {
		return anoFab;
	}

	public void setAnoFab(int anoFab) {
		this.anoFab = anoFab;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public List<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	// toString
	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", anoFab=" + anoFab + ", km=" + km
				+ ", pasajeros=" + pasajeros + "]";
	}
}
