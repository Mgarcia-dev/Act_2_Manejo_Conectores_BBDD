package main;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoches;

public class Main {

	public static void main(String[] args) {
		GestorCoches gc = new GestorCoches();
		
		menu();
		
	}

	
	// menu principal
	public static void menu() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		int option = 0;
		
		while (option != 7) {
			menuOptions();
			option = sc.nextInt();
			
			switch (option) {
			
			case 1: 
				// Añadir coche ( ID autoincremental)
				añadirCoche();
				break;
				
			case 2: 
				// Borrar coche por ID
				borrarCoche();
				break;
				
			case 3: 
				// Consultar coche por ID
				consultarCoche();
				break;
				
			case 4:
				// Modificar coche por ID
				modificarCoche();
				break;
				
			case 5:
				// Listar coches
				listarCoches();
				break;
				
			case 6:
				// Gestión de pasajeros
				menuGestionPasajeros();
				break;
				
			case 7: 
				System.out.println("*** Fin del programa *** ");
				sc.close();
				break;
				
			default:
				System.out.println("Opción incorrecta. Por favor, introduzca una opción válida\n");
				break;
			}
		}
	}
	
	// Menu principal
	public static void menuOptions() {
		
		System.out.println("****** Menú gestión coches******");
		System.out.println("__________________________\n");
		System.out.println("1.- Añadir nuevo coche.");
		System.out.println("2.- Borrar coche por ID.");
		System.out.println("3.- Consultar coche por ID.");
		System.out.println("4.- Modificar coche por ID");
		System.out.println("5.- Listar coches.");
		System.out.println("6.- Gestión de pasajeros");
		System.out.println("7.- Salir del programa");
		System.out.println("__________________________");
	}
	
	
	
	// menu pasajeros
	public static void menuGestionPasajeros() {
		
		Scanner sc = new Scanner(System.in);
		
		int option = 0;
		
		while (option != 8) {
			menuOptionsPasajeros();
			option = sc.nextInt();
			
			switch (option) {
			
			case 1: 
				// Crear nuevo pasajero
				crearPasajero();
				break;
			case 2: 
				// Borrar pasajero por ID
				borrarPasajero();
				break;
			case 3: 
				// Consultar pasajero por ID
				consultarPasajeroById();
				break;
			case 4:
				// Listar pasajeros
				listarpasajeros();
				break;
			case 5:
				// Añadir pasajero al coche
				addPasajeroCoche();
				break;
			case 6:
				// Eliminar pasajero del coche
				borrarPasajeroCoche();
				break;
			case 7: 
				// Listar pasajeros de un coche
				listarPasajerosCoche();
				break;
			case 8:
				menu();
				break;
			default:
				System.out.println("Opción incorrecta. Por favor, introduzca una opción válida");
				break;
			}
		
		}
		
	}
	
	// Menu pasajeros
	public static void menuOptionsPasajeros() {
		
		System.out.println("****** Menú gestión pasajeros ******");
		System.out.println("__________________________\n");
		System.out.println("1.- Crear nuevo pasajero");
		System.out.println("2.- Borrar pasajero por ID");
		System.out.println("3.- Consultar pasajero por ID.");
		System.out.println("4.- Listar pasajeros");
		System.out.println("5.- Añadir pasajero al coche ");
		System.out.println("6.- Eliminar pasajero de un coche");
		System.out.println("7.- Listar pasajeros de un coche");
		System.out.println("8.- Volver al menú principal");
		System.out.println("__________________________");
		
	}
	
	// AQUI SE AÑADEN LOS MÉTODOS DEL gc
	
	public static void añadirCoche() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca los datos del nuevo coche: ");
		System.out.println("ID: ");
		String idCoche = sc.next();
		System.out.println("Marca: ");
		String marca = sc.next();
		System.out.println("Modelo: ");
		String modelo = sc.next();
		System.out.println("Año de fabricación: ");
		int añoFab = sc.nextInt();
		System.out.println("Km: ");
		int km = sc.nextInt();
		
		Coche coche = new Coche(idCoche, marca, modelo, añoFab, km, null);
		//gc.addCoche(coche);
		
		boolean añadido = gc.addCoche(coche);
		
		if(añadido == true) {
			System.out.println("Coche añadido con éxito!");
		} else {
			System.out.println("Error al añadir el coche a la base de datos");
		}
		
		
		
	}
	public static void borrarCoche() {
		
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca el ID del coche que desea elimiar: ");
		String eliminarId = sc.next();
		
		boolean borrado = gc.delCoche(eliminarId);
		if(borrado == true) {
			System.out.println("Coche borrado.");
		} else {
			System.out.println("Fallo al eliminar el coche.");
		}
	}

	public static void consultarCoche() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Intoduzca el ID del coche que desea obtener: ");
		String idCoche = sc.next();
		
		Coche coche = gc.obtener(idCoche);
		if(coche != null) {
			System.out.println("\nEl resultado obtenido es el siguiente: ");
			System.out.println("ID: " + coche.getId());
			System.out.println("Marca: " + coche.getMarca());
			System.out.println("Modelo: " + coche.getModelo());
			System.out.println("Año de fabricación: " + coche.getAnoFab());
			System.out.println("KM: " + coche.getKm());
			System.out.println("");
		} else {
			System.out.println("No se ha podido obtener el resultado.");
		}
	}
	
	
	// TERMINAR 
	public static void modificarCoche() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca el ID del coche que desea modificar: ");
		String idCoche = sc.next();
		System.out.println("Introduzca la marca: ");
		String nuevaMarca= sc.next();
		System.out.println("introduzca el modelo: ");
		String nuevoModelo = sc.next();
		System.out.println("Introduzca año de fabricación: ");
		int nuevoAñoFab = sc.nextInt();
		System.out.println("Introduzca km: ");
		int nuevosKms = sc.nextInt();
		
		Coche coche = new Coche(idCoche, nuevaMarca, nuevoModelo, nuevoAñoFab, nuevosKms, null);
		
		boolean modificado = gc.modify(coche);
		
		if(modificado = true) {
			System.out.println("Coche modificado correctamente");
		} else {
			System.out.println("Error al modificar coche");
		}
		
	}
	
	public static void listarCoches() {
		
		GestorCoches gc = new GestorCoches();
		
		List<Coche> listaCoches = gc.toList();
		
	}
	
	
	// MÉTODOS PARA PASAJEROS 
	
	private static void crearPasajero() {
		
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca los datos del nuevo pasajero: ");
		System.out.println("ID: ");
		int idPasajero = sc.nextInt();
		
		System.out.println("Nombre: ");
		String nombre = sc.next();
		
		System.out.println("Edad: ");
		int edad = sc.nextInt();
		
		System.out.println("Peso: ");
		double peso = sc.nextDouble();
		
		Pasajero p = new Pasajero(idPasajero, nombre, edad, peso);
		
		boolean añadido = gc.addPasajero(p);
		
		if(añadido == true) {
			System.out.println("Pasajero creado con éxito!\n");
		} else {
			System.out.println("Error al crear pasajero\n");
		}
	}
	
	private static void borrarPasajero() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca el ID del pasajero que desea elimiar: ");
		int eliminarId = sc.nextInt();
		
		boolean borrado = gc.delPasajero(eliminarId);
		if(borrado == true) {
			System.out.println("Pasajero borrado.");
		} else {
			System.out.println("Fallo al eliminar el pasajero.");
		}
	}
	
	private static void consultarPasajeroById() {
		
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Intoduzca el ID del pasajero que desea obtener: ");
		int idPasajero = sc.nextInt();
		
		Pasajero p = gc.consultarPasajeroById(idPasajero);
		if(p != null) {
			System.out.println("\nEl resultado obtenido es el siguiente: ");
			System.out.println("ID: " + p.getId());
			System.out.println("Nombre: " + p.getNombre());
			System.out.println("Edad: " + p.getEdad());
			System.out.println("Peso: " + p.getPeso());
			System.out.println("");
		} else {
			System.out.println("No se ha podido obtener el resultado.");
		}
	}
	
	private static void listarpasajeros() {
		
		GestorCoches gc = new GestorCoches();
		
		List<Pasajero> pasengerList = gc.toListPassengers();
	}
	
	private static void addPasajeroCoche() {
		
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--- Añadir pasajero a un coche ---\n");
		System.out.println("Introduzca el ID del pasajero: ");
		int idPasajero = sc.nextInt();
		System.out.println("Introduzca el ID del coche para añadir al pasajero: ");
		String idCoche = sc.next();
		System.out.println("");
		
		boolean añadido = gc.addPasajeroCoche(idPasajero, idCoche);
		
		if(añadido == true) {
			System.out.println("Pasajero con ID: " + idPasajero +  " ha sido "
					+ "añadido con éxito!\n");
		} else {
			System.out.println("Error al añadir pasajero\n");
		}
	}
	
	private static void borrarPasajeroCoche() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--- Borrar pasajero de un coche ---\n");
		System.out.println("Introduzca el ID del pasajero: ");
		int idPasajero = sc.nextInt();
		System.out.println("");
		
		boolean añadido = gc.delPasajeroCoche(idPasajero);
		
		if(añadido == true) {
			System.out.println("Pasajero con ID: " + idPasajero +  " ha sido "
					+ "borrado del coche con éxito!\n");
		} else {
			System.out.println("Error al añadir pasajero\n");
		}
	}
	
	private static void listarPasajerosCoche() {
		GestorCoches gc = new GestorCoches();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduzca el ID del coche para obtener el listado de pasajeros: ");
		String idCoche = sc.next();
		
		List<Pasajero> passengerCarList = gc.toListPassengersOfCar(idCoche);
		
		if(passengerCarList == null ) {
			System.out.println("El coche no tiene pasajeros");
		} 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
