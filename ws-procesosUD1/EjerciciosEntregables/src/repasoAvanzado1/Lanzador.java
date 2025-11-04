package repasoAvanzado1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.File;

// Recibe por argumentos ficheros
public class Lanzador {

	public void Lanzar(String[] args) {

		// Recorro los argumentos
		for (String rutaArchivo : args) {
			ArrayList<Integer> listaTemperatura = new ArrayList<Integer>();

			String rutaCompleta = "src/repasoAvanzado1/" + rutaArchivo;

			// Leo las temperaturas desde el archivo
			try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {

				// Leer temperatura valida
				String linea;
				boolean fin = false;
				
				// Leo el archivo
				do {
					linea = br.readLine();
					if (linea == null) {
						fin = true; // Fin del archivo
					} else {
						try {
							int temperatura = Integer.parseInt(linea.trim());
							listaTemperatura.add(temperatura);
						} catch (NumberFormatException e) {
						}
					}
				} while (!fin);

				// Inicio y creo PH1
				// Inicio y creo PH1
				ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "repasoAvanzado1.termometro");
				pb1.redirectError(new File("src/repasoAvanzado1/p1.txt"));
				Process proceso1 = pb1.start();

				// PP pasa las temperaturas a PH1
				// Escribiendo por stdin
				try (BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))) {
					// Recorro todas las temperaturas obtenidas
					for (Integer tempe : listaTemperatura) {
						bw1.write(String.valueOf(tempe));
						bw1.newLine();
						bw1.flush();
					}
				}

				ArrayList<String> listaInfo1 = new ArrayList<String>(); // Almaceno toda la salida de PH1
				// PP lee la info de PH1
				try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
					String linea1 = "";

					while ((linea1 = br1.readLine()) != null) {
						listaInfo1.add(linea1); // Añado la salida de PH1 a la lista - se pasara a PH2
					}
				}

				// Inicio y creo PH2
				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "repasoAvanzado1.resumen");
				pb2.redirectError(new File("src/repasoAvanzado1/p2.txt"));
				Process proceso2 = pb2.start();

				// Mando la info a PH2
				try (BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {

					// Recorro toda la info a escribir
					for (String info : listaInfo1) {
						bw2.write(info);
						bw2.newLine();
						bw2.flush();
					}
				}

				// LEO PH2
				try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
					String linea2 = "";

					while ((linea2 = br2.readLine()) != null) {
						System.out.println(linea2);
					}
				}

			} catch (Exception e) {
				System.out.println("Error al leer el archvio PP");
			}
		}

	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar(args);
	}
}
