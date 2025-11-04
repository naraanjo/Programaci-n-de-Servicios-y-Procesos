package ejercicio3;

import java.io.File;
import java.io.IOException;

public class LanzadorAnalizador {

	// Metodo para lanzar el proceso
	public void lanzarAnalizador(String rutaEntrada, String rutaSalida, String palabraBuscar) throws IOException {

		// Caso de que no exista el archivo de entrada
		File entrada = new File(rutaEntrada);
		if (!entrada.exists()) {
			throw new IOException("Archivo inexistente");
		}

		// Clase y ruta
		String clase = "ejercicio3.Analizador";
		File path = new File(".\\bin");

		// Inicializo el objeto - proceso
		ProcessBuilder pb = null;
		try {

			pb = new ProcessBuilder("java", "-cp", path.getAbsolutePath(), clase, rutaEntrada, rutaSalida,
					palabraBuscar);
			pb.redirectError(new File("src/ejercicio3/errores.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Creacion del objeto proceso
		try {

			Process proceso = pb.start();
			proceso.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		LanzadorAnalizador l = new LanzadorAnalizador();
		l.lanzarAnalizador("src/ejercicio3/entrada.txt", "src/ejercicio3/salida.txt", "leon");
	}

}
