package repasoAvanzado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Lanzador {

	public void Lanzar() {

		ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
		ArrayList<String> listaInfo = new ArrayList<String>();

		// 30 numeros random del 1 AL 50
		for (int i = 0; i < 30; i++) {
			int random = (int) (Math.random() * (50 - 1 + 1) + 1);
			listaNumeros.add(random); // Añado los aleatorios
		}

		try {

			// PH1, sera llamado 30 veces. Ya que la lista tiene 30 numeros

			for (Integer numero : listaNumeros) {
				// Inicializo y creo el proceso1
				ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "repasoAvanzado.Multiplos3",
						String.valueOf(numero)); // Paso los numeros unoa uno
				pb1.redirectError(new File("src/repasoAvanzado/p1"));
				Process proceso1 = pb1.start();

				// Leo la salida de cada proceso
				// Guardo cada info en una lista
				try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
					String linea = "";
					while ((linea = br.readLine()) != null) {
						listaInfo.add(linea); // Añado cada salida de cada proceso -30-
					}
				}
			}

			// Paso todos los -si- y -no- para que haga recuento
			for (String info : listaInfo) {
				// Inicio y creo el PH2
				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "repasoAvanzado.ValidaMultiplos", info);
				Process proceso2 = pb2.start();
				pb2.redirectError(new File("src/repasoAvanzado/p2"));

				// Envio la info al PH-2, que contara cunatos -si- y -no- recibe
				try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {
					bw.write(info);
					bw.flush();
				}

				// Leo la salida de cada proceso
				try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
					String linea = "";
					while ((linea = br.readLine()) != null) {
						System.out.println(linea);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		;

	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}
}
