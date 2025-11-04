package comparadorArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class CopiaArchivo {

	public static void main(String[] args) throws IOException {
		ArrayList<String> listaRutas = new ArrayList<String>();
		
		// Leo las rutas que pasa el PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String linea = "";
			while((linea = br.readLine()) != null) {
				listaRutas.add(linea);
			}
		}
		
		String ruta1 = listaRutas.get(0);
		String ruta2 = listaRutas.get(1);
	     try {
	            

	            // Crear carpeta de destino
	            File carpeta = new File("src/comparadorArchivos/copias");

	            if (!carpeta.exists()) {
	                carpeta.mkdir(); // o mkdirs() si hay subcarpetas
	            }

	            // Crear Paths de origen
	            Path origen1 = Path.of(ruta1);
	            Path origen2 = Path.of(ruta2);

	            // Crear Paths de destino dentro de la carpeta "copias"
	            Path destino1 = Path.of(carpeta.getPath(), origen1.getFileName().toString() + "_copia");
	            Path destino2 = Path.of(carpeta.getPath(), origen2.getFileName().toString());

	            // Copiar archivos
	            Files.copy(origen1, destino1, StandardCopyOption.REPLACE_EXISTING);
	            Files.copy(origen2, destino2, StandardCopyOption.REPLACE_EXISTING);

	            System.out.println("✅ Archivos copiados correctamente en: " + carpeta.getPath());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
