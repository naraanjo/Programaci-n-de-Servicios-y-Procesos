package ejercicio1;

import java.io.File;
import java.io.IOException;

public class LanzadorVocales {

	public void lanzarCOntador(String archivoEntrada, String archivoSalida) {
		// Nombre de los archivos
		//"src/ejercicio1/entrada.txt";
		// "resultado.txt";
		

		// Control de que el archivo de entrada exista
		File entrada = new File(archivoEntrada);
		if (!entrada.exists()) {
			System.out.println("Archivo de entrada no encontrado || Inexistente");
			return; // Finalizo el programa
		}



		// Nombre de la clase que cuenta las vocales
		String clase = "ejercicio1.ContarVocales";
		// .class compilados
		File path = new File(".\\bin");

		// Inicio el proceso
		ProcessBuilder pb = null;
		try {
			pb = new ProcessBuilder("java", "-cp",path.getAbsolutePath(),clase, archivoEntrada, archivoSalida);
			pb.redirectError(new File("errorLanzador.txt"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Creo el proceso
		try {
			Process proceso1 = pb.start();
			proceso1.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		LanzadorVocales l = new LanzadorVocales();
		l.lanzarCOntador("src/ejercicio1/entrada.txt", "src/ejercicio1/salida.txt");

	}
}
