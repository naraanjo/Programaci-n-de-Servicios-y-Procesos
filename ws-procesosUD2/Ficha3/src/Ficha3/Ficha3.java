package Ficha3;

import java.util.ArrayList;
import java.util.Random;

public class Ficha3 {

	public static void main(String args[]) throws InterruptedException {
		
		// Creador del recurso compartido
		GestorTestigo gestorTestigo = new GestorTestigo();
		
		// Lista para almacenar los hilos
		ArrayList<Thread> listaHilos = new ArrayList<Thread>();
		
		// Creacion de 4 hilos
		for(int i = 1; i < 5; i++ ) {
			Thread corredor = new Thread(new Corredor(i, gestorTestigo));
			corredor.start();
			
			listaHilos.add(corredor);
		}
		
		// Join despues de crear todos
		for(int i = 0; i < 4; i++ ) {
			
			listaHilos.get(i).join();
		}
		
		System.out.println("Carrera finalizada");
	}
}

// Recurso compartido
class GestorTestigo {

	// Turno que se debe de seguir al coger el relevo
	int turno = 1;

	public void cogerTestigo(int numeroCorredor) throws InterruptedException {

		synchronized (this) {
			// Si no es su turno a esperar
			while (numeroCorredor != turno) {
				wait();
			}
			System.out.println("Soy el corredor " + numeroCorredor + ", corriendo");
		}

		// Corre un tiempo random
		Random random = new Random();
		Thread.sleep(random.nextInt(5000 - 1000 + 1) + 1000); // "Corre"

		
		
		
		synchronized (this) {
			turno++;
			if (numeroCorredor != 4) {
				System.out.println("Termine. Paso el testigo al corredor " + (numeroCorredor + 1));

			} else {
				System.out.println("Termine. Soy el ultimo corredor");

			}
			
			System.out.println("TURNO: " + turno);
			notifyAll(); // Saco del wait a los que esperan
		}

	}

}

// Corredor --> Se van pasando el testigo
class Corredor implements Runnable {

	private int numeroCorredor;
	private GestorTestigo gestorTestigo;

	// Constructor
	public Corredor(int numeroCorredor, GestorTestigo gestorTestigo) {

		this.numeroCorredor = numeroCorredor;
		this.gestorTestigo = gestorTestigo;
	}

	@Override
	public void run() {
		
		// Llamo a la funcion del monitor
		try {
			gestorTestigo.cogerTestigo(numeroCorredor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}