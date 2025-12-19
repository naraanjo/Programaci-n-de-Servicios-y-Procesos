package inicioHilos;

import java.util.ArrayList;

public class ParkingCamion {

	public final static int numeroVehiculos = 20;
	public final static int numeroPlazs = 5;

	public static void main(String args[]) {
		// Recurso compartido
		GestorPlazasParking gestorPlazasParking = new GestorPlazasParking(numeroPlazs);

		// 5-coches --> 2 camiones --> 11-coches
		// Creo los vehiculos

		// --- 5 COCHES (IDs 0 al 4) ---
		for (int i = 0; i < 5; i++) {
			Thread v = new Thread(new Vehiculo(i, 1, gestorPlazasParking));
			v.start();
		}

		// --- 2 CAMIONES (IDs 5 al 6) ---
		for (int i = 5; i < 7; i++) {
			Thread v = new Thread(new Vehiculo(i, 2, gestorPlazasParking));
			v.start();
		}

		// --- 11 COCHES (IDs 7 al 17) ---
		for (int i = 7; i < 18; i++) {
			Thread v = new Thread(new Vehiculo(i, 1, gestorPlazasParking));
			v.start();
		}
	}

}

// Gestor de plazas - compartido
class GestorPlazasParking {

	// Attibutos
	Boolean plazaOcupada[];
	String estadoPlazaVehiculo[];
	int turnoValido = 0;

	// Constructor
	public GestorPlazasParking(int numeroPlazas) {

		this.plazaOcupada = new Boolean[numeroPlazas];
		this.estadoPlazaVehiculo = new String[numeroPlazas];

		// Inicializo todas las plazas a false
		for (int i = 0; i < numeroPlazas; i++) {
			plazaOcupada[i] = false; // Todas las plazas libres
			estadoPlazaVehiculo[i] = "-"; // Relleno por defecto indicare CA-CAMION O CO-COCHE
		}
	}

	// Metodo para obtener una plaza libre
	public int posicionLibre() {

		// Recorro todas las plazas
		for (int i = 0; i < plazaOcupada.length; i++) {

			// Caso de que la plaza este libre
			if (!plazaOcupada[i]) {
				// Devuelvo el numero de la plaza libre
				return i;
			}
		}
		// Devuelvo -1, si no hay plazas libre
		return -1;
	}

	// Metodo para obtener una plaza libre para un CAMION 23
	public ArrayList<Integer> posicionLibreCamion() {

		// Almacena las posicions contiguas
		ArrayList<Integer> plazasLibresDos = new ArrayList<Integer>();

		// Recorro todas las plazas
		for (int i = 0; i < plazaOcupada.length - 1; i++) {

			// Caso de que dos plazas contiguas esten libres
			if (!plazaOcupada[i] && !plazaOcupada[i + 1]) {
				// Guardo las dos posiciones
				plazasLibresDos.add(i);
				plazasLibresDos.add(i + 1);

				// Devuelvo array con las dos plazas
				return plazasLibresDos;
			}
		}
		// Devuelvo vacio, si no hay plazas libre
		return plazasLibresDos;

	}

	public boolean camionEntra() {
		ArrayList<Integer> validar = posicionLibreCamion();
		if (validar.size() > 0)
			return true;
		else
			return false;

	}

	// Metodo para acceder al parking
	public void accederParking(int turno, int tamaño) throws InterruptedException {
		int pos1 = 0;
		int pos2 = 0;
		int ocupa = -1;

		synchronized (this) {
			// Caso de que sea un coche
			if (tamaño == 1) {

				// Compruebo que haya hueco y sea su turno
				while (turno != turnoValido || (ocupa = posicionLibre()) == -1) {
					wait(); // Esperar
				}

				// Aqui ya ha podido entrar al parking
				turnoValido = (turnoValido + 1) % ParkingCamion.numeroVehiculos; // Aumento el turno
				plazaOcupada[ocupa] = true; // Ocupo la plaza
				estadoPlazaVehiculo[ocupa] = "CO";
				notifyAll(); // Notifico a los demas vehiculos

				System.out.println("Coche-" + turno + ", accede al parking");

			} else {
				// Caso de que sea un camion
				// Compruebo su turno y que haya dos posiciones libres
				while (turno != turnoValido || !camionEntra()) {
					wait(); // Esperar
				}
				// Aqui ya accede
				turnoValido = (turnoValido + 1) % ParkingCamion.numeroVehiculos; // Aumento el turno
				// Ocupo las posiciones
				ArrayList<Integer> posicionesCamion = posicionLibreCamion();
				pos1 = posicionesCamion.get(0);
				pos2 = posicionesCamion.get(1);
				plazaOcupada[pos1] = true;
				plazaOcupada[pos2] = true;

				estadoPlazaVehiculo[pos1] = "CA";
				estadoPlazaVehiculo[pos2] = "CA";
				// Notifico
				notifyAll();
				System.out.println("Camion-" + pos1 + +pos2 + ", accede al parking");

			}
		}

		// Imprimo el estado del parking
		synchronized (this) {

			for (int i = 0; i < estadoPlazaVehiculo.length; i++) {
				System.out.print(" || " + estadoPlazaVehiculo[i]);
			}
			System.out.println();

		}

		// Tiempo que pasa el vehiculo en el parking
		Thread.sleep(2000);

		// Finaliza
		synchronized (this) {
			// Caso de que sea un coche
			if (tamaño == 1) {

				estadoPlazaVehiculo[ocupa] = "-";
				plazaOcupada[ocupa] = false; // Libero la plaza del coche
				notifyAll();
				System.out.println("Coche-" + turno + ", sale del parking");

			} else {

				plazaOcupada[pos1] = false;
				plazaOcupada[pos2] = false;
				estadoPlazaVehiculo[pos1] = "-";
				estadoPlazaVehiculo[pos2] = "-";

				// Notifico
				notifyAll();

				System.out.println("Camion-" + pos1 + +pos2 + ", sale del parking");

			}
		}

	}

}

// Clase coche que puede acceder a las plazas
class Vehiculo implements Runnable {

	private int turno;
	private GestorPlazasParking gestorPlazasParking;
	private int tamaño; // 1 Coche || 2 Camion

	// Constructor
	public Vehiculo(int turno, int tamaño, GestorPlazasParking gestorPlazasParking) {

		this.turno = turno;
		this.gestorPlazasParking = gestorPlazasParking;
		this.tamaño = tamaño;
	}

	@Override
	public void run() {

		try {
			gestorPlazasParking.accederParking(turno, tamaño);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
