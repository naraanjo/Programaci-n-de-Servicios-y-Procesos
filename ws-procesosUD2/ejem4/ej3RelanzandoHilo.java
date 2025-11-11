package ejem4;

class hiloRelanzado extends Thread {

	public void run() {

		System.out.println("[hilo]:\tDormimos un rato.");
		try {
			Thread.sleep(1000);
			System.out.println(this.getState());
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}
		System.out.println("[hilo]:\tTerminamos.");
		
	}

}

public class ej3RelanzandoHilo {

	public static void main(String[] args) throws InterruptedException {

		hiloRelanzado hr = new hiloRelanzado();
		System.out.println("[main]: Lanzando el hilo...");
		hr.start();
		do {
			System.out.println("[main]: Dormimos un rato.");
			Thread.sleep(500);
		} while(hr.getState() != Thread.State.TERMINATED);
		System.out.println("[main]: Relanzamos el hilo.");
		hr.start();

		// Bucle infinito.
		while(true)
			;

	} 
	
}