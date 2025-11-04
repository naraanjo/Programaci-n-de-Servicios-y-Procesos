package Procesos1.Ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio3 {

    public static void main(String[] args) {
    	
    	
    	// Creo el proceso
    	try {
    		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir no_existe");
    		Process proceso = pb.start();
    		
    		//Captura stdout
    		BufferedReader brOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
    		
    		//Captura stderrr - flujo de errores del proceso hijo
    		BufferedReader brErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
    		
    		// Leo OUT
    		String linea;
    		while((linea = brOut.readLine()) != null){
    			System.out.println("STD > " + linea);
    		}
    		
    		// Leo Error
    		while((linea = brErr.readLine()) != null) {
    			
    			System.out.println("ERR > " + linea);
    		}
    		
    		// Paro el proceso
    		proceso.waitFor();
    		
    		System.out.println("Proceso terminado con codigo " + proceso.exitValue());
    	} catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    	
    }
}
