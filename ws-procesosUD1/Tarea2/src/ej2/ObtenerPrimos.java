package ej2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ObtenerPrimos {

	public static void main(String[] args) {

		try {
			ArrayList<String> numeros = new ArrayList<String>();

			// Leo N y M, enviados por el proceso padre
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String linea;
			while ((linea = br.readLine()) != null) {

				numeros.add(linea);
				System.out.println(linea);
			}
			// Parseo los numeros que paso el padre como String
			int numeroN = Integer.parseInt(numeros.get(0));
			int numeroM = Integer.parseInt(numeros.get(1));

			System.out.println("VALOR DE (N) = " + numeroN + "\nVALOR DE (M) = " + numeroM);

			boolean noHayPrimos = true;
			// Calculo los numeros primos entre N y M
			int contador = 0;
			for (int i = numeroN; i <= numeroM; i++) {
				for (int j = 1; j <= i; j++) {
					if (i % j == 0)
						contador++; // Contador de divisores

				}

				if (contador == 2) {
					System.out.println("--> " + i); // Imprimo el primo
					noHayPrimos = false; // Controlo si hay algun primo
				}

				contador = 0; // Reseteo divisores
			}
			if (noHayPrimos)
				System.out.println("Ten primos pa' esto");

		} catch (NumberFormatException e) {
			System.err.println("Error al introducir N y M: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
