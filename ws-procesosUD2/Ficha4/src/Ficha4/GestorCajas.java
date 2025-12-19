package Ficha4;

// Recurso compartido para todos los clientes

import java.util.Random;

class GestorCajas{
	
	// Array con el estado de las cajas
	Boolean estaLibre [];
	int numeroCaja;
	int pago;
	
	// Construcor
	public GestorCajas(int nCajas) {
		this.estaLibre = new Boolean[nCajas];
		
		// Todas a true por defecto
		for(int i = 0; i < estaLibre.length; i++) {
			estaLibre[i] = true;
		}
	}
	
	// Control de acceso a las cajas
	public void accederCaja(int numeroCliente) throws InterruptedException {
		
	
		synchronized (this) {
			// Si no hay caja libre - Espera
			while((numeroCaja = cajaLibre()) == -1) {
				wait(); // Esperar
			}
			// Aqui ya ha accedido a alguna caja libre
			System.out.println("Cliente " + numeroCliente + ", esta comprando...");
		}
		
		// Tiempo de compra
		Random aleator = new Random();
		Thread.sleep(aleator.nextInt(5000-1000+1)+1000); // Entre 1s - 5s comprando
		
		// Saliendo de la caja - finaliza compra
		synchronized (this) {
			pago+= aleator.nextInt(500-10+1)+10;
			System.out.println("Cliente " + numeroCliente + ", saliendo del supermercado. " + "Ingresos supermercado: " + pago );
			estaLibre[numeroCaja] = true; // Dejo la caja del cliente en libre
			notifyAll(); // Despierto al wait
		}
	}
	
	// Funcion para ver si hay alguna caja libre
	public synchronized int cajaLibre() {
		
		// Recorro el estado de las cajas
		for(int i = 0; i < estaLibre.length; i++) {
			// Caja libre
			if(estaLibre[i]) return i; // Devuelvo el numero de la caja
		}
		
		// Caso de que no haya ninguna caja libre
		return -1;
	}
	
	
}