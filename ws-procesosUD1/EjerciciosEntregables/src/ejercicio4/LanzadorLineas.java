package ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

public class LanzadorLineas {

	public  void lanzarLineas(String rutaEntrada, String rutaSalida) throws IOException {

		// Compruebo que exista el archivo
		File archivoEntrada = new File(rutaEntrada);

		if (!archivoEntrada.exists()) {
			throw new IOException("Archivo de entrada inexistente ");

		}

	
		String clase = "ejercicio4.ContadorLineas";

		// Inicializo el proceso
		ProcessBuilder pb = null;
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", clase, rutaEntrada, rutaSalida);
			pb.redirectError(new File("erroresLineas.txt"));
			pb.redirectErrorStream(true);

		} catch (Exception e) {
			System.out.println("Error al inicializar el proceso" + e.getMessage());
		}

		// Creacion del objeto proceso
		try {
			Process proceso = pb.start();
			
			
			proceso.waitFor();

		} catch (Exception e) {
			System.err.println("Error al crear el objeto proceso");
		}
	}

	public static void main(String[] args) throws IOException {
		LanzadorLineas l = new LanzadorLineas();
		l.lanzarLineas("src/ejercicio4/entrada.txt", "src/ejercicio4/salida.txt");
	}
}
