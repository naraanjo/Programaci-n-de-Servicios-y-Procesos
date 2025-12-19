package ficha2;

public class Check {

	public static void main(String[] args) throws InterruptedException {

		// Recurso compartido
		BufferCompartido buff = new BufferCompartido(10);

		Thread hiloEscritor = new Thread(new Hilos(true, buff));
		Thread hiloLector = new Thread(new Hilos(false, buff));

		// Arranco
		hiloLector.start();

		hiloEscritor.start();

	}
}

// Recurso compartido el buffer --> array
class BufferCompartido {

	// Buffer
	int miBuffer[];
	int contador = 0;
	Boolean isEscritor = true;

	// Constructor
	public BufferCompartido(int tamañoBuffer) {

		miBuffer = new int[tamañoBuffer];
	}

	// Primero debe de escribir --> rellenar buffer
	// Con uso de monitor
	public synchronized void rellenarEscribirMonitor(Boolean hiloEscritor) throws InterruptedException {

		while (contador < 10) {

			// Lector espera
			while (isEscritor && !hiloEscritor) {
				wait();
			}

			// Escritor
			if (isEscritor && hiloEscritor) {
				// Relleno
				for (int i = 0; i < miBuffer.length; i++) {
					miBuffer[i] = contador;
				}

				// Aumento el contador
				contador++;
				isEscritor = false; // Primero escribe luego lee
				notifyAll();

			} else {
				// Muestro el buffer
				for (int i = 0; i < miBuffer.length; i++) {
					System.out.print(miBuffer[i]);
				}
				System.out.println();
				isEscritor = true;
			}

		}

	}

	
	public void rellenarEscribirSinMonitor(boolean hiloEscritor) {

		while (contador < 10) {

			if (hiloEscritor) {

				// Escritor pisa cuando quiera
				for (int i = 0; i < miBuffer.length; i++) {
					miBuffer[i] = contador;
				}
				System.out.println("Escritor escribe " + contador);
				contador++;

			} else {

				// Lector lee incluso aunque escritor no haya acabado
				System.out.print("Lector lee: ");
				for (int i = 0; i < miBuffer.length; i++) {
					System.out.print(miBuffer[i]);
				}
				System.out.println();
			}
		}
	}

}

class Hilos implements Runnable {

	private Boolean hiloEscritor;
	private BufferCompartido bufferCompartido;

	// Constructor
	public Hilos(Boolean hiloEscritor, BufferCompartido bufferCompartido) {
		this.hiloEscritor = hiloEscritor;
		this.bufferCompartido = bufferCompartido;
	}

	@Override
	public void run() {

		bufferCompartido.rellenarEscribirSinMonitor(hiloEscritor);

	}
}