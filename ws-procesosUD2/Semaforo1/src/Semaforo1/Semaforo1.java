package Semaforo1;

import java.util.concurrent.Semaphore;

// Asegura que el hilo 2 -  se ejecute ante que el 1
// Usando semaforos

public class Semaforo1 {

	public static void main(String args[]) {
		
		// Crecion del recurso compartdio
		SemaforoGestor semaforoGestor = new SemaforoGestor();
		
		// Creacion de los dos hilos
		Thread hilo1 = new Thread(new Hilo(1, semaforoGestor));
		Thread hilo2 = new Thread(new Hilo(2, semaforoGestor));

		hilo1.start();;
		hilo2.start();
	}
}

class SemaforoGestor {
	// Creacion del semaforo - 1 paso a la vez
	private Semaphore semaforoHilo = new Semaphore(0);

	public void saludar(int numeroHilo) throws InterruptedException {

		if (numeroHilo == 1) {

			semaforoHilo.acquire(); // Pide permiso
			System.out.println("Hola soy el hilo " + numeroHilo);

		} else {

			System.out.println("Hola soy el hilo " + numeroHilo);
			semaforoHilo.release(); // Doy +1 al semaforoHilo1

		}

	}
}

class Hilo implements Runnable {

	private int numeroHilo;
	private SemaforoGestor semaforoGestor;

	// Constructor
	public Hilo(int numeroHilo, SemaforoGestor semaforoGestor) {

		this.numeroHilo = numeroHilo;
		this.semaforoGestor = semaforoGestor;
	}

	@Override
	public void run() {

		try {
			semaforoGestor.saludar(numeroHilo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
