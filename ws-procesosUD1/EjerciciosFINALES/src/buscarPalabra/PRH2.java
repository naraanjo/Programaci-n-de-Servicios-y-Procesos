package buscarPalabra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class PRH2 {

	public static void main(String[] args) throws Exception {
		
		
		File archivo = new File("resultadosBusqueda.txt");
		if(!archivo.exists()) archivo.createNewFile(); // Si no existe, se crea
		
		// Lecura de la info de PP
		// Escritura en el archivo de resultador
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(archivo, true))){
			
			
			// Leo la informacion y envio todas las lineas a un archivo de resultados
			String linea = "";
			while((linea = br.readLine()) != null) {
				// Escribo en el fichero la informacion
				bw1.write(linea);
				bw1.newLine();
				bw1.flush();
			}	
		}
		
		// Informacion que sacara el padre
		System.out.println("Resultados enviados con exito");
	}
}
