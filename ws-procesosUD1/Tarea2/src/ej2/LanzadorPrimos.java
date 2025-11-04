package ej2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class LanzadorPrimos { // JVM22

	public void lanzarPrimos() {
		Scanner teclado = new Scanner(System.in);

		int numeroN;
		int numeroM;
		// Pido N y M por consola, seran parametros para el proceso
		do {
			System.out.println("Introduce dos numeros N y M: siendo N > = 0, M < 1000 y N < = M");
			System.out.println("Introduce numero N:");
			numeroN = teclado.nextInt();
			System.out.println("Introduce numero M:");
			numeroM = teclado.nextInt();
		} while (numeroN < 0 || numeroM >= 1000 || numeroN > numeroM);

		File path = new File(".\\bin");
		String clase = "ej2.ObtenerPrimos";

		try {
			// Inicio el proceso
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", path.getAbsolutePath(), clase);
			pb.redirectErrorStream(true); // Fusiono stdout sterror
			Process proceso = pb.start();

			try (// Envio N y M al proceso hijo
					BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(proceso.getOutputStream(), "UTF-8"))) {
				bw.write(String.valueOf(numeroN));
				bw.newLine();
				bw.write(String.valueOf(numeroM));
				bw.flush();
			}

			// Leo la salida del proceso ObtenerPrimos
			try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
				// Recorro la salida del proceso hijo
				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
				proceso.waitFor();

			}
		} catch (IOException e) {
			System.err.println("Error de E/S!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LanzadorPrimos l = new LanzadorPrimos();
		l.lanzarPrimos();
	}

}
