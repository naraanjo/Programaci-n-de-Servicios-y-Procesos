package leerArchivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lanzador {
	/*Leer de un fichero, datos de un producto
	 * -Nomrbe-Precio-, en orden
	 * Acaba al encontrar una X
	 * 
	 * 
	 * */

	public void Lanzar() {

		File archivo = new File("productos.txt");
		
		try {
			
			// Creacion de los procesos
			ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "leerArchivo.Pr1");
			Process proceso1 = pb1.start();
			

			
			// Lectura del archivo | Envio datos a PH1
			// Envio cada linea que leo | linea a linea
			try(BufferedReader brArchivo = new BufferedReader(new FileReader(archivo));
				BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(proceso1.getOutputStream()))){
				
				String linea = "";
				while((linea = brArchivo.readLine()) != null){
					
					// Envio la info que leo el archivo a PH1
					bw1.write(linea);
					bw1.newLine();
					bw1.flush();
				}
			}
			
			// Leo la info sacada por PH1
			try(BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))){
				
				String linea = "";
				while((linea = br1.readLine()) != null) {
				
					// Caso de que se pare por -X- | Hijo envia FIN
					if(linea.equals("FIN")) {
						System.out.println("Lectura finalizada: -X-");
					}else {
						System.out.println("[PH-1]: " + linea); // Info de PH1
					}
				}
				
				
			}
			
			
		}catch(Exception e ) {
			System.out.println("Error");
		}
	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}

}
