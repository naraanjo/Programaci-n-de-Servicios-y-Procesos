package Temperatura;

import java.util.ArrayList;
import java.util.Random;

// Capacidad estandar de 50 personas
//Si la t>30, el numero de personas se limita a 35. Si se detecta
// cuando el numero de personas > 35, no se desalojan

// Persona jubilidad tiene prioridad, para entrar
// La temperatura se debe notificar
public class Temperatura {
	
	public static void main(String args[]) {
		
		GestorSala gestor = new GestorSala();
		
		for(int i = 0 ; i < 100; i++) {
			Thread h = new Thread(new Persona("NoJubilado", gestor, i));
			h.start();
		}
		
		for(int i = 0 ; i < 10; i++) {
			Thread h = new Thread(new Persona("jubilado", gestor, i));
			h.start();
		}
		
		Thread t = new Thread(new Termometro(0, gestor));
		t.start();
	}

}

// Clase para gestionar la sala
class GestorSala {
	int capacidadActual =0; // Por defecto no hay nadie
	int jubiladoEntrar = 0;
	int temperatura = 0;
	
	ArrayList<Integer> ordenIndividuos = new ArrayList<Integer>();
	
	public void iniciar(String tipo, int id) throws InterruptedException {
		
		if(tipo.equals("jubilado")) {
			accederJubilado(id);
		}else if(tipo.equals("NoJubilado")) {
			accederPersonaNoJubilidad(tipo, id);
		}
		
	}
	
	public void cambioTemperatura() throws InterruptedException {
			
		Thread.sleep(4000);
		
		synchronized (this) {
			Random aleator = new Random();
			int t = aleator.nextInt(70 - 20 + 1) + 20; // 20 a 70
			temperatura = t;
			System.out.println("Cambio de temperatura!!!!!!!!!!!!!!!!!!!: " + t);
			notifyAll();
		}
		
	}
	
	public void accederPersonaNoJubilidad(String tipo, int id) throws InterruptedException {
		
		synchronized (this) {
			// Orden de entrar, para que no se cuelen
			ordenIndividuos.add(id);
			
			while((temperatura > 30 && capacidadActual>= 35) || capacidadActual>=50 || ordenIndividuos.get(0)!=id || jubiladoEntrar>0) {
				wait();
			}
			ordenIndividuos.remove(0);
			capacidadActual++; // Aumento las personas dentro del museo
			// Aqui ya accede al museo
			System.out.println("Persona-"+id+", accede al museo");
		}
		Random aleator = new Random();
		int tiempo = aleator.nextInt(4000-1000+1)+1000;
		Thread.sleep(tiempo);// Tiempo en el museo
		
		synchronized (this) {
			System.out.println("Persona-"+id+", sale del museo");
			System.out.println("PERSONAS DENTRO: "+ capacidadActual);
			capacidadActual--; // Sale la persona del museo
			notifyAll(); // Notifico
		}
	}
	
	public void accederJubilado(int id) throws InterruptedException {
		
		synchronized (this) {
			// Jubilado quiere acceder
			jubiladoEntrar++; // Incremento el numero de jubilados que queire entrar
			
			while((temperatura > 30 && capacidadActual>= 35) || capacidadActual>=50 ) {
				wait();
			}
			capacidadActual++; // Incremento el numero de personas en el museo
			System.out.println("Jubiliado-"+id+", accediendo del museo");
			

		}
		
		Random aleator = new Random();
		int tiempo = aleator.nextInt(4000-1000+1)+1000;
		Thread.sleep(tiempo);// Tiempo en el museo
		
		synchronized (this) {
			System.out.println("Jubiliado-"+id+", saliendo del museo");
			System.out.println("PERSONAS DENTRO: "+ capacidadActual);
			capacidadActual--;
			jubiladoEntrar--;
			notifyAll(); // Notifico a los demas
		}
	}

}

class Persona implements Runnable {

	private String tipo;
	private int id;
	private GestorSala gestor;

	public Persona(String tipo, GestorSala gestor, int id) {
		this.tipo = tipo;
		this.gestor = gestor;
		this.id = id;
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

class Termometro implements Runnable {

	private int temperatura;
	private GestorSala gestor;

	public Termometro(int temperatura, GestorSala gestor) {
		this.temperatura = temperatura;
		this.gestor = gestor;
	}

	@Override
	public void run() {
		try {
			while(true) {
				gestor.cambioTemperatura();

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
