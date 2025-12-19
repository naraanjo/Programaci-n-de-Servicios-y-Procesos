package inicioHilos;

// Simula una cola unica para poder acceder a todas las cajas
// no eliges la cola, cuando hay una libre y te toca vas a esa


public class ModernMarket {
	
	public final static int  numeroDeCajas = 3;
	public final static int numeroDeClientes = 30;
	
	public  static void main(String args[]) {
		
		// Creacion del recurso compartido
		Gestorcaja gestoCajaa = new Gestorcaja(numeroDeCajas);
		
		// Creacion de todos los clientes
		for(int i = 0; i < numeroDeClientes; i ++) {
			
			Thread cliente = new Thread(new Client(i, gestoCajaa));
			cliente.start();
		}
		
		
	} 

}

// Recurso compartido
class Gestorcaja{
	
	// Array booleano con el estado de todas las cajas
	Boolean estaOcupada [];
	int turnoValido = 0;
	
	// Constructor del recurso compartido
	public Gestorcaja(int numeroCajas) {
		estaOcupada = new Boolean[numeroCajas];
		
		// Inicialmente pongo todas las cajas a false = estsaOcupada
		for(int i = 0; i < estaOcupada.length; i++) {
			estaOcupada[i] = false; // Esta ocupada = false (libre)
		}
	}
	
	// Metodo para acceder a una caja
	public void clienteAccedeCaja(int turno) throws InterruptedException {
		
	int ocupo=-1;
		// Solo pueden acceder de uno en uno 
		// A cambiar el estado de las cajas
		synchronized (this) {
			
			// Si no es el turno del cliente espera
			while (turno!=turnoValido || (ocupo=buscarCajaLibre()) == -1)  {
				wait(); // Espera hasta que alguien lo notifique
			}
			
			// Ocupo la caja encontrada linre
			estaOcupada[ocupo] = true;
			turnoValido++;
			notifyAll();
			// Estas lineas pueden acceder varios clientes a la vez
			System.out.println("Cliente con codigo --> " + turno + ", accede a la caja");
		}
		
		
		Thread.sleep(1000);

		
		
		// Cambiar el estado de la caja de True a false
		// Unicamente un cliente a la vez
		// Recibe el numero de la caja
		synchronized (this) {
			System.out.println("Cliente con codigo --> " + turno + ", finalizo el pago");

			// Libero y notifico que dejo libre la caja
			estaOcupada[ocupo] = false; // Libero
			notifyAll(); // Notifico que ya esta liber --> activa los wait
			
			
			
		}
		
	
		
	}
	
	private int buscarCajaLibre() {

		// Recorro el estado de las cajas
		for(int i = 0; i<estaOcupada.length; i++) {
			
			// Compruebo que haya alguna caja libre
			if(estaOcupada[i] == false) { 
				
				return i;
				
			}
		}
		return -1;

	}
	
}


class Client implements Runnable{
	
	// Atributos
	private int turno; // Turno para ver si le toca o no acceder a la caja
	private Gestorcaja gestorCajaA;
	
	// Constructor
	public Client(int turno, Gestorcaja gestorcaja) {
		
		this.turno = turno;
		this.gestorCajaA = gestorcaja;
	}

	@Override
	public void run() {
		
		// Llamo hasta que no haya cliente
			try {
				gestorCajaA.clienteAccedeCaja(turno);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		
	}
}
