package ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hijo1 {

	public static void main(String[] args) throws Exception {
	
		String cadena ;
		// Recibo la informacion enviadad desded PP || Mediante System.in
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			// Recibo la palabra enviada desde PP
			cadena = br.readLine();
		}
		
		// Hago la logica reverse
		StringBuilder palabraReverese = new StringBuilder();
		palabraReverese.append(cadena);
		palabraReverese.reverse(); // Hago el reverse de la palabra
		
		// Envio al proceso padre la informacion
		// Cadena reverse
		System.out.println(palabraReverese.toString()); 

	}

}
