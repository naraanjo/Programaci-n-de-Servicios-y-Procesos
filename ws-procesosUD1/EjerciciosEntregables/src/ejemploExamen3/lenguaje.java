package ejemploExamen3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class lenguaje {

	public static void main(String[] args) throws Exception {

		Random random = new Random();

		String nombreFichero = args[0];
		int cantidadPalabras = Integer.parseInt(args[1]);
		
		// Nombre del fichero
		File resultado = new File(nombreFichero);
		if (!resultado.exists()) {
			resultado.createNewFile();
		}

		// Escribe en el fichero
		// Palabras de 5 caracteres

		try(BufferedWriter bw =new BufferedWriter(new FileWriter(resultado, true))) { // Sobreescribe

			StringBuilder letrasSuma = new StringBuilder();
				
			for(int j = 0; j < cantidadPalabras; j++) {
				// Genero palabra de  5 letras
				for (int i = 0; i < 5; i++) {
					char letra = (char) (random.nextInt(26) + 'a'); // Genera una letra aleatoria
					letrasSuma.append(letra); // Añade la letra al StringBuilder
				}
				bw.write(letrasSuma.toString()); // Escribe la palabra en el fichero
				bw.newLine();
				letrasSuma.setLength(0); // Limpia

			}
			bw.newLine();
			bw.flush();	
		} ;
		
	}
}
