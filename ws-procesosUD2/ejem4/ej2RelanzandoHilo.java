package ejem4;

class hiloSleep extends Thread {

	public void run() {

		System.out.println("[hilo]:\tDormimos un rato.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}
		System.out.println("[hilo]:\tTerminamos.");

	}

}

public class ej2RelanzandoHilo {

	public static void main(String[] args) throws InterruptedException {

		hiloSleep hs = new hiloSleep();
		System.out.println("[principal]: Lanzando el hilo...");
		hs.start();
		System.out.println("[principal]: Dormimos un rato.");
		Thread.sleep(5000);
		// Hemos dado tiempo suficiente para que el hilo termine.
		System.out.println("[principal]: Relanzamos el hilo.");
		hs.start();
		//hs.run();
		// Bucle infinito.
		while(true)
			;
	} 
} 