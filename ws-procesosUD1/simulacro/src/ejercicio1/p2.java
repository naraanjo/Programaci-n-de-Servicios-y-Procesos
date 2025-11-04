package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class p2 {
	
	// Lectura de la info de pp
	public static void main(String[] args) throws Exception {
		
		// Creacion de los procesos si no lo estan
		File archivoCaliente = new File("caliente.txt");
		File archivoFrio = new File("frio.txt");
		
		if(!archivoCaliente.exists()) {
			archivoCaliente.createNewFile();
			System.out.println("Creando el archivo caliente...");
		}else {
			System.out.println("El archivo caliente ya estaba creado");
		}
		
		if(!archivoFrio.exists()) {
			archivoFrio.createNewFile();
			System.out.println("Creando el archivo frio...");
		}else {
			System.out.println("El archivo frio ya estaba creado");
		}
		
		

		// Debe leer el resultado y la temperatura
		// Lo escribe en los ficheros
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw1 = new BufferedWriter(new FileWriter(archivoFrio, true));
				BufferedWriter bw2 = new BufferedWriter(new FileWriter(archivoCaliente, true))) {
			
		
			String linea = "";
			while((linea = br.readLine()) != null) {
				
				if(linea.contains("frio")) {
					bw1.write(linea);
					bw1.newLine();
					bw1.flush();
				}
				
				if(linea.contains("bien")) {
					bw2.write(linea);
					bw2.newLine();
					bw2.flush();
				}
			}
			
		
		}
	}
}
