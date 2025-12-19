package Examen2012;

public class Examen2012 {

	public static void main(String args[]) {

		GestorNumeros gest = new GestorNumeros();

		Thread hilo = new Thread(new Hilo(gest, 0), "Siguiente");
		hilo.start();

		Thread hil1 = new Thread(new Hilo(gest, 1), "Par");
		hil1.start();

		Thread hilo2 = new Thread(new Hilo(gest, 2), "Impar");
		hilo2.start();

	}
}

class GestorNumeros {
	private int numero = 1;
	private boolean parProcesado = false;
	private boolean imparProcesado = false;
	private final int MAX = 10; // máximo número a generar

	public void iniciar(int tipo) throws InterruptedException {
		if (tipo == 0) {
			siguiente();
		} else if (tipo == 1) {
			esPar();
		} else if (tipo == 2) {
			esImpar();
		}

	}

	public synchronized void siguiente() throws InterruptedException {

		numero++;
		// Reset
		parProcesado = false;
		imparProcesado = false;
		System.out.println(Thread.currentThread().getName() + " Contador: " + numero);
		notifyAll();
	}

	public synchronized void esPar() throws InterruptedException {
		while (numero % 2 != 0 || parProcesado) {
			wait();
		}
		parProcesado = true;
		System.out.println(Thread.currentThread().getName() + " Es par --> " + numero);
	}

	public synchronized void esImpar() throws InterruptedException {
		while (numero % 2 == 0 || imparProcesado) {
			wait();
		}
		imparProcesado = true;
		System.out.println(Thread.currentThread().getName() + " Es impar --> " + numero);
	}
}

class Hilo implements Runnable {

	private GestorNumeros gestor;
	private int tipo; // 0=siguiente, 1=par, 2=impar

	public Hilo(GestorNumeros gestor, int tipo) {
		this.gestor = gestor;
		this.tipo = tipo;
	}

	@Override
	public void run() {

		try {
			while (true) {
				gestor.iniciar(tipo);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
