package modelo.entidad;

import java.io.Serializable;


public class Pasajero implements Serializable{

	
	private static final long serialVersionUID = -3661305815702217973L;
	
	private int id;
	private String nombre;
	private int edad;
	private double peso;
	
	public Pasajero(int idPasajero, String nombre, int edad, double peso) {
		super();
		this.id = idPasajero;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}

	public Pasajero() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}

	
	
	
	
	
	
}
