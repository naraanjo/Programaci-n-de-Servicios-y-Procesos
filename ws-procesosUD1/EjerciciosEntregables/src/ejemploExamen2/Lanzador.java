package ejemploExamen2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.File;

/*PP llama a generarNumeros, los numerosGenerados vuelven al padre
 * el padre llama a ordenar , los numeros ordenados vuelven al padre,
 * el padre imprime*/
public class Lanzador {

	public void Lanzar() {
		// Lista donde almacenare los numeros
		ArrayList<Integer> listaNumeros = new ArrayList<Integer>();

		try {
			// PROCESO -1
			// Inicio proceso-1
			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen2.aleatorios");
			pb1.redirectError(new File("proceso1"));
			// Creo proceso-1
			Process proceso1 = pb1.start();

			// Lector del PH-proceso1

			try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
				// Leo los 40 numeros del PH-1
				String linea = "";
				while ((linea = br1.readLine()) != null) {
					listaNumeros.add(Integer.parseInt(linea)); // Añado el numero que lee el PP al PH-1
				}
			}
			;

			// PROCESO - 2
			// Creo el proceso-2
			ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen2.ordenar");
			pb2.redirectError(new File("proceso2"));

			Process proceso2 = pb2.start();
			try (BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {
				// Escritor del PROCESO-2
				// Mando los numeros al PH-2
				for (Integer numero : listaNumeros) {
					bw2.write(numero.toString()); // Envio numero a numero al PH-2
					bw2.newLine();
					bw2.flush();
				}
			}
			;

			listaNumeros.clear(); // Vacio la lista

			// Leo la info del PH-2
			// Lector de PH-2
			try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
				String linea2 = "";
				while ((linea2 = br2.readLine()) != null) {
					System.out.println(linea2); // Muestro uno a uno los numeros ordenados
				}
			};

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}
}
