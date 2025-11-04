package ejemploExamen1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.File;

public class Lanzador {

	public  void Lanzar(String[] args) throws Exception {

		Scanner teclado = new Scanner(System.in);
		
		if(args.length == 0) {
			throw new Exception("Numero de argumentos incorrectos");
		}
		String argumento = args[0];

		// Inicio el proceso
		try {
			int contador = 1;

			for(String fichero: args) {
				System.out.println("Fichero " + contador);
				ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen1.leerAlumnos", fichero);
				pb.redirectError(new File("Errores2.txt"));
				Process proceso = pb.start();
				
				// Lectura y escritura del PH
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));

				BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				
				// Leo lo que saque el PH
				String linea = "";
				while((linea = br.readLine()) != null) {
					System.out.println(linea);
					
					if(linea.contains("Introduce")) {
						String dato = teclado.nextLine();
						bw.write(dato);
						bw.newLine();
						bw.flush();
					}
				}
				contador++;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar(args);
	}
}

