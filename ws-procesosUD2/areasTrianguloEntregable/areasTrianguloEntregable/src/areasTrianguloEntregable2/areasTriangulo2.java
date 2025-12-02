package areasTrianguloEntregable2;

import java.util.Random;

public class areasTriangulo2 implements Runnable {
	int base, altura;
	// Referencia al monitor Contador
	private Contador contadorMonitor;
	
	public areasTriangulo2(int base, int altura, Contador monitor){
		this.base=base;
		this.altura=altura;
		this.contadorMonitor = monitor; 
	}
	
	@Override
	public void run() {
		float area=this.base*this.altura/2.0f; 
		
		// Llamamos al método sincronizado del monitor
		this.contadorMonitor.incrementar();
		
		// Mostramos el valor actual del contador del monitor
		System.out.print("Base:"+this.base);
		System.out.print(" Altura:"+this.altura);
		System.out.print(" Area:"+area);
		System.out.println(" Contador:"+this.contadorMonitor.getValor());
	}
}