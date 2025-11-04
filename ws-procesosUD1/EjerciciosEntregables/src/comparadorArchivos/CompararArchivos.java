package comparadorArchivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CompararArchivos {

	public static void main(String[] args) throws IOException {
		// Lista donde guardo la info
		ArrayList<String> listaInfo = new ArrayList<String>();

		// Leo la informacion recibida por el PP
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			String linea = "";
			while ((linea = br.readLine()) != null) {
				listaInfo.add(linea);
			}
		}
		// Almaceno las dos rutas
		String ruta1 = listaInfo.get(0);
		String ruta2 = listaInfo.get(1);

		// Leo ambos archivos a la vez para comparar si tienen el mismo contenido
		try (BufferedReader br = new BufferedReader(new FileReader(ruta1));
				BufferedReader br2 = new BufferedReader(new FileReader(ruta2))) {
			String linea1 = "";
			String linea2 = "";
			Boolean iguales = true;
			
			while((linea1 = br.readLine()) != null && (linea2 = br2.readLine()) != null) {
				
				if(!linea1.equals(linea2)) iguales = false;
		
				
			}
			
			// Pasamos el resultado al PP
			System.out.println("[PH-1] Archivos iguales: " + iguales);
			System.out.println(iguales); // Clave para que lo almacene el PP
 
		} catch (Exception e) {
			System.err.println("Error al leer los archivos: " + e.getMessage());
		}

	}

}
