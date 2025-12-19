package Junio2013;

import java.util.ArrayList;

// Tras el despege de un avion esperar 5s
// Tras el de una avioneta esperar 3s

// No despguen avionetas consecutivas, SI HAY aviones esperando

public class Junio2013 {

	public static void main(String args[]) {
		GestorAeropuesrto gestor = new GestorAeropuesrto();

		for (int i = 0; i < 10; i++) {
			Thread h1 = new Thread(new Vehiculo("avion", i, gestor));
			h1.start();

			Thread h2 = new Thread(new Vehiculo("avioneta", i, gestor));
			h2.start();
		}
	}

}

class GestorAeropuesrto {

	int avionesEsperando = 0; // Por defecto no hay
	int turno = 0; // 0 quien sea 1 avion 2 avioneta 
	boolean pistaOcupada = false; // Por defecto libre
	ArrayList<Integer> turnoAvion = new ArrayList<Integer>();

	public void iniciar(String tipo, int id) throws InterruptedException {

		if (tipo.equals("avion")) {
			despegarAvion(id);
		} else if (tipo.equals("avioneta")) {
			despegarAvioneta(id);
		}
	}

	public void despegarAvioneta(int id) throws InterruptedException {

		synchronized (this) {
			System.out.println("Avioneta-" + id + ", en espera");

			// Si hay aviones - avioneta espra
			while (avionesEsperando > 0 || pistaOcupada || turno ==1) {
				wait();
			}
			pistaOcupada = true;
			System.out.println("Avioneta-" + id + ", despegando");
		}
		Thread.sleep(3000); // Despegue 3s

		synchronized (this) {
			System.out.println("Avioneta-" + id + ", libera pista");
			pistaOcupada = false; // Libera la pista
			if(avionesEsperando> 0) turno =1; else turno=2;
			notifyAll(); // Notifico a los demas
		}

	}

	public void despegarAvion(int id) throws InterruptedException {

		synchronized (this) {
			avionesEsperando++;
			turnoAvion.add(id);
			System.out.println("Avion-" + id + ", en espera");

			// Espera si esta ocupada o no le toca
			while (pistaOcupada || turnoAvion.get(0) != id || turno ==2) {
				wait();
			}
			pistaOcupada = true; // Ocupa la pista
			turnoAvion.remove(0);
			avionesEsperando--; // Lo quito de la espera
			System.out.println("Avion-" + id + ",despegando");
		}

		Thread.sleep(5000); // 5s de despegue

		synchronized (this) {
			pistaOcupada = false; // Dejo la pista libre
			System.out.println("Avion-" + id + ", libera la pista");
			
			if(avionesEsperando> 0) turno =1; else turno=2;
			notifyAll();
		}
	}
}

class Vehiculo implements Runnable {

	private String tipo;
	private int id;
	GestorAeropuesrto gestor;

	public Vehiculo(String tipo, int id, GestorAeropuesrto gestor) {
		this.tipo = tipo;
		this.id = id;
		this.gestor = gestor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			gestor.iniciar(tipo, id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}