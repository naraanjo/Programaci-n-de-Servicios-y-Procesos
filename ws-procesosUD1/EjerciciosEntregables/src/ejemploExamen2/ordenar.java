package ejemploExamen2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ordenar {

	
	public static void main(String[] args) throws Exception {
		// Lector de la info del PP
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//Lista donde almaceno los numeros recibidos del PP
		ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
		
		// Leo del PP, los 40 numeros
		try {
			String linea = "";
			while((linea = br.readLine()) != null) {
				listaNumeros.add(Integer.parseInt(linea)); // Añado el numero a la lista
			}
			
			// Ordeno la lista
			Collections.sort(listaNumeros);
			
			// Envio al padre la info ordenada
			for(Integer numOrdenado: listaNumeros) {
				System.out.println(numOrdenado);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
