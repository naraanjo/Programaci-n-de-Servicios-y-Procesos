package Julio2013;

import java.util.Random;

public class Julio2013 {

// 2 entradas este y Oeste
// Si esta libre, las dos estan abiertas
// Si esta lleno, se alternar los accesos

	public static void main(String args[]) {


		GestorParking gestor = new GestorParking();

		for (int i = 0; i < 7; i++) {
			Thread h1 = new Thread(new Coche(i, gestor));
			h1.start();
		}

		for (int i = 7; i < 14; i++) {
			Thread h2 = new Thread(new Coche(i, gestor));
			h2.start();
		}
	}
}

class GestorParking {

	int plazasLibres = 5; // Todas libres por defecto
	int turno = 0; // 0 da igual, 1este, 2 oeste

	
	public void iniciar(int id) throws InterruptedException {
		
		if(plazasLibres > 0) {
			// SI HAY HUECO SE PASA POR CUALQUIER LADO
			// DE FORMA ALEATORIA
			Random aleator = new Random();
			int opcion = aleator.nextInt(2-1+1)+1; // 1 o 2
			
			if(opcion == 1) accederEsteLibre(id);
			if(opcion == 2) accederOesteLibre(id);
		}else {
			
			// Si no hay hueco al que le toque
			if(turno == 1) {
				accederEste(id);
			}else if(turno == 2) {
				accederOeste(id);
			}
			
		}
		
		
	}
	public void accederEsteLibre(int id) throws InterruptedException {
	    synchronized (this) {
	        System.out.println("Coche-" + id + ", quiere pasar (ESTE) [LIBRE]");
	        plazasLibres--;
	        System.out.println("Coche-" + id + ", ha pasado (ESTE) [LIBRE]");
	    }

	    Thread.sleep(2000);

	    synchronized (this) {
	        plazasLibres++;
	        System.out.println("Coche-" + id + ", ha salido (ESTE) [LIBRE]");
	        System.out.println("PLAZAS LIBRES: " + plazasLibres);
	        notifyAll();
	    }
	}

	public void accederOesteLibre(int id) throws InterruptedException {
	    synchronized (this) {
	        System.out.println("Coche-" + id + ", quiere pasar (OESTE) [LIBRE]");
	        plazasLibres--;
	        System.out.println("Coche-" + id + ", ha pasado (OESTE) [LIBRE]");
	    }

	    Thread.sleep(2000);

	    synchronized (this) {
	        plazasLibres++;
	        System.out.println("Coche-" + id + ", ha salido (OESTE) [LIBRE]");
	        System.out.println("PLAZAS LIBRES: " + plazasLibres);
	        notifyAll();
	    }
	}

	
	public void accederEste(int id) throws InterruptedException {

		synchronized (this) {
			System.out.println("Coche-" + id + ", quiere pasar (ESTE)");
			// No hay hueco - o pasa Oeste
			while (plazasLibres == 0 || turno == 2) {
				wait();
			}
			plazasLibres--;
			System.out.println("Coche-" + id + ", ha pasado (ESTE)");
		}
		turno = 2;

		Thread.sleep(2000);

		synchronized (this) {
			plazasLibres++;
			System.out.println("Coche-" + id + ", ha salido (ESTE)");
			System.out.println("PLAZAS LIBRES: " + (plazasLibres));
			notifyAll();
		}
	}

	public void accederOeste(int id) throws InterruptedException {

		synchronized (this) {
			System.out.println("Coche-" + id + ", quiere pasar (OESTE)");
			// No hay hueco - o pasa este
			while (plazasLibres == 0 || turno == 1) {
				wait();
			}
			plazasLibres--;
			System.out.println("Coche-" + id + ", ha pasado (OESTE)");

		}
		turno = 1;

		Thread.sleep(2000);

		synchronized (this) {
			plazasLibres++;
			System.out.println("Coche-" + id + ", ha salido (OESTE)");
			System.out.println("PLAZAS LIBRES: " + (plazasLibres));

			notifyAll();
		}
	}
}

class Coche implements Runnable {

	private int id;
	private GestorParking gestor;
	
	public Coche(int id, GestorParking gestor) {
		this.id = id;
		this.gestor = gestor;
	}

	@Override
	public void run() {
		try {
			gestor.iniciar(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
