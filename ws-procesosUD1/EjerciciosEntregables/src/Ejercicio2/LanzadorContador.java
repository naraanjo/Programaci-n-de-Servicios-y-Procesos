package Ejercicio2;

import java.io.File;

public class LanzadorContador {

	// Funcion para lanzarlo
	public void lanzarContador(String rutaEntrada, String rutaSalida, char vocal) {

		File entrada = new File(rutaEntrada);
		if (!entrada.exists()) {
			System.out.println("Archivo de entrada no encontrado || Inexistente");
			return; // Finalizo el programa
		}

		// Nombre de la clase que tiene la funcionalidad
		String clase = "Ejercicio2.Contador";
		File path = new File(".\\bin");

		// Inicio el proceso
		ProcessBuilder pb = null;
		try {

			pb = new ProcessBuilder("java",  "-cp", path.getAbsolutePath(), clase,rutaEntrada, rutaSalida, String.valueOf(vocal));
			pb.redirectError(new File("Errores ejercicio2.txt"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Creo el proceso
		try {
			Process proceso = pb.start();
			proceso.waitFor();
			
		}catch(Exception e ) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		LanzadorContador l = new LanzadorContador();
		
		l.lanzarContador("src/Ejercicio2/entrada.txt", "src/Ejercicio2/salida.txt", 'a');
		
	}
}
