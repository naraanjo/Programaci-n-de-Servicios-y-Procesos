package Puente;

import java.util.ArrayList;

// Si hay vehiculos en los dos accesos, entran donde haya mas
// si hay ambulancia pasa ella
public class Puente {

	public static void main(String[] args) {
		GestionarPuente gest = new GestionarPuente();
		
		for (int i = 10; i < 20; i++) {
			Thread hilo = new Thread(new Vehiculo("S", gest, i));
			hilo.start();
		}

		for (int i = 0; i < 10; i++) {
			Thread hilo = new Thread(new Vehiculo("N", gest, i));
			hilo.start();
		}

		Thread hilo2 = new Thread(new Vehiculo("AMB", gest, 22));
		hilo2.start();
		Thread hilo1 = new Thread(new Vehiculo("AMB", gest,33));
		hilo1.start();
		
		
		
	}
}

class GestionarPuente {
	// Pasan de 1 en 1, los demas esperar
	int vehiculoNorte = 0;
	int vehiculoSur = 0;
	int ambulanciaPasando = 0;
	int turnoNorte = 0; //0 defect - 1 norte 2suir
	boolean puenteOcupado = false;
	ArrayList<Integer> turnosSur = new ArrayList<Integer>();
	ArrayList<Integer> turnosNorte = new ArrayList<Integer>();

	public void iniciar(int id, String tipo) throws InterruptedException {

		if (tipo.equals("N")) {
			accederNorte(id);
		} else if (tipo.equals("S")) {
			accederSur(id);
		}else {
			pasaAmbulancia();
		}
	}
	
	public  void  pasaAmbulancia() throws InterruptedException {

		synchronized (this) {
			System.out.println("AMBULANCIA QUIERE PASARRRRRRR");
			ambulanciaPasando ++;

			while(puenteOcupado) {
				wait();
			}
			puenteOcupado = true;
			System.out.println("AMBULANCIA ENTRANDOOO");
			
		}
		
		Thread.sleep(1000);
		
		synchronized (this) {
			ambulanciaPasando --;
			puenteOcupado = false;
			System.out.println("AMBULANCIA SALIENDOOOO");
			notifyAll();
		}
	}

	public void accederNorte(int id) throws InterruptedException {

		synchronized (this) {
			System.out.println("Vehiculo-" + id + ", quiere entrar (norte)");
			turnosNorte.add(id);

			vehiculoNorte++; // Quiere acceder al puente
			// Si hay mas vehiculos en Sur, Norte esperar
			while (turnosNorte.get(0) != id || puenteOcupado || turnoNorte==2 || ambulanciaPasando>0) {
				wait();
			}
			vehiculoNorte--;

			System.out.println("Vehiculo-" + id + ", entra al puente (norte)");
			turnosNorte.remove(0);
			puenteOcupado = true;
		}

		// Aqui pasa norte
		Thread.sleep(1200);

		synchronized (this) {
			puenteOcupado = false;
			System.out.println("Vehiculo-" + id + ", saliendo del puente (norte)");

			if (vehiculoNorte > vehiculoSur) {
				turnoNorte = 1;
			} else if(vehiculoSur > vehiculoNorte){
				turnoNorte = 2;
			}else if(turnoNorte==1) {
				turnoNorte=2;
			}else {
				turnoNorte = 1;
			}

			notifyAll(); // Notifico a los demas vehiculos


		}
	}

	public void accederSur(int id) throws InterruptedException {

		synchronized (this) {
			System.out.println("Vehiculo-" + id + ", quiere entrar (sur)");
			turnosSur.add(id);

			vehiculoSur++; // Quiere acceder al puente
			// Si hay mas vehiculos en Norte , espera sur
			while (turnoNorte==1 || turnosSur.get(0) != id || puenteOcupado || ambulanciaPasando>0) {
				wait();
			}
			turnosSur.remove(0);
			System.out.println("Vehiculo-" + id + ", entra al puente (sur)");
			vehiculoSur--;

			puenteOcupado = true;
		}

		// Aqui pasas sur
		Thread.sleep(1200);

		synchronized (this) {
			puenteOcupado = false;
			System.out.println("Vehiculo-" + id + ", saliendo del puente (sur)");
			
			if (vehiculoNorte > vehiculoSur) {
				turnoNorte = 1;
			} else if(vehiculoSur > vehiculoNorte){
				turnoNorte = 2;
			}else if(turnoNorte==1) {
				turnoNorte=2;
			}else {
				turnoNorte = 1;
			}

			notifyAll(); // Notifico a los demas vehiculos

		}
	}

}

// Clase del vehiculo
class Vehiculo implements Runnable {
	private String tipo;
	int id;
	private GestionarPuente gestorPuente;

	// Constructor
	public Vehiculo(String tipo, GestionarPuente gestor, int id) {
		this.tipo = tipo;
		this.gestorPuente = gestor;
		this.id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			gestorPuente.iniciar(id, tipo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}