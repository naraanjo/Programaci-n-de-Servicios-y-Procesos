package Temperatura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Resultado {

	public static void main(String[] args) throws IOException {
		Boolean linea = false;
		
		// Lee la info del PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			 linea = Boolean.parseBoolean(br.readLine());
		}catch(Exception e ) {
			System.err.println(e.getMessage());
		}
		
		// Creo un archivo con el resultado
		//src/Temperatura
		
		File resultado = new File("src/Temperatura/resultadoFinal.txt");
		
		if(!resultado.exists()){
			resultado.createNewFile();
		}
		
		// Escribo en el archivo del resultado
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(resultado))){
			bw.write("Resultado de la temperatura: " + linea );
			bw.newLine();
			if(linea) bw.write("Temperatura elevada"); else bw.write("Temperatura baja");
			bw.flush();
		}
		
	
	}
}
