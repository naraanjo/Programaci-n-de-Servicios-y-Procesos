package ejemploExamen6;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


// PP recibe nombres por argumentos
// Cuenta lineas de cada fichero uno a uno - PH1
// PH-1 envia las linea al PP / PP las envia a PH-2
// PH-2 crea nombrefichero_E si tiene mas de 10 lineas
// PH-2 crea nombreFichero_S si tiene menos de 10 lineas
// Crear erroresPH1 y erroresPH2

public class Lanzador {

	public void Lanzar(String args[]) {

		try {

			// Recorro los argumentos - rutas de ficheros
			for (String rutaArchivo : args) {

				// Llamo a PH-1
				// Inicializo y creo el PH-1
				ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen6.PH1", rutaArchivo);
				pb1.redirectError(new File("src/ejemploExamen6/Errores_PH1.txt"));
				Process proceso1 = pb1.start();

				// Leo la salida de PH-1
				try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {
					String linea1 = "";

					while ((linea1 = br1.readLine()) != null) {

						// Envio la salida a PH-2
						ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen6.PH2", linea1,
								rutaArchivo); // Linea1 es la cantidad de lineas
						pb2.redirectError(new File("src/ejemploExamen6/Errores_PH2.txt"));
						Process proceso2 = pb2.start();

						// Leo la salida de PH-2
						try (BufferedReader br2 = new BufferedReader(
								new InputStreamReader(proceso2.getInputStream()))) {
							String linea2 = "";

							while ((linea2 = br2.readLine()) != null) {
								System.out.println(linea2); // PP imprime la salida de PH-2
							}
						}
						
						proceso2.waitFor();
					}
				}
				proceso1.waitFor();
			}
		} catch (Exception e) {

			System.out.println("Error en el PP");
		}

	}

	
	public static void main(String[] args) {
		Lanzador l = new Lanzador();
		l.Lanzar(args);
	}
}
