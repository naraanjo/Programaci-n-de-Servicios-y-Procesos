package inicioHilos;

import java.util.ArrayList;

public class Relevos {

	public static void main(String[] args) throws InterruptedException {

		// Recurso compartido
		Testigo testigo = new Testigo();
		ArrayList<Thread> listaHilos = new ArrayList<Thread>();
		// Creo los 4 corredores
		for (int i = 1; i < 5; i++) {

			Thread corredor = new Thread(new Corredor("Corredor-" + i, testigo, i));
			listaHilos.add(corredor); // Almaceno todos los hilos
		}
		System.out.println("Todos los hilos creados");
		System.out.println("Doy la salida");

		// Arranco todos los hilos
		for (Thread thread : listaHilos) {
			thread.start();

		}
	}

}

// Recurso compartido
class Testigo {

	private static int turno = 1;

	public synchronized void correr(String nombre, int numero) throws InterruptedException {

		// Si no es su turno espera - liberando el monitor
		while (turno != numero) {
			wait();
		};

		// Caso de que sea su turno
		System.out.println("Soy el " + nombre + " , corriendo...");
		Thread.sleep(1000);
		System.out.println("Termine paso al corredor siguiente --> " + "Corredor siguiente: " + (turno+1));

		// Siguiente turno
		turno++;

		// Termina y avisa a los demas de que ha terminado 
		notifyAll();
	}

}

class Corredor implements Runnable {

	private String nombre;
	private Testigo testigo;
	private int numero;

	public Corredor(String nombre, Testigo testigo, int numero) {
		this.nombre = nombre;
		this.testigo = testigo;
		this.numero = numero;
	}

	@Override
	public void run() {
		try {
			testigo.correr(nombre, numero);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
