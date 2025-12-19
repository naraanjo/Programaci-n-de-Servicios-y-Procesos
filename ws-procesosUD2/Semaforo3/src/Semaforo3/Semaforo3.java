package Semaforo3;

import java.util.concurrent.Semaphore;

// Todos los hilos deben llegar a un punto determinado antes de continuar

public class Semaforo3 {

	public static void main(String args[]) {

		// Gestor
		GestorBarrera gestor = new GestorBarrera();

		// Hilos
		for (int i = 0; i < 5; i++) {
			Thread hilo = new Thread(new Hilo(i, gestor));
			hilo.start();
		}

	}
}

class GestorBarrera {

	private Semaphore semaforo = new Semaphore(0);
	int contador = 0;

	public void avanzar(int numeroHilo) throws InterruptedException {

		synchronized (this) {
			contador++;
		}
		
		System.out.println("Hilo " + numeroHilo + " llegando a la barrera");

		if (contador == 5) {
			// Libero a todos
			for (int i = 0; i < 5; i++) {
				semaforo.release();
			}
			
		}

	}

	public  void pasarBarrera(int nHilo) throws InterruptedException {

			semaforo.acquire();
			System.out.println("Hilo " + nHilo + " pasando la barrera");

	}
}

class Hilo implements Runnable {

	private int numeroHilo;
	private GestorBarrera gestorBarrera;

	// Constructor
	public Hilo(int numeroHilo, GestorBarrera gestor) {
		this.numeroHilo = numeroHilo;
		this.gestorBarrera = gestor;
	}

	@Override
	public void run() {
		try {
			gestorBarrera.avanzar(numeroHilo);
			gestorBarrera.pasarBarrera(numeroHilo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
