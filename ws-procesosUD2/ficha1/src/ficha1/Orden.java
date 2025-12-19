package ficha1;

public class Orden {

	public static void main(String args[]) {
		// Creacion del recurso compartido
		GestorOrden gestorOrden = new GestorOrden();
		
		// Creacion de dos hilos
		Thread hilo1 = new Thread(new Hilo(gestorOrden, true, 1));
		Thread hilo2 = new Thread(new Hilo(gestorOrden, false,2));
		
		// Arranco los hilos
		hilo1.start();
		hilo2.start();
	}

}


// Creacion de un monitor
class GestorOrden{
	Boolean pasaElSegundo = false;

	public synchronized void saludar(Boolean isPrimero, int numeroHilo) throws InterruptedException {
		
		// Si es el primero lo mando a esperar
		// Cambio la variable booleana al pasar el hilo-2
		while (!pasaElSegundo && isPrimero) {
			wait(); // Espera hasta un notify
		}
		
		// Aqui ya entra
		System.out.println("Hola, soy el thread numero " + numeroHilo);
		pasaElSegundo = true;
		notifyAll(); // Notifico al otro hilo que deje de esperar
		
	}
}





// Clase del hilo
class Hilo implements Runnable{
	
	
	
	// Atributos 
	private GestorOrden gestorOrden;
	private Boolean isPrimero;
	private int numeroHilo;

	// Constructor
	public Hilo( GestorOrden gestorOrden, Boolean isPrimero, int numeroHilo) {
		
		this.gestorOrden = gestorOrden;
		this.isPrimero = isPrimero;
		this.numeroHilo = numeroHilo;
	}
	
	
	@Override
	public void run() {
		
		try {
			gestorOrden.saludar(isPrimero, numeroHilo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}