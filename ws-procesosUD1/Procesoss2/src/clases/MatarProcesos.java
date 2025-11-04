package clases;

import java.io.IOException;

public class MatarProcesos {

	// MATAR PROCESOS / 0- ES CORRECTO / OTRO NUMERO NO (PROBLMEAS)
		public static void main(String[] args) {
			
			if(args.length <= 0) {
				System.out.println("Se necesita un programa a ejecutar");
				System.exit(-1);
			}
			
			// Creacion de un proceso
			ProcessBuilder process = new ProcessBuilder(args);
			
			try {
				// Creo mi proceso
				Process miProceso = process.start();
				
				Thread.sleep(3000);
				miProceso.destroy(); // Mato al proceso
			}catch(IOException ex) {
				System.out.println("Error de E/S !!");
				System.exit(-1);
			}catch(InterruptedException ex) {
				System.out.println("Error al finalizar el proceso");
				System.exit(-1);
			};

		}
}
