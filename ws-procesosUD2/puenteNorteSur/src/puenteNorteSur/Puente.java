package puenteNorteSur;

import java.util.Random;


public class Puente {

	public static void main(String[] args) {

		GestionAccesoPuente gestor = new GestionAccesoPuente();

		for (int i = 0; i < 5; i++)
			new Thread(new Vehiculo(i, "sur", gestor)).start();

		for (int i = 5; i < 10; i++)
			new Thread(new Vehiculo(i, "norte", gestor)).start();
	}
}

// ======================================================
class GestionAccesoPuente {

	int enprocesoNorte=0;
	int enProcesoSur =0;
	int vehiculosEnPuente=0;
	int esperandoN =0;
	int turno = 0;
	int esperandoS=0;

	public void accederPuente(String dire, int id) throws InterruptedException {

		if(dire.equals("sur")) {
			accederDireccionSur( id);
		}else {
			accederDireccionNorte(id);
		}
	}

	public void accederDireccionNorte(int id) throws InterruptedException {

		
		
		synchronized (this) {
			System.out.println("Vehiculo-"+id+", quiere pasar (norte)");
			esperandoN++;
			System.out.println("ESPERANDO NORTE: " + esperandoN);
			// Compruebo
			while ( enProcesoSur >0 || (turno == 2 && esperandoS>0)) {
				wait();
			}
			
			vehiculosEnPuente++;
			// Aqui ya ha pasado
			System.out.println("Vehiculo-"+id+", accede (norte)");
			enprocesoNorte++;


		}
		
		// Tiempo en pasar
		Random aleator = new Random();
		int tiempo = aleator.nextInt(500-100+1)+000;
		Thread.sleep(tiempo);
		
		// Salida
		synchronized (this) {
			System.out.println("Vehiculo-"+id+", sale (norte)");
			esperandoN--;

			System.out.println("ESPERANDO NORTE: " + esperandoN);
			enprocesoNorte--;
			vehiculosEnPuente--;
			if (vehiculosEnPuente == 0 && esperandoS > 0) {
			    turno = 2; // ahora le toca al Sur
			}
			System.out.println("VEHICULOS EN EL PUENTE: " + vehiculosEnPuente);
			notifyAll();
			
		}
		
	}
	
	
	
	public void accederDireccionSur(int id) throws InterruptedException {

	
		
		synchronized (this) {
			System.out.println("Vehiculo-"+id+", quiere pasar (sur)");
			esperandoS++;
			System.out.println("ESPERANDO SUR: " +  esperandoS);
			// Compruebo
			while (( enprocesoNorte > 0 || (turno==1 && esperandoN>0)) ) {
				wait();
			}
		
			vehiculosEnPuente++;
			enProcesoSur++;

			// Aqui ya ha pasado
			System.out.println("Vehiculo-"+id+", accede (sur)");

		}
		
		// Tiempo en pasar
		Random aleator = new Random();
		int tiempo = aleator.nextInt(500-000+1)+000;
		Thread.sleep(tiempo);
		
		// Salida
		synchronized (this) {
			System.out.println("Vehiculo-"+id+", sale (sur)");
			esperandoS--;

			System.out.println("ESPERANDO SUR: " +  esperandoS);
			vehiculosEnPuente--;
			enProcesoSur--;
			if (vehiculosEnPuente == 0 && esperandoN > 0) {
			    turno = 1; // ahora le toca al Norte
			}
			System.out.println("VEHICULOS EN EL PUENTE: " + vehiculosEnPuente);
			notifyAll();
			
		}
		
	}

}

// ======================================================
class Vehiculo implements Runnable {

	private int id;
	private String direccion;
	private GestionAccesoPuente gestor;

	public Vehiculo(int id, String direccion, GestionAccesoPuente gestor) {
		this.id = id;
		this.direccion = direccion;
		this.gestor = gestor;
	}

	@Override
	public void run() {
		try {
			gestor.accederPuente(direccion, id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
