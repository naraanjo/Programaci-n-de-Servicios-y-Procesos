package ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Analizador {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String archivoEntrada = args[0];
		String archivoSalida = args[1];
		String palabraBuscar = args[2];
		int contador = 0;
		// Creo un lector para el archivo de entrada
		try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
				BufferedWriter bw = new BufferedWriter(new FileWriter("src/ejercicio3/salida2.txt"))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				// Separa cada linea por palabras
				String[] listaPalabras = linea.split("\\s+");

				for (String palabra : listaPalabras) {
					if (palabra.toLowerCase().equals("leon")) {
						contador++;

					}
					bw.write(palabra);
					bw.write("\n");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Escribo en el archivo de salida los resultados
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
			bw.write("Resultados \n");
			bw.write("Apariciones de la palabra leon: " + contador);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
