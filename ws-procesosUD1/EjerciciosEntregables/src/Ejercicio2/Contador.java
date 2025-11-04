package Ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Contador {
	public static void main(String[] args) {

		String archivoEntrada = args[0];
		String archivoSalida = args[1];
		char consonanteBuscar = args[2].charAt(0);

		int contadorConsonante = 0;
		int contadorVocal = 0;
		int contadorCarcateresCincoMas = 0;
		int contadorBuscada = 0;

		try (BufferedReader br1 = new BufferedReader(new FileReader(archivoEntrada))) {

			// Leo letra a letra
			int c;
			ArrayList<Character> vocales = new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u'));

			// Recorro caracteer a caracter
			while ((c = br1.read()) != -1) {

				char ch = Character.toLowerCase((char) c);

				if (vocales.contains(ch)) {
					contadorVocal++;
				} else {
					contadorConsonante++;
				}

				if (ch == consonanteBuscar)
					contadorBuscada++;
			}

		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(BufferedReader br2 = new BufferedReader(new FileReader(archivoEntrada))){
			// Recorro linea a linea
			String linea;
			while ((linea = br2.readLine()) != null) {

				String[] palabras = linea.split("\\s+"); // Separa por palabras
				for (String palabra : palabras) {
					if (palabra.length() > 5) {

						contadorCarcateresCincoMas++;
					}
				}

			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		try (BufferedWriter br = new BufferedWriter(new FileWriter(archivoSalida))) {

			br.write("Numero de vocales: " + contadorVocal);
			br.write("\n");
			br.write("Numero de consonantes: " + contadorConsonante);
			br.write("\n");
			br.write("Numero de veces de la letra buscada: -A- " + contadorBuscada);
			br.write("\n");
			br.write("Palabras con mas de 5 caracteres: " + contadorCarcateresCincoMas);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
