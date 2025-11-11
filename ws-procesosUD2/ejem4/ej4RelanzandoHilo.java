package ejem4;

class hiloRelanzado2 extends Thread {

	public void run() {

		System.out.println("[hilo]:\tDormimos un rato.");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}
		System.out.println("\n[hilo]:\tTerminamos.");
		
	} 

} 

public class ej4RelanzandoHilo {

	public static void main(String[] args) throws InterruptedException {

		hiloRelanzado2 hr = new hiloRelanzado2();
		System.out.println("[main]: Lanzando el hilo...");
		hr.start();
		System.out.println("[main]: Esperamos a que termine.");
		do {
			hr.join(1000);
			System.out.print(".");
		} while(hr.isAlive());
		System.out.println(hr.getState());
		System.out.println();
		System.out.println("[main]: Relanzamos el hilo.");
		hr.start();

		while(true)
			;

	} 
	
} 