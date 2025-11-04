package factorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Lanzador {

	/*
	 * PP tiene un array de numeros, va pasando uno a uno a PH1- este calcula el
	 * factorial, envia el numero y el resultado a PP - y este a PH2- que guarda la
	 * info en un fichero
	 *
	 */

	public void Lanzar() {

		try {

			int listaNumeros[] = { 2, 5, 8, 3 };

			// Recorro la lista de los numeros
			for (Integer numero : listaNumeros) {

				// Creacion de procesos por cada numero
				ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "factorial.P1");
				Process proceso1 = pb1.start();

				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "factorial.P2");
				Process proceso2 = pb2.start();

				// Envio el numero al PH-1
				try (BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))) {

					// Envio info a PH1
					bw1.write(numero.toString()); // IMPORTANTE PARSEAR
					bw1.newLine();
					bw1.flush();
				}

			
				 // Abrimos lectura de PH1 y escritura a PH2 simultáneamente
                try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
                     BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {

                    String linea;
                    while ((linea = br1.readLine()) != null) {
                        // Mostramos la salida en PP
                        System.out.println("[PH-1]: " + linea);

                        // Reenviamos inmediatamente a PH2
                        bw2.write(linea);
                        bw2.newLine();
                        bw2.flush();
                    }
                }

				// Lectura de la info de PH-2
				try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {

					String linea2 = "";
					while ((linea2 = br2.readLine()) != null) {
						System.out.println("[PH2]: " + linea2);
					}
				}
				
				proceso1.waitFor();
				proceso2.waitFor();
			}

		} catch (Exception e) {
			System.out.println("Eror - Lanzador");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}

}
