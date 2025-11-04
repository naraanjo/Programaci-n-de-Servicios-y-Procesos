package ejemploExamen5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import ejercicio3.LanzadorAnalizador;

// PP recibe por argumentos palabras llama a PHA
// Este cuenta los caracteres, lo envia a PHB
// Este ecribe no si tiene menos de 5
// Si, si tiene 5 o mas caracteres
public class Lanzador {

	public void Lanzar(String args[]) {

		// Recorro los argumentos ( palabras )
		String[] listaArgumentos = args;

		for (String argumento : listaArgumentos) {

			try {
				// Creo e inicializo el proceso
				ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen5.ProcesoB", argumento); // Paso la palabra de args 1 a 1
				pb.redirectError(new File("src/ejemploExamen5/erroresPH1.txt"));
				Process proceso1 = pb.start();

				// Lector del PH-A
				try (BufferedReader br1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()))) {

					String linea1 = "";
					while ((linea1 = br1.readLine()) != null) {
						
						// Aqui hay una palabra que se envia a PH-C
						// Creo el proceso PH-B, que recibe cada palabra de PH-B
						ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen5.ProcesoC",linea1); // Paso la palabra de args 1 a 1
						pb2.redirectError(new File("src/ejemploExamen5/erroresPH2.txt"));
						Process proceso2 = pb2.start();

						// Escribo al PH-C
						try (BufferedWriter bw2 = new BufferedWriter(
								new OutputStreamWriter(proceso2.getOutputStream()))) {
							bw2.write(linea1); // Mando la linea al proceso PH-C
							bw2.newLine();
							bw2.flush();
						}
						
						
						// Leo la info de PHC
						try(BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))){
							String linea2="";
							
							while((linea2 = br2.readLine()) != null) {
								System.out.println(linea2); // Saco la info del PH-C
							}
						}
						
						proceso2.waitFor();

					}
				}
				proceso1.waitFor();
				

			} catch (Exception e) {
				System.out.println("Error no reconocido");
			}
		}
	}

	public static void main(String[] args) {
		
		Lanzador l = new Lanzador();
		l.Lanzar(args);
	}
}
