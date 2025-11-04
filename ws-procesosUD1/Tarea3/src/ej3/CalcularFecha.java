package ej3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CalcularFecha {

	public static void main(String[] args) throws IOException {
		
		// Leo la informacion pasada por el PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader((System.in)))){
			
			String	lineaFecha = br.readLine();
		
			
			
			// Formateo la fecha --> string-fecha
			DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaUsuario = LocalDate.parse(lineaFecha, fechaFormato);
			
			// Fecha actual
			LocalDate fechaActual = LocalDate.now();
			
			// Extra los dias entre una fecha y otra
			// NUNCA puede ser negativo, ya que se controlo en la entrada de fecha por el usuario
			long dias = ChronoUnit.DAYS.between( fechaActual,fechaUsuario);
			
			System.out.println("Dias de diferencia entres ambas fechas: " + dias);
			
		}
		
		
	
		
	}
}
