package Procesos1.Ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		//Archivo donde guardare la informacion del proceso gijo
		File listadoInfo = new File("info.txt");
		
		// Creamos processBuilder con comandos especificos
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
		
		// Redirigo la salida del proceso al archivo
		pb.redirectOutput(listadoInfo);
		
		//Arranco el proceso
		Process proceso = pb.start();
		
		//Para al proceso padre
		proceso.waitFor();
		
		// Lecuta del archivo generado
		try(BufferedReader br = new BufferedReader(new FileReader(listadoInfo))){
			
			String linea;
			
			while((linea = br.readLine()) != null) {
				
				System.out.println(linea);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
