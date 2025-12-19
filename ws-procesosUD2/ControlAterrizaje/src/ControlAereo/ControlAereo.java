package ControlAereo;

import java.util.ArrayList;

// Pista para aterrizajes y despegues
// aterrizaje tiene prioridad todal

// Si la pista esta ocupada nadie entra
// libre y estan aterrizando entran primero
// si quieren despegar, tiene que estar libre y sin nadie queriendo aterrizar
public class ControlAereo {

	public static void main(String args[]) {
		GestorPista gest = new GestorPista();
		
		for(int i =0; i < 5; i++) {
			Thread avion = new Thread(new Avion("aterrizar",gest,i));
			avion.start();
		}
		
		for(int i =5; i < 15; i++) {
			Thread avion = new Thread(new Avion("despegar",gest,i));
			avion.start();
		}
	}
}

class GestorPista {

	private boolean estaOcupada = false; // Por defecto libre
	private boolean esperandoAterrizaje = false;
	private int conteoEsperandoAterrizaje=0;
	int turno=0;
	ArrayList<Integer> turnoLlegada = new ArrayList<Integer>();
	
	public void accederPista(String tipo, int id) throws InterruptedException {

		// Aterrizajes
		if (tipo.equals("aterrizar")) {
			aterrizar(id);

		} else {
			// Despegues
			despegar(id);
		}
	}

	public void aterrizar(int id) throws InterruptedException {

		synchronized (this) {
			System.out.println("!!!!!AVISO!!!! - Avion quiere aterrizar: " + id);
			turnoLlegada.add(id);

			// Avion que espera aterrizar
			esperandoAterrizaje = true;
			conteoEsperandoAterrizaje++;
			// Mientras este ocupada a esperar
			while (estaOcupada || turnoLlegada.get(turno)!=id) {
				wait();
			}
			estaOcupada = true; // Ocupo la pista
			turno++;
			System.out.println("Avion-" + id + ", aterrizando");
		}

		// Aqui entra
		Thread.sleep(1000);

		synchronized (this) {
			System.out.println("Avion-" + id + ", finalizando aterrizaje..");
			estaOcupada = false; // Libero la pista
			conteoEsperandoAterrizaje--;
			esperandoAterrizaje = false;
			notifyAll();
		}
	}

	public void despegar(int id) throws InterruptedException {

		synchronized (this) {
			// Esperar si quieren despegar o esta ocupada
			while (estaOcupada || esperandoAterrizaje || conteoEsperandoAterrizaje>0) {
				wait();
			}

			// Aqui ya va a despegar
			System.out.println("Avion-" + id + ", despegando");
			estaOcupada = true; // Ocupo la pista
		}

		// Despegue
		Thread.sleep(2000);

		synchronized (this) {
			// Termina el despeguie
			estaOcupada = false; // Libero pista
			System.out.println("Avion-" + id + ", finalizando despegue..");
			notifyAll();

		}
	}

}

class Avion implements Runnable {

	// Atributos
	private String tipo;
	private int id;
	private GestorPista gestorPistaAereo;

	// Constrcutor
	public Avion(String tipo, GestorPista gest, int id) {

		this.tipo = tipo;
		this.gestorPistaAereo = gest;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			gestorPistaAereo.accederPista(tipo, id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}