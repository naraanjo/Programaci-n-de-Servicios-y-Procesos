package n1;

public class holaMundo_Runnable {

	public static void main (String args[]) {
		new hiloHola_Runnable();
		new hiloHola_Runnable();
		System.out.println("Hola desde el hilo principal!");
		System.out.println("Proceso principal finalizado.");
	}
}
