package Semaforo2;

import java.util.concurrent.Semaphore;

// Array (Buffer de 5), productor escriber, consumidor lee
// Uso de semaforo 

public class Semaforo2 {

	public static void main(String args[]) {
		// Creacion del gestor
		GestorBuff gestorBuff = new GestorBuff();
		
		// Creacion de los hilos
		Thread consumidor = new Thread(new Persona("consumidor", gestorBuff));
		Thread productor = new Thread(new Persona("productor", gestorBuff));

		consumidor.start();
		productor.start();
	}
}


class GestorBuff{
	
	// Semaforo
	private Semaphore semaforoLeer = new Semaphore(1); 
	private Semaphore semaforoEscribir = new Semaphore(0); 

	// Buffer
	int listaBuffer[] = new int[5]; // Tamaño de 5
	int contador = 1;
	
	// Constructor
	public GestorBuff() {
		
		// Por defecto el buffer con 0
		for(int i =0; i< 4; i++) {
			listaBuffer[i] = 0;
		}
	}
	
	public void accederBuffer(String tipo) throws InterruptedException {
	
		while(contador < 6) {
			
			// Consumidor --> lee
			if(tipo.equals("consumidor")) {
				
				semaforoEscribir.acquire(); // Pide permiso
				
				System.out.println("Consumidor en accion (escribir)");

				
				// Entra y escribe
				for(int i = 0 ; i < listaBuffer.length; i++) {
					listaBuffer[i] = contador;
				}
				contador++; // Aumento para rellenar con el siguiente numero
				semaforoLeer.release(); // Da acceso 
					
				
			}else {
				// Productor --> escribe
				// Lee
				semaforoLeer.acquire();
				
				System.out.println("Productor en accion (lee)");
				for(int i = 0 ; i < listaBuffer.length; i++) {
					System.out.print(listaBuffer[i]); // Saco los valores - leer
				}
				System.out.println();
			
				semaforoEscribir.release(); // Da acceso a escribir
				
			}
		}
		
	
	}
}

class Persona implements Runnable{
	
	private String tipo;
	private GestorBuff gestorBuff;
	
	
	// Constructor
	public Persona(String tipo, GestorBuff gestorBuff){		
		this.tipo = tipo;
		this.gestorBuff = gestorBuff;
	}


	@Override
	public void run() {
		
		try {
			gestorBuff.accederBuffer(tipo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}