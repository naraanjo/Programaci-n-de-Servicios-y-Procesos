package ejercicio7;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ej7 {

	
	public void lanzador() throws IOException, InterruptedException {
		ProcessBuilder pb = null ;
		// Inicio al proceso hijo
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", "ejercicio7.Aleatorios");
			pb.redirectErrorStream(true); // Fusiona stdout y stderr

		}catch(Exception e ) {
			System.err.println("Error al inicializar al proceso hijo");
		}
		
		// Creo el proceso
		Process proceso = null;
		int exitCode = 2;
		try {
			proceso = pb.start();
			
			
		}catch(Exception e ) {
			System.err.println("Error al crear el proceso");
		}
		
		// Envio N y M al proceso hijo
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))){
			bw.write("6");
			bw.newLine();
			bw.write("100");
			
			bw.flush();
			
		};
		

		
		// Leo la informacion generada por el hijo
		try(BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))){
			String linea = "";
			
			while((linea = br.readLine()) != null ) {
				System.out.println("[HIJO] " + linea);
			}
		};
		
		exitCode = proceso.waitFor();

	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		ej7 l = new ej7();
		l.lanzador();
	}
}
