package Molecula;

import java.util.concurrent.Semaphore;

public class Molecula {

	public static void main(String args[]) {
		CreadorMolecula creador = new CreadorMolecula();

		for (int i = 0; i < 10; i++) {
			Thread h = new Thread(new Atomo("H", creador));
			h.start();

			Thread a = new Thread(new Atomo("O", creador));
			a.start();
		}
	}

}

class CreadorMolecula {

	int contadorH = 0;
	int conradorO = 0;
	int contador = 0;
	private Semaphore semaforoH = new Semaphore(2);
	private Semaphore semaforoO = new Semaphore(1);
	private Semaphore semaforoCreacion = new Semaphore(1);
	private Semaphore moleculaJunta = new Semaphore(0);
	int atomos = 0;

	public void crearMoleculaH20(String tipo) throws InterruptedException {

		if (tipo.equals("H")) {

			semaforoH.acquire(); // Baja -1
			System.out.println("ATOMO H");
			construitMolecula();
		}

		if (tipo.equals("O")) {
			semaforoO.acquire(); // Baja -1
			System.out.println("ATOMO Ox");

			construitMolecula();
		}

	}

	public void construitMolecula() throws InterruptedException {
		semaforoCreacion.acquire();
		atomos++;
		if (atomos == 3) {

			System.out.println("MOLECULA CREADA");
			// Reset
			semaforoH.release(2);
			semaforoO.release(1);
			atomos =0;
			moleculaJunta.release(3);
		}
		semaforoCreacion.release();

		moleculaJunta.acquire();
	}

}

class Atomo implements Runnable {

	private String tipo;
	private CreadorMolecula creadorMolecula;

	public Atomo(String tipo, CreadorMolecula creadorMolecula) {
		this.tipo = tipo;
		this.creadorMolecula = creadorMolecula;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while(true) {
				creadorMolecula.crearMoleculaH20(tipo);

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
