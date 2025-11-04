package ejercicio1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Comunicador {

	public void Lanzar() {

		Scanner teclado = new Scanner(System.in);

		try {

			// Solicito la cadena al usuario
			System.out.println("Introduce una cadena");
			String cadenaIntroducida = teclado.nextLine();

			// Creacion del Proceso Hijo 1
			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "ejercicio1.Hijo1");
			// Archivo para posibles errores en este PH1
			pb1.redirectError(new File("ErroresPH1.txt"));
			Process proceso1 = pb1.start();

			// Creacion del Proceso Hijo 2
			ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "ejercicio1.Hijo2");
			// Archivo para posibles errores en este PH2
			pb2.redirectError(new File("ErroresPH2.txt"));
			Process proceso2 = pb2.start();
			
			
			// Comunicacion con el PH1 - ESCRIBIR
			// Envio la informacion del PP A PH1, por la tuberia
			try(BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))){
				
				// Envio de la palabra a PH1
				bw1.write(cadenaIntroducida);
				bw1.newLine();
				bw1.flush();
				
			}
			
			// Leo la informacion recibida de PH1
			try(BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
				BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))){
				
				String linea = "";
				while((linea = br1.readLine()) != null) {
	
					// Envio de informacion de PH1 A PH2, pasando por PP
					bw2.write(linea); // Informacion enviada a PH2
					bw2.newLine();
					bw2.flush(); // Confirmo cambios
				}
			}
			
			// Leo la informacion del PH2
			try(BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))){
				
				String linea = "";
				while((linea = br2.readLine()) != null) {
					
					// Saco la info del PH2
					System.out.println(linea);
				}
			}

		} catch (Exception e) {
			System.out.println("Error en el comunicador de procesos");
		}
	}
	public static void main(String[] args) throws Exception {
		Comunicador l = new Comunicador();
		l.Lanzar();
	}
}
