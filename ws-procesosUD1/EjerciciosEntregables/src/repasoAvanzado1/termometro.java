package repasoAvanzado1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class termometro {

	public static void main(String[] args) throws Exception {
		
		// Lista donde almaceno las temperaturas recibidas
		ArrayList<Integer> listaTemperaturas = new ArrayList<Integer>();
		
		// Leo los valores enviados por stdin del PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String linea = "";
			
			while((linea = br.readLine()) != null) {
				listaTemperaturas.add(Integer.parseInt(linea)); // Paso la temperatura | Por defecto es string
				
			}
		}
		
		
		// Saco la media
		double suma =0;
		for(Integer t: listaTemperaturas) suma+=t;
		double media = suma / listaTemperaturas.size();
		
		// Recorro la lista y envio al PP las que esten por encima de la media
		for(Integer t: listaTemperaturas) {
			if(t >= media) {
				System.out.println(t); // Envio la temperatura al padre
			}
		}
	}
}
