package comparadorArchivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


/*
 *
 * // Ordena la lista en sitio usando un comparador
        numeros.sort((a, b) -> a - b); // Ascendente

        System.out.println(numeros); // [1, 2, 5, 9]*/
public class Lanzador {

	boolean resultado;
	String ruta1;
	String ruta2;
	Scanner teclado = new Scanner(System.in);

	// Lanza al PH1
	public void Lanzar() {

		System.out.println("Introduce la ruta del archivo número 1");
		ruta1 = teclado.nextLine();
		System.out.println("Inroduce la ruta del segundo archivo");
		ruta2 = teclado.nextLine();

		// Inicio el proceso
		try {

			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "comparadorArchivos.CompararArchivos");
			pb1.redirectErrorStream(true);
			Process proceso1 = pb1.start();

			// Envio la informacion al PH
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))) {
				bw.write(ruta1);
				bw.newLine();
				bw.write(ruta2);
				bw.flush();
			}

			// PP espera a PH
			int exitCode = proceso1.waitFor();

			// Array para guardar la info del PH-1 al PP
			ArrayList<String> listaInfo = new ArrayList<String>();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
				String linea = "";

				while ((linea = br.readLine()) != null) {
					System.out.println("[PH] " + linea); // Inofrmacion de PH que saca PP
					listaInfo.add(linea);
				}

			}
			System.out.println("Proceso hijo-1 termino con codigo: " + exitCode);

			resultado = Boolean.parseBoolean(listaInfo.get(1));
		} catch (Exception e) {
			System.err.println("Error al creaer el proceso");
		}
	}

	// Lanza al PH2
	public void Lanzar2() {

		if(resultado) {
			// Inicio el proceso
			try {
				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "comparadorArchivos.CopiaArchivo");
				pb2.redirectErrorStream(true);
				Process proceso = pb2.start();

				// PP escribe en PH2
				// Le envio ambas rutas
				try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {

					bw.write(ruta1);
					bw.newLine();
					bw.write(ruta2);
					bw.flush();
				}

				int exitCode2 = proceso.waitFor();
				
				//PP lee la informacion que saca PH-2
				try(BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))){
					String linea = "";
					
					while((linea = br.readLine()) != null) {
						System.out.println("[PH-2]: " + linea);
					}
				}
			} catch (Exception e) {
				System.err.println("Error al crear el PH-2: " + e.getMessage());
			}
		}else {
			System.out.println("PH-1 RESULTO FALSE");
		}
	}

	public static void main(String[] args) throws IOException {

		Lanzador l = new Lanzador();
		l.Lanzar(); // Lanza el PH-1
		l.Lanzar2();
		
	}
}
