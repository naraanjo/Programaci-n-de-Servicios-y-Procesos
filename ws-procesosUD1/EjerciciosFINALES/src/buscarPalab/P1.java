package buscarPalab;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1 {

	public static void main(String[] args) throws Exception {
		
		String palabraBuscar = ""; // Lo primero que recibo
		int contador = 0;
		
		
		// Leo las lineas pasadas por PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			String linea = "";
			while((linea = br.readLine()) != null) {
				
				if(contador == 0) {
					palabraBuscar = linea;
					contador++;
				}else {
					contador++;
					
					// Divido la linea en palabras
					String lineaDividida [] = linea.split("\\s+");
					
					// Compruebo que la linea contenga o no esa palabra
					for (String palabra : lineaDividida) {
						
						if(palabra.equals(palabraBuscar)) {
							// Envio esa linea al PP
							System.out.println("Linea con coincidencia: " + linea);
						}
					}
				}
				
				
			
			}
		}
	}
}
