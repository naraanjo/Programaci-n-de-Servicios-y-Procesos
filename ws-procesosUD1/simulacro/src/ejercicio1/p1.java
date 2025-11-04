package ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p1 {

	public static void main(String[] args) throws Exception {
		
		// Leo la info enviada por el padre
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			
			boolean fin = false;
			String temperaturaStr;
			while(!fin) {
				
				// Lecutra de numeros
				// Comprobandos linea vacia
				// Si linea contiene f o h
				do {
					// Validar linea vacia
					String linea = br.readLine();
					
					// Fin de la lectura
					if(linea == null) {
						fin = true;
						break;
					}
					
					temperaturaStr = linea.trim();

					
					
					// Recorro la linea caracter a caracter
					for(int i = 0; i<linea.length(); i++) {
						
						if(String.valueOf(linea.charAt(i)).equals("h")) {
							// Se  entiende que la temperatura es -99
							temperaturaStr = "-99";
						}else if(String.valueOf(linea.charAt(i)).equals("f")) {
							temperaturaStr = "99";
						}
					}
					
					
					// Envio al proceso PP
					if(Integer.parseInt(temperaturaStr) > 0) {
						System.out.println("Se esta bien " + temperaturaStr.toString());
					}else {
						System.out.println("Hace frio " + temperaturaStr.toString());

					}
					
				}while(temperaturaStr.isEmpty());
				
				
			}
			
			
		}
	}
}
