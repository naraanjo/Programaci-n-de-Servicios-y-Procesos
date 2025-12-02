package areasTrianguloEntregable;

import java.util.Random;

public class areasTriangulo implements Runnable {
	
	float base, altura, area;
	// Contador de referencia al monitor
	private Contador contadorMonitor; 
	
	public areasTriangulo(float b, float a, Contador monitor) {
		this.base = b;
		this.altura = a;
		this.contadorMonitor = monitor; // Guardamos la referencia al monitor.
	}
	
	
	@Override
	public void run() {
		Random generador = new Random();
		area = base * altura / 2;
		try {
			// Simulo calculo
			Thread.sleep(generador.nextInt(100));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		// Llamamos al método sincronizado del monitor
		this.contadorMonitor.incrementar(); 
		
		System.out.println("hilo: "+Thread.currentThread().getName()+" area resultado: "+area+" con contador: "+this.contadorMonitor.getValor());
	}
}