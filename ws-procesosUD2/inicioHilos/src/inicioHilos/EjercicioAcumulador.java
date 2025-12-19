package inicioHilos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

// Uso de syncronized (monitor)
// Varias hilos, acceden a un contador (recurso compartido)
// Para ello la operacion de sumar en el contador debe ser, syncronized
// para que solo la puedo realizar uno a la vez
public class EjercicioAcumulador {

	public static void main(String[] args) throws InterruptedException {

		ContadorCompartido contadorCompartido = new ContadorCompartido();

		// Lista de Thread
		ArrayList<Thread> listaThread = new ArrayList<Thread>();
		for (int i = 0; i < 1000; i++) {
			Thread hilo = new Thread(new Hilo(contadorCompartido), "Hilo-" + i);
			listaThread.add(hilo);
		}

		// Llamo al start de cada hilo
		for (int i = 0; i < 1000; i++) {
			listaThread.get(i).start();
		}
		
		// Esperar a que termine
		  for (Thread hilo : listaThread) {
		        hilo.join();
		    }
		
		// Contador final
		System.out.println("Contador compartido final esperado - " + (1000*999));
		
		System.out.println("Obtenido: " + contadorCompartido.contador);
	}
}

// Variable compartida
class ContadorCompartido {

	// Creacion del semaforo donde le indicamos que solo
	// puede acceder una persona a la vez
	// Deja pasar de 5 en 5 - en este ejemplo redundante
	// ya que luego hay un syncronized
	private Semaphore semaforo = new Semaphore(5);

	int contador = 0; // Empieza en 0

	public void sumarEnContador(String nombreHilo) throws InterruptedException {

		try {

			System.out.println(nombreHilo + ": Esta intentando acceder al recurso compartido");
			// Si el semaforo esta en 1 pasa
			semaforo.acquire();

			// Deja pasar de 1 en 1
			synchronized (this) {
				contador += 999;
				System.out.println(nombreHilo+ " - Ha sumado en el recurso compartido ");
			}

			// Libera el recurso y le suma uno
			System.out.println(nombreHilo + " - Ha salido del recurso compartido");
			semaforo.release();
		} catch (Exception e) {

		}
	}

}

class Hilo implements Runnable {

	private ContadorCompartido contadorCompartido;

	public Hilo(ContadorCompartido contadorCompartido) {
		this.contadorCompartido = contadorCompartido;
	}

	@Override
	public void run() {
		// Obtenemos el nombre del hilo actual
		String nombre = Thread.currentThread().getName();

		try {
			contadorCompartido.sumarEnContador(nombre);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
