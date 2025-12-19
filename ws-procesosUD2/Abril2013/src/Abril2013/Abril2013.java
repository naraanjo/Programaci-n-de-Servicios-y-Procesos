package Abril2013;

// Tres hilos
// H1 -> Recurso 1
// H2 -> Recurso 2 y 3
// H3 -> Recurso 1,2 y 3

// Los recursos solo puedens ser usados por un hilo a la vez
public class Abril2013 {

	public static void main(String args[]) {

		GestorRecursos gest = new GestorRecursos();

		for (int i = 1; i < 4; i++) {
			Thread hilo = new Thread(new Hilo(i, gest));
			hilo.start();
		}
	}
}

class GestorRecursos {

	// Inicialmente todos los recursos libres
	boolean ocupadoR1 = false;
	boolean ocupadoR2 = false;
	boolean ocupadoR3 = false;

	// Hilo 1 -> R1
	public void accesoR1(int idHilo) throws InterruptedException {

		synchronized (this) {
			// Compruebo que no lo este usando nadie
			while (ocupadoR1) {
				wait(); // Espera hasta que se libere R1
			}
			ocupadoR1 = true;
			// Acceso al recurso
			System.out.println("Hilo-1 --> accediendo al recurso R1");
		}

		// Tiempo que tiene el recurso
		Thread.sleep(2000);

		synchronized (this) {
			// Salida del recurso
			ocupadoR1 = false; // Libero R1
			System.out.println("Hilo-1 --> libera el recurso R1");
			notifyAll(); // Notifico a los que esperan el recurso
		}
	}

	// Hilo 2 -> R2-R3
	public void accesoR2_R3(int idHilo) throws InterruptedException {

		synchronized (this) {
			// Esperar mientras esten ocupados R2-R3
			while (ocupadoR2 || ocupadoR3) {
				wait();
			}
			ocupadoR2 = true;
			ocupadoR3 = true;
			// Acceso al recurso
			System.out.println("Hilo-2 --> accediendo al recurso R2-R3");
		}

		Thread.sleep(2000);

		synchronized (this) {
			// Salida del recurso
			ocupadoR2 = false;
			ocupadoR3 = false;
			System.out.println("Hilo-2 --> libera el recurso R2-R3");
			notifyAll(); // Notifico a los que esperan el recurso
		}
	}

	// Hilo 3 -> R1-R2-R2
	public void accesoR1_R2_R3(int idHilo) throws InterruptedException {

		synchronized (this) {
			// Compruebo que no lo este usando nadie
			while (ocupadoR1 || ocupadoR2 || ocupadoR3) {
				wait(); // Espera hasta que se libere R1-R2-R3
			}
			ocupadoR1 = true;
			ocupadoR2 = true;
			ocupadoR3 = true;

			// Acceso al recurso
			System.out.println("Hilo-3 --> accediendo al recurso R1-R2-R3");
		}

		Thread.sleep(2000);

		synchronized (this) {
			// Salida del recurso
			ocupadoR1 = false; // Libero R1
			ocupadoR2 = false; // Libero R2
			ocupadoR3 = false; // Libero R3
			System.out.println("Hilo-3 --> libera el recurso R1-R2-R3");
			notifyAll(); // Notifico a los que esperan el recurso
		}

	}

	public void iniciar(int idHilo) throws InterruptedException {

		if (idHilo == 1) {
			accesoR1(idHilo);
		} else if (idHilo == 2) {
			accesoR2_R3(idHilo);
		} else if (idHilo == 3) {
			accesoR1_R2_R3(idHilo);
		}

	}
}

class Hilo implements Runnable {

	// Atributos
	private int idHilo; // 1-2-3
	private GestorRecursos gestor;

	// Constructor
	public Hilo(int hilo, GestorRecursos gestor) {
		this.idHilo = hilo;
		this.gestor = gestor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			try {
				gestor.iniciar(idHilo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

}