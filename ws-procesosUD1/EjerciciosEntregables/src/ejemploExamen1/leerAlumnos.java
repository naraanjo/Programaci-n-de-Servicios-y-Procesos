package ejemploExamen1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class leerAlumnos {

	public static void main(String[] args) throws Exception {

		
		String nombre;
		int edad;
		int max = 0;
		int min = 100;
		String nombreMin ="";
		String nombreMax="";
		boolean numeroValido = true;
		int contador = 0;
		
		
		// Argumento del PP al PH
		if(args.length != 1) {
			throw new Exception("Numero de argumentos incorrectos");
		}
		String argumento = "src/ejemploExamen1/" + "S" + args[0];
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		

		// Funcionalidad logica del ejercicio
		do {

			numeroValido = true;
			// Valido el nombre
			do {
				System.out.println("Introduce el nombre del alumno");
				nombre = br2.readLine();

			} while (nombre.isEmpty() || nombre.length() == 0);

			// Valido la edad
			do {
				try {
					numeroValido = true;

					if (!nombre.equals("*")) {

						do {
							System.out.println("Introduce la edad");
							edad = Integer.parseInt(br2.readLine());
						} while (edad < 1 || edad > 99);

						if(edad > max) {
							max=edad;
							nombreMax = nombre;
						}
						
						if(edad < min) {
							min = edad;
							nombreMin = nombre;
						}
						contador++;

					}

				} catch (Exception e) {
					numeroValido = false;
					System.out.println("Introduce un numero");
				}
				
			} while (!numeroValido);

		} while (!nombre.equals("*"));
		
		

		// Escribo en el fichero
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(argumento))){
			bw.write("Numero de alumnos: " + contador);
			bw.newLine();
			bw.write("Alumno con mas edad: " + nombreMax + " | " +  max + " años");
			bw.newLine();
			bw.write("Alumno con menos edad: " + nombreMin + " | " +  min + " años");
			bw.newLine();
			bw.flush();
		}

	}

}
