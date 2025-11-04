package ejercicio4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ContadorLineas {

	public static void main(String[] args) throws IOException {

		String rutaEntradas = args[0];
		String rutaSalida = args[1];
		int contadorLineas = 0;

		// Leo el archivo de entreada
		try (BufferedReader br = new BufferedReader(new FileReader(rutaEntradas))) {

			String linea;
			while ((linea = br.readLine()) != null) {
				++contadorLineas; // Sumo linea
			}
		} catch (Exception e) {
			System.err.println("Error al leer el contenido del archivo");
		}

		// Envio el resultado final al archivo
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {

			bw.write("Resultado --> " + contadorLineas + "lineas");

		} catch (Exception e) {
			System.err.println("Error al escribir el resultado en el archivo");
		}
	}
}
