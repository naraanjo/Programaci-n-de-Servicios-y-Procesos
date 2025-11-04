package ejercicio7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Aleatorios {

	public static void main(String[] args) throws IOException {

		ArrayList<String> listaNumeros = new ArrayList<String>();

		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

			String linea = "";
			
			while((linea = br.readLine()) != null) {
				listaNumeros.add(linea);
			}
		}
		
		// Parseo ya que solo se pueden recibir string
		int n = Integer.parseInt(listaNumeros.get(0));
		int m = Integer.parseInt(listaNumeros.get(1));
	
		
		// Lista de 5 randoms
		ArrayList<Integer> listaRandom = new ArrayList<Integer>();
		
		for(int i = 0; i < 5; i++) {
			// Genero el random
			int aleator = (int) (Math.random() * (m - n +1)) + n;
			listaRandom.add(aleator); // Añado 5 random a la lista
		}
		
		for(Integer numero: listaRandom) {
			System.out.println("[HIJO] " + numero);
		}
		
	
	}

}
