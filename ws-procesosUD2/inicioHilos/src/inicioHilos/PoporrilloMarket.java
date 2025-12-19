package inicioHilos;

public class PoporrilloMarket {

	public static final int numeroCajas = 5;
	public static final int numeroClientes = 25;

	public static void main(String[] args) {

		// Recurso compartido
		GestorCajas2 gestorCajas2 = new GestorCajas2();

		// Creacion de los clientes y arranco los hilos
		for (int i = 0; i < numeroClientes; i++) {
			Thread cliente = new Thread(new ClienteP(i, gestorCajas2));
			cliente.start();
		}

	}
}

// Cajas es el recurso compartido
// entre todos los clientes
class GestorCajas2 {

	// Array de boolen con el estado de cada caja
	// Inicialmente false
	Boolean cajasOcupadas[] = new Boolean[PoporrilloMarket.numeroCajas]; // Cambiar por el numero de cajas

	// Constructor del recurso compartido
	public GestorCajas2() {

		// Inicialmente todas a false
		for (int i = 0; i < cajasOcupadas.length; i++) {
			cajasOcupadas[i] = false;
		}
	}

	// Metodo cliente --> realiza compra
	public void accedeCaja(int turnoCliente) throws InterruptedException {
		int cajaLibre = -1;

		
			synchronized (this) {
				while(true) {
				// Recorro las cajas - y su estado
				for (int i = 0; i < cajasOcupadas.length; i++) {

					// Compruebo si la caja esta libre
					if (cajasOcupadas[i] == false) {
						// Pasa a ser ocupada
						cajasOcupadas[i] = true;

						cajaLibre = i;

						// Salgo del bucle - ocupando la caja
						break;

					}
				}

				// Encontramos caja - salimos del while
				if (cajaLibre != -1) {
					break;
				}

				// Si no hay caja libre esperar 
				wait();

				}
			}
			
			// Compra simulada fuera del synchronized
			// Para poder realizar varias compras simultaneamente
			System.out.println("Cliente-" + turnoCliente + " comprando en caja " + cajaLibre);
			Thread.sleep(1000);
			System.out.println("Cliente-" + turnoCliente + " terminó compra en caja " + cajaLibre);

			synchronized (this) {
				liberarCaja(cajaLibre);

			}
		}



	// Metodo para liberar la caja que estaba ocupada
	public synchronized void liberarCaja(int nCaja) {

		// Libero la caja
		cajasOcupadas[nCaja] = false; // Libero la caja

		// Notifico a los demas hilos que la caja esta libre
		notifyAll();
	}

	public synchronized void estadoCajas() {

		for (int i = 0; i < cajasOcupadas.length; i++) {
			System.out.println(" | " + cajasOcupadas[i]);
		}
	}

}

// Cliente que quiere acceder a una caja
class ClienteP implements Runnable {

	// Cliente tiene un turno
	int turnoCliente;
	// Recurso compartido
	GestorCajas2 gestorCajas;

	public ClienteP(int turnoCliente, GestorCajas2 gestorCajas) {

		this.turnoCliente = turnoCliente;
		this.gestorCajas = gestorCajas;

	}

	@Override
	public void run() {

		try {
			gestorCajas.accedeCaja(turnoCliente);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//
}