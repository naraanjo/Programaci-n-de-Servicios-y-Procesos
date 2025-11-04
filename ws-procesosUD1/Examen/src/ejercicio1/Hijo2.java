package ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hijo2 {

	public static void main(String[] args) throws Exception {
		
		
		String cadenaRecibida = "";
		// Lectura de la informacion enviada desde PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			// Leo la cadena enviada desde
			cadenaRecibida = br.readLine();
			
		}
		
		// Esa cadena la divido en palabras
		// Funcionamiento del metodo; Devuelve un array de palabras
		String listaPalabras[] = cadenaRecibida.split("\\s+");
		
		int contadorPalabrasConN = 0;
		
		// Recorro el array de palabras
		for(String palabra: listaPalabras) {
			
			// Si la palabra contiene -n- o -N-, incremento uno
			if(palabra.contains("n") || palabra.contains("N")) {
				contadorPalabrasConN ++;
			}
		}
		
		
		// Envio la informacion al PP
		System.out.println("Se tienen " + contadorPalabrasConN + "  palabras que contienen 'n' o 'N'" );
		
		
		
	}
}
