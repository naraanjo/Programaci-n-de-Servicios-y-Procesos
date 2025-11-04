package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ejericicio1.funciones.Funciones;

public class ContadorVocales1 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String vocalBuscar;
		// Proceso hijo le la respuesta del padre
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			vocalBuscar = br.readLine(); // Recibe la vocal
		}
		// Recibo argumentos
		String rutaEntrada = args[0];
		String rutaSalida = args[1];

		// Controlo que por parametros se le pase una vocal
		try {
			Funciones.validarVocal(vocalBuscar);
		} catch (NullPointerException e) {
			System.err.println("No se ha introducido una vocal || null");
			return;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			return;
		}

		int contadorVocales = 0; // Vocales totales
		int contadorVocalBuscada = 0; // Vocal pasada por parametro
		ArrayList<Character> listaVocales = new ArrayList<Character>(List.of('a', 'e', 'i', 'o', 'u'));

		// Lectura del archivo de entrada
		try (BufferedReader br = new BufferedReader(new FileReader(rutaEntrada))) {

			int caracter;
			// Lectura del archivo caracter a caracter
			while ((caracter = br.read()) != -1) {

				// Coincidencia con la vocal buscada
				if (Character.toLowerCase((char) caracter) == vocalBuscar.toLowerCase().charAt(0)) {
					contadorVocalBuscada++;
				}

				// Coincidencia con cualquier vocal
				if (listaVocales.contains(Character.toLowerCase((char) caracter))) {
					contadorVocales++;
				}
			}

		} catch (IOException e) {
			System.err.println("Error al leer el archivo de entrada");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Envio los resultados al archivo de salida
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
			bw.write("Vocales totales: " + contadorVocales);
			bw.write("\nApariciones de la letra |" + vocalBuscar + "| " + "-> " + contadorVocalBuscada);

		} catch (IOException e) {
			System.err.println("Error al escirbir en el archivo de salida");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Informacion en salida.txt - refresh");
	}

}
