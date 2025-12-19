package Filosofos;

import java.util.Random;

public class EjercicioFilosofos {

	public final static int numeroFilosofos = 8;
	public final static int numeroPalillos = 8;

	public static void main(String args[]) {

		// Creacion del recurso compartido
		GestionarPalillos gestionarPalillos = new GestionarPalillos();

		// Creacion de los filosofos
		for (int i = 0; i < numeroFilosofos; i++) {
			Thread filosofo = new Thread(new Filosofo(i, gestionarPalillos));
			filosofo.start();
		}

	}
}

// Recurso compartido para gestionar los palillos
class GestionarPalillos {

	// Array de booleans, con el estado de cada palillo
	Boolean estadoPalilloLibre[] = new Boolean[EjercicioFilosofos.numeroPalillos];
	Boolean estaPensando[] = new Boolean[EjercicioFilosofos.numeroFilosofos];

	// Construcotr
	public GestionarPalillos() {

		// Inicialmente todos los palillos libres
		for (int i = 0; i < estadoPalilloLibre.length; i++) {
			estadoPalilloLibre[i] = true; // Libre todos los palillos inicialmente
			estaPensando[i] = false;
		}
	}

	public void gestionarPalillos(int numeroFilosofo) throws InterruptedException {

		Random aleator = new Random();

		System.out.println("Filosofo: " + numeroFilosofo + ", esta pensando...");
		Thread.sleep(aleator.nextInt(5000 - 1000 + 1) + 1000); // Piensa entre 5s y 1s

		// Termina de pensar y sigue el codigo

		// Comprueba que puede coger los palillos
		synchronized (this) {
			// Mientras no lo pueda coger espera
			while (!cogerPalillos(numeroFilosofo)) {
				wait();
			}

			// Consigue comer
			System.out.println("FILOSOFO: " + numeroFilosofo + " CONSIGUE COMER");
		}

		// Aqui ya tiene los palillos
		Thread.sleep(aleator.nextInt(5000 - 1000 + 1) + 1000); // Come entre 5s y 1s

		// Aqui ya ha comido
		synchronized (this) {
			System.out.println("Filosofo: " + numeroFilosofo + ", ha terminado de comer.");
			liberarPalillo(numeroFilosofo);
		}
	}

	// Funcion para liberar los palillos
	public void liberarPalillo(int numeroFilosofo) {

		System.out.println("Filosofo: " + numeroFilosofo + " liberando palillos");

		// Caso del F1
		if (numeroFilosofo == 0) {
			// Libero los palillos
			estadoPalilloLibre[numeroFilosofo] = true;
			estadoPalilloLibre[EjercicioFilosofos.numeroFilosofos - 1] = true;
			notifyAll(); // Notifico que salga del wait

		} else {

			// Libero los palillos
			estadoPalilloLibre[numeroFilosofo] = true;
			estadoPalilloLibre[numeroFilosofo - 1] = true;
			notifyAll(); // Notifico que salga del wait

		}

	}

	// Funcion para verficar si el filosofo puede coger sus palillos
	public boolean cogerPalillos(int numeroFilosofo) {

		// Caso del F1
		if (numeroFilosofo == 0) {
			// Palillos libres
			if (estadoPalilloLibre[numeroFilosofo] && estadoPalilloLibre[EjercicioFilosofos.numeroFilosofos - 1]) {
				// Pasan a estar ocupados
				estadoPalilloLibre[numeroFilosofo] = false;
				estadoPalilloLibre[EjercicioFilosofos.numeroFilosofos - 1] = false;
				return true;
			} else {
				return false;
			}

		} else {
			// Los demas filosofos
			// Palillos libres
			if (estadoPalilloLibre[numeroFilosofo] && estadoPalilloLibre[numeroFilosofo - 1]) {
				// Pasan a estar ocupados
				estadoPalilloLibre[numeroFilosofo] = false;
				estadoPalilloLibre[numeroFilosofo - 1] = false;
				return true;
			} else {
				return false;
			}
		}
	}

}

// Hilo --> Filosofo
class Filosofo implements Runnable {

	// Atributos
	int numeroFilosofo;
	GestionarPalillos gestionPalillos;

	// Constructor
	public Filosofo(int numeroFilosofo, GestionarPalillos gestionarPalillos) {

		this.numeroFilosofo = numeroFilosofo;
		this.gestionPalillos = gestionarPalillos;
	}

	@Override
	public void run() {
		try {

			while (true) {
				gestionPalillos.gestionarPalillos(numeroFilosofo);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}