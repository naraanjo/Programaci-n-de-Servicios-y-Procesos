package ejercicio5;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lanzador {

	public void lanzar(String rutaEntrada, String rutaSalida) throws IOException {

		File archivoEntrada = new File(rutaEntrada);
		if (!archivoEntrada.exists())
			throw new IOException("ARCHIVO INEXISTENTE");

		;
		String clase = "ejercicio5.BuscarPalabraLarga";

		// Inicializo el proceso
		ProcessBuilder pb = null;

		try {
			pb = new ProcessBuilder("java", "-cp", "bin", clase, rutaEntrada, rutaSalida);
			pb.redirectError(new File("errores.txt"));
			pb.redirectErrorStream(true);

		} catch (Exception e) {
			System.err.println("Error al inicializar el proceso");
		}

		try {
			Process proceso = pb.start();
			// Leer la salida del hijo stdout
			try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {

				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println("Hijo dice --> " + linea);
				}
			}
			int exit = proceso.waitFor();
			System.out.println("Proceso hijo finalizo con codigo --> " + exit);
			
		} catch (Exception e) {
			System.err.println("Error al crear el objeto proceso");
		}
	}

	public static void main(String[] args) throws IOException {

		Lanzador l = new Lanzador();
		l.lanzar("src/ejercicio5/entrada.txt", "src/ejercicio5/salida.txt");
	}

}
