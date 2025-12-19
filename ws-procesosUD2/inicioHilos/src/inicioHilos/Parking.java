package inicioHilos;

public class Parking {
	
	public final static int numeroCoches = 20;
	public final static int numeroPlazas = 5;

	
	public static void main(String[] args) {
		
		// Creacion del recurso compartido
		GestorPlazas gestorPlazass  = new GestorPlazas(numeroPlazas);
		
		// Creo los coches
		for(int i = 0; i < numeroCoches; i++) {
			Thread coche = new Thread(new Coche(i, gestorPlazass));
			coche.start();
		}
		
	}
}

// Recurso compartido
class GestorPlazas {
	// Array con las plazas del parking libres
	Boolean plazaDisponible[];
	int cocheOcupa[];

	int turnoValido = 0;

	// Constructor del recurso compartido
	public GestorPlazas(int numeroPlazas) {

		plazaDisponible = new Boolean[numeroPlazas]; // Tamaño del parking - numero de plazas
		cocheOcupa = new int[numeroPlazas];
		
		// Inicializo todas las plazasDisponibles = true
		for (int i = 0; i < plazaDisponible.length; i++) {
			plazaDisponible[i] = true; // Disponible
			cocheOcupa[i] = -1;
		}
	}

	// Funcion pata obtener que plaza esta libre
	public int plazaLibre() {

		// Recorro todas las plazas
		for (int i = 0; i < plazaDisponible.length; i++) {

			// Plaza disponible
			if (plazaDisponible[i]) {
				// Devuelvo el numero de la plaza disponible
				return i;
			}
		}

		// Devuelvo -1, si no hay plaza disponible
		return -1;
	}

	// Metodo para acceder a la plaza del parking
	public void accederPlazaParking(int turnoCoche) throws InterruptedException {
		
		int ocupo = -1;
	
		synchronized (this) {
			// Compruebo que sea su turno y haya hueco
			while(turnoCoche != turnoValido || (ocupo = plazaLibre()) == -1) {
				wait(); // Espera hasta que sea su turno - y haya hueco
			}
			
			// Incremento el turno, cambio estado y notifico
			plazaDisponible[ocupo] = false; // Cambio estado
			cocheOcupa[ocupo] = turnoCoche; // Para saber que numero de coche esta
			turnoValido = (turnoValido +1) % Parking.numeroCoches;
			notifyAll(); // Notifico
			
			System.out.println("Coche-"+ turnoCoche + ", accediendo al parking");
			// Estado del parking
			for (int i = 0; i < cocheOcupa.length; i++) {
				System.out.print("  " + cocheOcupa[i]);
			}
			System.out.println();
			
		}
		
		// Tiempo aparcado
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Coche-"+ turnoCoche + ", saliendo del parking");
			plazaDisponible[ocupo] = true; // Libero la posicion del parking
			cocheOcupa[ocupo] = -1; // Quit el coche
			notifyAll();
		}
		
		
	}

}

// Intenta acceder al parking
class Coche implements Runnable {

	private int turno; // Turno para acceder al parking
	private GestorPlazas gestorPlazas;

	// Constructor
	public Coche(int turno, GestorPlazas gestorPlazas) {
		this.turno = turno;
		this.gestorPlazas = gestorPlazas;
	}

	@Override
	public void run() {
		try {
			while(true) {
				gestorPlazas.accederPlazaParking(turno);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}