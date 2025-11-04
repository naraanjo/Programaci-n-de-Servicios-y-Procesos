package ej1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Creador {

	public void crearProcesos() {

		// Matriz con comandos
		// Cada vez que se ejecuta el padre se realizara un comando aleatorio
		String[][] comandos = { { "cmd", "/c", "echo Hola Mundo" }, { "cmd", "/c", "dir" }, { "cmd", "/c", "ver" },
				{ "cmd", "/c", "hostname" }, { "cmd", "/c", "whoami" } };
		// Numero aleatorio para seleccionar el comando. Caada fila es un comando
		int numeroComando = (int) (Math.random() * comandos.length);

		Scanner teclado = new Scanner(System.in);
		String respuestaUsuario = "";

		// Pido al usuario que introduzca si/no. Para saber si el proceso padre se debe
		// esperar
		do {
			System.out.println(
					"Introduce -si- o -no-\n-si- determina que el proceso padre debe esperar a que el proceso hijo termine su ejecución\n"
							+ "-no- determina que el proceso padre no debe esperar a que el proceso hijo termine su ejecución.");

			respuestaUsuario = teclado.nextLine().trim().toLowerCase();

		} while (!respuestaUsuario.equals("si") && !respuestaUsuario.equals("no"));

		// Inicializo el proceso
		ProcessBuilder pb = null;
		try {
			pb = new ProcessBuilder(comandos[numeroComando]); // Proceso aleatorio = fila
			pb.redirectErrorStream(true); // Fusion stderr y stdout
			Process procesoHijo = pb.start();

			if (respuestaUsuario.equals("si")) {

				// Muestro la salida del proceso hijo - comando
				try (BufferedReader br = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()))) {

					String linea = "";
					System.out.println("---------------- SALIDA DEL PROCESO HIJO - COMANDO ----------------");
					while ((linea = br.readLine()) != null) {
						System.out.println(linea); // Imprimo toda la informacion que saca el comando - creando un flujo
													// de datos del hijo al padre
					}
					// El padre espera al hijo
					int exitCode = procesoHijo.waitFor();
					System.out.println("El proceso hijo ha terminado con codigo: " + exitCode);

				}

			} else {
				// Caso de que el padre no tenga que esperar al proceso hijo
				System.out.println("El proceso padre no debe esperar a la finalización del proceso hijo");
			}

		} catch (InterruptedException e) {
			System.err.println("Error en la interrupcion del proceso -waitFor()- : " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Eror de E/S !! " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void main(String[] args) {

		Creador creador = new Creador();
		creador.crearProcesos();

	}

}
