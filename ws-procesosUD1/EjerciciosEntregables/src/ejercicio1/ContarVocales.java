package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ContarVocales {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Número de argumentos inválido. Se esperan 2 argumentos:");
			System.exit(1); // Termina el programa con código de error
		}
		
		//PH espera exactamente 2 argumentos 
		String archivoEntrada = args[0];
		String archivoSalida = args[1];

		int contador = 0;
		// Leo el archivo
		try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
			int c;

			while ((c = br.read()) != -1) {

				char ch = Character.toLowerCase((char) c);
				if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
					contador++; // si es vocal, sumamos 1
				}

			}

		} catch (IOException e) {
			System.out.println("Error de E/S!!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Escribo en el archivo de salida
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

			bw.write("Numero de vocales --> " + contador);
			System.out.println("Escribiendo el numero de vocales");
		} catch (IOException e) {
			System.out.println("Error de E/S!!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
