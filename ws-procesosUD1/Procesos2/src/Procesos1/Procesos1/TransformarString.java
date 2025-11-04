package Procesos1.Procesos1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TransformarString {

	// Otro tipo de leer por teclado -  Usar scanner como siempre
	public static void main(String[] args) throws IOException {

		InputStreamReader ipsr = new InputStreamReader(System.in, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(ipsr);	
		
		//Scanner scanner = new Scanner(System.in);
		
		String line = "";
		//String linea;
		
		do {
			line = br.readLine();
			//linea = scanner.nextLine();
			//linea = scanner.nextInt();
			System.out.println(line.toUpperCase());					
		} while (!line.isBlank());
		//scanner.close();
	}

}
