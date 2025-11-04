package EjercicioContarVocales;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContadorVocales {
	public static void main(String[] args) {
		// Validar argumentos
		if (args.length < 3) {
			System.err.println("Uso: java ContadorVocales <archivoEntrada> <vocal> <archivoSalida>");
			System.exit(1);
		}

		
		String archivoEntrada = args[0];
		String vocal = args[1];
		int cont = 0;

		// Con FileReader se podría hacer pero no funciona con tildes, ñ.
		// try(BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
		// Con InputStreamReader y FileInputStream se puede especificar la codificación
		// (UTF-8) para que lea todos los caracteres.
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivoEntrada), "UTF-8"))) {
			String linea;
			// Leer línea a línea, pasar el contenido a un array de char y comparar la vocal con cada carácter del array
			// Si coinciden, incrementar el contador
			while ((linea = br.readLine()) != null) {
				for (char c : linea.toCharArray()) {
					if (c == vocal.charAt(0)) {
						cont++;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Mostrar resultado por consola
        System.out.println("La vocal '" + vocal + "' aparece " + cont + " veces.");
	}
}