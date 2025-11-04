package ejercicio8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ej8 {

	public void Lanzador() throws IOException, InterruptedException {
		
		// Proceso1 -  pares del 1 al 100
		// Proceso2 - impares del 1 al 100
		ProcessBuilder pb1 = null;
		ProcessBuilder pb2 = null;
		// Inicializo procesos
		try {
		
			pb1 = new ProcessBuilder("java", "-cp", "bin", "ejercicio8.pares");
			pb2 = new ProcessBuilder("java", "-cp", "bin", "ejercicio8.impares");
		
			// Errores por consolo fusion stdout y stderror
			pb1.redirectErrorStream(true);
			pb2.redirectErrorStream(true);
			
		}catch(Exception e ) {
			System.err.println("Error al iniciar los procesos");
		}
		
		// Creo procesos
		Process proceso1 = null;
		Process proceso2 = null;
		try {
			proceso1 = pb1.start();
			proceso2 = pb2.start();
	
		}catch (Exception e) {
			System.err.println("Error al crear el proceso");
		}
		
		// Leo los dos procesos
		// Proceso 1
		try(BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))){
			String linea = "";
			
			while((linea = br1.readLine()) != null) {
				System.out.println("[HIJO-1] " + linea);
			}
		};
		
		// Proceso 2
		try(BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))){
			String linea;
			
			while((linea = br2.readLine()) != null) {
				System.out.println("[HIJO-2] " + linea);
			}
		}
		
		int exitCode1 = proceso1.waitFor();
		int exitCode2 = proceso2.waitFor();
		
		System.out.println("-------------------------------------------");
		System.out.println("Proceso-1 finalizado con codigo: " + exitCode1);
		System.out.println("Proceso-2 finalizado con codigo: " + exitCode2);
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
	
		ej8 lDoble = new ej8();
		lDoble.Lanzador();
	
	}
}
