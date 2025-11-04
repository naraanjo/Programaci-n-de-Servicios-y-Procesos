package AlCuadrado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.File;

public class Lanzador {

	// Genera una lista de numero
	// Los pasa a PH-1, este hace el cuadrado de cada numero
	// Los pasa a PP
	// PP a PH-2, le suma 10 a cada numero, los saca por terminal

// ----- SI NO SALE NADA -----
	// Ver que he pasado string y NO NUMEROS
	// FLUSH - NEWLINE
	
	public void Lanzar() throws Exception {

		// Lista de numeros
		ArrayList<Integer> listaAleatorios = new ArrayList<Integer>();

		// Genero 5 numeros entre 2 - 20
		for (int i = 0; i < 5; i++) {
			int aleator = (int) (Math.random() * (20 - 2 + 1) + 2);
			listaAleatorios.add(aleator);
		}

		try {
			// ------- FASE-0 | CREACION DE PH1 y PH2 -----------
			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "AlCuadrado.PH1");
			Process proceso1 = pb1.start();
			pb1.redirectError(new File("uno.txt"));

			ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "AlCuadrado.PH2");
			Process proceso2 = pb2.start();
			pb2.redirectError(new File("dos.txt"));

			// --- FASE 1: Enviar números a PH1 ---
			try (BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))) {
			    for (Integer numero : listaAleatorios) {
			        bw1.write(numero.toString()); // ---IMPORTANTE--- PARSEAR STRING
			        bw1.newLine();
				    bw1.flush(); // Muy importante para que PH1 reciba los datos

			    }
			}

			// --- FASE 2: Leer salida de PH1 ---
			ArrayList<String> salidaPH1 = new ArrayList<>();
			try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
			    String linea;
			    while ((linea = br1.readLine()) != null) {
			        System.out.println("[PP recibe de PH1]: " + linea);
			        salidaPH1.add(linea); // Guardamos para enviar a PH2
			    }
			}

			// --- FASE 3: Enviar datos a PH2 ---
			try (BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {
			    for (String linea : salidaPH1) {
			        bw2.write(linea);
			        bw2.newLine();
				    bw2.flush();

			    }
			}

			// --- FASE 4: Leer salida de PH2 ---
			try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
			    String linea;
			    while ((linea = br2.readLine()) != null) {
			        System.out.println("[PP recibe de PH2]: " + linea);
			    }
			}

		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}
}
