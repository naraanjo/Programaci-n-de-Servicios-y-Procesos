package ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcesoHijo1 {

	public static void main(String[] args) throws Exception {

		
		int numeroRecibido = 0;
		int numeroInicio = 0; // Numero desde el que comienza el sumatorio
		int total = 0;
		
		// Leo el numero enviado del PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
		
			numeroRecibido=Integer.parseInt(br.readLine()); // Parseo el numero recibido
			numeroInicio = Integer.parseInt(br.readLine());	// Desde donde comienza el sumatorio
			
		}
		

		// Sumatorio del numero introducido
		// Controlando las mitades
		for (int i = numeroInicio+1; i <= numeroRecibido; i++) {
			total += i;
		}
		
		// Envio el valor al PP
		System.out.println(total);

	}
}
