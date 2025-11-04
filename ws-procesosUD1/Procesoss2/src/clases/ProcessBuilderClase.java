package clases;

import java.io.IOException;

public class ProcessBuilderClase {

public static void main(String[] args) {
		
		// Compruebo que el proceso sea valido
		if(args.length <= 0) {
			System.out.println("No hay ningun programa seleccionado");
			System.exit(-1); 
		}
		
		// Creacion del proceso
		ProcessBuilder pb = new ProcessBuilder(args);
		
		try {
			// Creo mi proceso
			Process miProceso = pb.start();
			
			//Espera el padre hasta que termine el hijo - devuelve int - 0 valido
			int retorno = miProceso.waitFor();
			
			// Esto se imprime cuando finalice el proceso hijo, y continue el padre
			System.out.println("Proceso finalizado correctamente " + retorno);
		}catch(IOException ex) {
			System.out.println("Excepcion de E/S!!!");
			System.exit(-1); // Ya que no ha terminado correctamente
		}catch(InterruptedException ex) {
			System.out.println("Archivo cerrado incorrectamente");
			System.exit(-1); // Ya que no se ha cerrado correctamente
		};
	}

}
