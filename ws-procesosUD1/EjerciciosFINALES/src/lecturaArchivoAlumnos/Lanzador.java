package lecturaArchivoAlumnos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lanzador {

	public void Lanzar() throws Exception {

		// Array con rutas de ficheros
		String[] listaFicheros = { "FicheroA.txt", "FicheroB.txt", "FicheroC.txt" };

		try {

			// Recorro los ficheros - LEO CADA UNO
			for (String rutaFichero : listaFicheros) {

				// Creo los archivos de salida de cada fichero
				String rutaSalida = rutaFichero + "_E";
				File archivoSalida = new File(rutaSalida);
				archivoSalida.createNewFile();

				// Creo e inicializo el proceso | Para cada fichero
				ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "lecturaArchivoAlumnos.Hijo");
				pb.redirectError(new File("src/lecturaArchivoAlumnos/Errores"));
				Process proceso = pb.start();

				File archivo = new File(rutaFichero);

				// Si no existe lo notifico
				if (!archivo.exists()) {
					System.out.println("El archivo introducido no existe");
				} else {

					// ------CREAR FUERA DE BUCLES---- Para que no se abra | cierre | abra ...
					try (BufferedReader brF = new BufferedReader(new FileReader(archivo));
							BufferedWriter bwPH = new BufferedWriter(
									new OutputStreamWriter(proceso.getOutputStream()))) {
						// --- FASE 1: enviar datos al hijo ---
						String linea = "";
						// A la vez que leo el archivo se envian datos al hijo
						// Si recibe * = fin
						while ((linea = brF.readLine()) != null && !linea.equals("fin")) { 
							bwPH.write(linea);
							bwPH.newLine();
							bwPH.flush();
						}
					}

					// Creo un fichero de salida con los resultados
					// Para cada fichero
					// Lectura de la salida del PH
					// --- FASE 2: recibir salida del hijo ---
					try (BufferedReader brH = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
							BufferedWriter bwSalida = new BufferedWriter(new FileWriter(archivoSalida))) {
						String lineaH = "";

						while ((lineaH = brH.readLine()) != null) {
							// Escribo en el fichero de salida de cada fichero
							System.out.println(lineaH);
							bwSalida.write(lineaH);
							bwSalida.newLine();
							bwSalida.flush();

						}
					}

					int codigo = proceso.waitFor(); // Esperar al proceso a que termine
					System.out.println("Proceso finalizado para fichero: " + rutaFichero + " | Codigo: " + codigo);
				}
			}

		} catch (Exception e) {
			System.out.println("Error no reconocido");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}
}
