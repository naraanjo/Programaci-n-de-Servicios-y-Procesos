package ejemploExamen6;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PH1 {

	// Recibe 1 args - rutaFichero
	public static void main(String[] args) throws Exception {
		
		if (args.length != 1 ) {
			throw new Exception("Numero de argumentos invalidos");
		}
	
		// Ruta del archivo por argumentos
		String rutaArchivo = args[0];
		File archivo = new File (rutaArchivo);
		
		// Caso de que el archivo no exista
		if(!archivo.exists()) {
			throw new Exception("Archivo inexistente");
		}
		
		int contador = 0;
		// Leo el archivo
		try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
			String linea = "";
			while((linea = br.readLine()) != null) {
				++contador; // Cuento las lineas del archivo
			}
		}
		
		// Envio las linea del fichero al PP
		System.out.println(contador);
	}
}
