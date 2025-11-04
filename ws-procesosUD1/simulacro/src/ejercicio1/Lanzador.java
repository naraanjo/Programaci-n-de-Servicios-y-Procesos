package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lanzador {

	public void Lanzar() {

		// Creacion de la lista con 3 ficheros
		String listaFicheros[] = { "f1.txt", "f2.txt", "f3.txt" };

		try {

			// Recorro la lista de los ficheros
			for (int i = 0; i < listaFicheros.length; i++) {

				// Creacion de los procesos a ejecutar
				ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "ejercicio1.p1");
				pb1.redirectError(new File("erroresPH1.txt"));
				Process proceso1 = pb1.start();

				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "ejercicio1.p2");
				pb2.redirectError(new File("erroresPH2.txt"));
				Process proceso2 = pb2.start();

				// Lectura del archivo para enviar cada linea
				try (BufferedReader brArchivo = new BufferedReader(new FileReader(listaFicheros[i]));
						BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))) {

					// Leo el archivo linea a linea
					String linea = "";
					while ((linea = brArchivo.readLine()) != null) {

						// Envio cada linea leida al PH1
						bw1.write(linea.toString());
						bw1.newLine();
						bw1.flush();

					}
				}

				// Lectura de la informacion de PH1
				try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
						BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {

					String linea = "";
					while ((linea = br1.readLine()) != null) {
						// Saco por consola la info
						System.out.println("[PH1]: " + linea);

						// Envio a PH2
						bw2.write(linea.toString());
						bw2.newLine();
						bw2.flush();

					}

				}

				// Lectura de la informacion de PH2
				try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
					String linea = "";

					// Salida por consola de la info de pH2
					while ((linea = br2.readLine()) != null) {
						System.out.println("[PH-2]: " + linea);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Errror");
		}
	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}

}
