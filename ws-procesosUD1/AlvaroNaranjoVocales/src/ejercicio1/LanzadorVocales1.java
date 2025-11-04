package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// JVM22
public class LanzadorVocales1 {
	Scanner teclado = new Scanner(System.in);

	public void LanzarContadorVocales(String rutaEntrada, String rutaSalida) throws IOException {

		// Control de que exista el archivo de entrada
		File entrada = new File(rutaEntrada);
		if (!entrada.exists()) {
			throw new IOException("Archivo no encontrado || No existe");
		}

		// Clase que cuenta las vocales
		String clase = "ejercicio1.ContadorVocales1";

		// Inicio el proceso
		ProcessBuilder pb = null;
		try {

			pb = new ProcessBuilder("java", "-cp", "bin", clase, rutaEntrada, rutaSalida);
			pb.redirectErrorStream(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Creacion del proceso
		try {
			Process proceso = pb.start();

			System.out.println("Introduce una vocal, se realizara un conteo de sus apariciones");
			String vocal = teclado.nextLine();

			// Envio la vocal al proceso hijo
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream(), "UTF-8"))) {

				bw.write(vocal);
				bw.newLine();
				bw.flush();
			}

			proceso.waitFor(); // El proceso padre espera al hijo

			// Leo la informacion que saca el hijo, como errores/excepciones
			try (BufferedReader brHijo = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
				String linea;
				while ((linea = brHijo.readLine()) != null) {
					System.out.println("Hijo: " + linea);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		LanzadorVocales1 l = new LanzadorVocales1();
		l.LanzarContadorVocales("src/ejercicio1/entrada.txt", "src/ejercicio1/salida.txt");
	}

}
