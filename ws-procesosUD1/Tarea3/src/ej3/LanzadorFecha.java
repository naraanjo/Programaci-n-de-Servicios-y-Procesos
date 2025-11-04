package ej3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LanzadorFecha {

	public void lanzarFecha() throws IOException {
		Scanner teclado = new Scanner(System.in);

		// Formato de fecha
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = null;
		boolean fechaValida = false;

		// Valido fecha
		do {
			System.out.println("Introduce una fecha en formato; dd/MM/yyyy. La fecha debe ser mayor que la actual");
			String fechaUsuario = teclado.nextLine();

			try {
				fecha = LocalDate.parse(fechaUsuario, formato);
				LocalDate fechaActual = LocalDate.now();

				if (fechaActual.isAfter(fecha)) {
				System.out.println("Debes introducir una fecha posterior");
					fechaValida = false;
				} else {
					fechaValida = true;
				}

			} catch (DateTimeParseException e) {
				System.out.println(e.getMessage());
			}
		} while (!fechaValida);

		try {
			// Inicio el proceso
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ej3.CalcularFecha");
			pb.redirectErrorStream(true); // Errores por consolo
			Process proceso = pb.start(); // Creo el proceso

			// Envio la fecha al PH
			// Podria enviarlo por parametro, pero lo he hecho asi para practicar
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
				bw.write(fecha.format(formato));
				bw.flush();
			}

			int exitCode = proceso.waitFor();

			
			// Leo la informacion del PH
			try(BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))){
				String linea = "";
				
				while((linea = br.readLine()) != null) {
					System.out.println("[PH] " + linea);
				}
				
			}
			System.out.println("Proceso hijo finalizo con codigo: " + exitCode);
		} catch (Exception e) {
			System.err.println("Error de E/S!!!");
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		LanzadorFecha l = new LanzadorFecha();
		l.lanzarFecha();
	}

}
