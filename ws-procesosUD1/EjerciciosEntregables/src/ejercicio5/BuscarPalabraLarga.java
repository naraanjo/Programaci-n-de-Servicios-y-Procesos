package ejercicio5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BuscarPalabraLarga {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// Recibo los argumentos
		String rutaEntrada = args[0];
		String rutaSalida = args[1];
		String palabraLarga = "1";
		
		// Leer archivo
		try(BufferedReader br = new BufferedReader(new FileReader(rutaEntrada))){
			
			String linea;
			while((linea = br.readLine()) != null) {
				
				String[] palabras = linea.split("\\s+");
				
				for(String palabra: palabras) {
					
					if(palabra.length() > palabraLarga.length()) {
						palabraLarga = palabra;
					}
				}
			}
		}
		
		System.out.println("Palabras mas larga: " + palabraLarga);

	}

}
