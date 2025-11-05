package n1;

public class holaMundo_Thread {

	public static void main (String args[]) {
		new hiloHola_Thread().start();
		new hiloHola_Thread().start();
		System.out.println("Hola desde el hilo principal!");
		System.out.println("Proceso principal finalizado.");
	}
}
