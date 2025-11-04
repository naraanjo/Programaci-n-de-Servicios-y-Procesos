package repasoAvanzado1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class resumen {

	public static void main(String[] args) throws Exception {
		
		
		
		// Leo la info del PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String linea = "";
			System.out.println("RESUMEN DE TEMEPRATURAS ELEVADAS");
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		}
				
	}
}
