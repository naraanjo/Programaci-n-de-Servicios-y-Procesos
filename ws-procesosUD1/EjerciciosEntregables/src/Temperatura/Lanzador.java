package Temperatura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lanzador {

	boolean resultadoPH1;

	public void Lanzar1() {

		// Inicio el PH-1
		try {
			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "Temperatura.Sensor");
			pb1.redirectError(new File("ErroresTemperatura.txt")); // Importar file

			Process proceso1 = pb1.start();
			int exitCode = proceso1.waitFor();

			// PP lee la informacio el PH-1
			try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
				String linea = "";

				while ((linea = br.readLine()) != null) {
					System.out.println("Resultado [PH-1]: " + linea);
					resultadoPH1 = Boolean.parseBoolean(linea); // Guardo el boolean para el PH-2
				}
			}

			System.out.println("[PH-1] termino con codigo: " + exitCode);

		} catch (Exception e) {
			System.err.println("Error al crear el proceso: " + e.getLocalizedMessage());
		}
	}

	public void Lanzar2() {

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "Temperatura.Resultado");
			pb.redirectError(new File("ErrorResultado"));
			Process proceso2 = pb.start();

			// Envio info de PP-PH2
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {
				bw.write(String.valueOf(resultadoPH1));
				bw.flush();
			}

			int exitCode = proceso2.waitFor();
			System.out.println("[PH2]- Termino con codigo: " + exitCode);

		} catch (Exception e) {
			System.out.println("Error al crear el proceso 2: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {

		Lanzador l = new Lanzador();
		l.Lanzar1();
		l.Lanzar2();
	}
}