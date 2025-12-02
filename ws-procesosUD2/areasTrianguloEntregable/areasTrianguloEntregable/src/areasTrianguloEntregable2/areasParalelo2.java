package areasTrianguloEntregable2;

import java.util.Random;

public class areasParalelo2 {
	public static void main(String[] args) throws InterruptedException {
		Random generador=new Random();
		final int numHilos=10000;
		int baseMaxima=3;
		int alturaMaxima=5;
		
		// Unico monitor-contador
		Contador contadorGlobal = new Contador();

		// Almaceno los hilos
		Thread[] hilos = new Thread[numHilos];
		
		for (int i=0; i<numHilos; i++){
			// Evito base 0
			int base=1+generador.nextInt(baseMaxima);
			int altura=1+generador.nextInt(alturaMaxima);

			// Creo el objeto
			areasTriangulo2 ca= new areasTriangulo2(base, altura, contadorGlobal);
			
			hilos[i]=new Thread(ca);
			hilos[i].start();
		}
		
		// Espero a que los hijos termine
		for (int i = 0; i < numHilos; i++) {
			// Evita que el main termine antes que los hilos
			hilos[i].join(); 
		}
		
		System.out.println("\n--- RESULTADO FINAL ---");
		System.out.println("Total de hilos lanzados: " + numHilos);
		System.out.println("Total de cálculos (Contador final): " + contadorGlobal.getValor());
	}
}