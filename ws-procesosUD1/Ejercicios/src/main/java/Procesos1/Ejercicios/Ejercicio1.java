package Procesos1.Ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {

	public static void main(String[] args) throws InterruptedException {
		
		
		try {
			ProcessBuilder pb = new ProcessBuilder("ping", "www.google.com"); 
			Process proceso = pb.start(); // Creacion del proceso
			
			//Capturamos la salida del proceso
			BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			
			// Leo
			String linea;
			
			while((linea = br.readLine()) != null) {
				
				System.out.println(linea);
			}
			
			
			int retorno = proceso.waitFor();
			
			System.out.println("El proceso termino con codigo " + retorno);
		}catch(IOException e) {
			e.printStackTrace();
		}
	} 
}
