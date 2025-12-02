package areasTrianguloEntregable;

public class areasParalelo {
	public static void main(String[] args) throws InterruptedException {

		// Unico monitor
		Contador contadorGlobal = new Contador();
		
		final int MAX_HILOS = 10000;
		Thread[] hilos = new Thread[MAX_HILOS];
		
		// Runnable diferente para cada uno por su base y altura
		for (int i = 0; i < MAX_HILOS; i++) {
			
            areasTriangulo ca = new areasTriangulo(1, 2, contadorGlobal);
			hilos[i] = new Thread(ca, "Hilo:" + i); 
			hilos[i].start();
		}
		
		// Esperar a que todos los hilos terminen
		for (int i = 0; i < MAX_HILOS; i++) {
			hilos[i].join();
		}
		
		// Resultado final
		System.out.println("Total de calculos (Valor esperado: " + MAX_HILOS + "): " + contadorGlobal.getValor());
	}
}