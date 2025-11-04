package ejercicio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;


public class ej6 {

	// "cmd", "/c", "echo Hola Mundo"
	
	
	public void lanzarComandos() throws IOException {
		
		String comando = "cmd";  // el ejecutable
		String argumento1 = "/c"; // opción para ejecutar y salir
		String argumento2 = "echo Hola mundo";
		ProcessBuilder pb = null;
		try {
			
			 pb = new ProcessBuilder(comando, argumento1, argumento2); 
			pb.redirectError(new File("errores6.txt"));
		}catch(Exception e) {
			System.err.println("Error al iniciar el proceso");
		}
		
		
		int exitCode = 2;
		// Creo el objeto
		Process proceso = null;
		try {
			 proceso = pb.start();
		 exitCode = proceso.waitFor();
			
		}catch(Exception e ) {
			System.err.println("Error al crear el proceso");
		}
		
		// Leo el contenido generado
		try {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
				System.out.println("Resultado del hijo");
				String linea = "";
				while((linea = br.readLine()) != null ) {
					System.out.println(linea);
				}
			};
		}catch(Exception e ) {
			System.err.println("Error al obtener la salida del hijo");
			
			
		}
		
		System.out.println("Proceso hijo finalizado con codigo: " + exitCode );
	}

	public static void main(String[] args) throws IOException {
		ej6 l = new ej6();
		l.lanzarComandos();
	}
}
