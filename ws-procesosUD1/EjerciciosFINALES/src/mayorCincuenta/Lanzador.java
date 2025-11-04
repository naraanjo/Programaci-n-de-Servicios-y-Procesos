package mayorCincuenta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import factorial.Lanzador;

public class Lanzador {

	public void Lanzar() {

		ArrayList<Integer> listaNumeros = new ArrayList<Integer>();

		// Genero 5 numeros aleatorioas (1-100)
		for (int i = 0; i < 4; i++) {
			int aleator = (int) (Math.random() * (100 - 1 + 1) + 1);
			listaNumeros.add(aleator);
		}

		try {

			for (Integer numero : listaNumeros) {

				// Creacion de los procesos
				ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "mayorCincuenta.Proceso1");
				Process proceso1 = pb1.start();

				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "mayorCincuenta.Proceso2");
				Process proceso2 = pb2.start();

				// Envio el numero al PH1
				try (BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))) {
					bw1.write(numero.toString());
					bw1.newLine();
					bw1.flush();
				}

				// Leo la salida de PH1
				// Envio la salida de PH1 a PH2
				try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
						BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {
					String linea = "";

					// Envio la info a PH2
					// A la vez que la leo en PH1
					while ((linea = br1.readLine()) != null) {
						System.out.println("[PH-1]: " + linea);
						bw2.write(linea);
						bw2.newLine();
						bw2.flush();
					}
				}

				// Leo la salida de PH2
				try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
					String linea = "";
					while ((linea = br2.readLine()) != null) {
						System.out.println("[PH-2]: " + linea);
					}
				}

				// Proceso padre espera
				proceso1.waitFor();
				proceso2.waitFor();
			}

		} catch (Exception e) {
			System.out.println("Error en el lanzador");
		}
	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}

}
