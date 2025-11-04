package n1;


public class hiloHola_Runnable implements Runnable {
	Thread t;
	
	public hiloHola_Runnable () {
		t = new Thread (this, "Hilo nuevo");
		System.out.println("Creado hilo: " + t);
		t.start();
	}
	
	public void run () {
		System.out.println("Hola desde el hilo creado! ");
		System.out.println("Hilo finalizado ");
	}
}
