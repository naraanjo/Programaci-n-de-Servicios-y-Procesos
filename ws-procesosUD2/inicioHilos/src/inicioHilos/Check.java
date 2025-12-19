package inicioHilos;

public class Check {

	public static void main(String[] args) {

		// Recurso compartido
		BufferCompartido bufCompartido = new BufferCompartido();

		// Creo un hilo-Escritor y uno lector
		// Escritor
		Thread escritor = new Thread(new Escritor(bufCompartido));
		// Lector
		Thread lector = new Thread(new Lector(bufCompartido));

		escritor.start();
		lector.start();
	}

}

// Buffer --> Array De 10.000
class BufferCompartido {

	int bufferEnteros[] = new int[100];
	int valorInicial = 0;
	boolean listoParaLeer;

	// Rellena el buffer
	public synchronized void escribirBuffer() {

		while (listoParaLeer) { // espera si lector aún no leyó
	        try {
	            wait();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
		
		// Relleno el array
		for (int i = 0; i < bufferEnteros.length; i++) {
			bufferEnteros[i] = valorInicial;
		}

		valorInicial++; // Aumento el valor (con el que se rellena)
		listoParaLeer = true;
		notify(); // Notifica cuando termine

	}

	// Leo el buffer completo, comprobando si algun valor no coincide
	public synchronized void leerBuffer() {

		while (!listoParaLeer) { // espera hasta que el escritor termine
			try {
				wait(); // Espera hasta que le llamen con notify
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Modificacion para que falle y pare
		if (valorInicial == 4) {
			bufferEnteros[2] = 8888;
		}

		System.out.println("Leyendo...");
		// Leo el array --> comprobando el valor
		for (int i = 0; i < bufferEnteros.length; i++) {

			if (bufferEnteros[i] != valorInicial - 1) {
				System.out.println("Valor requerido: " + (valorInicial-1) + " Valores obtenido: " + bufferEnteros[i]);

				System.out.println("Valor no valido, saliendo...");
				System.exit(0);
			}

			System.out.println("Valor requerido: " + (valorInicial-1) + " Valores obtenido: " + bufferEnteros[i]);
		}

		System.out.println("Todos los valores son validos");
		listoParaLeer = false;
		notify(); // Despierta al escritor

	}
}

// Escribe todos el buffer con el mismo valor
// incrementando en uno el valor en cada pasada
class Escritor implements Runnable {

	private BufferCompartido bufferCompartido;

	// Constructor del escritor
	public Escritor(BufferCompartido bufferCompartido) {
		this.bufferCompartido = bufferCompartido;
	}

	@Override
	public void run() {

		while (true) {
			bufferCompartido.escribirBuffer();
		}

	}

}

// Lee todo el buffer y dice si algun numero
// no coincide
class Lector implements Runnable {

	private BufferCompartido bufferCompartido;

	// Constructor
	public Lector(BufferCompartido bufferCompartido) {
		this.bufferCompartido = bufferCompartido;
	}

	@Override
	public void run() {

		while (true) {
			bufferCompartido.leerBuffer();
		}

	}

}