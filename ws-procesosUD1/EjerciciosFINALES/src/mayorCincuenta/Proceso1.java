package mayorCincuenta;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Proceso1 {

	public static void main(String[] args) throws Exception {

		int numeroRecibido = 0;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			// Recibo el numero enviado por el padre
			numeroRecibido = Integer.parseInt(br.readLine()); // Parseo
		}

		if(numeroRecibido > 50) {
			System.out.println("Numero recibido: " + String.valueOf(numeroRecibido));
			System.out.println("Resultado: Mayor que 50");
		}else {
			System.out.println("Numero recibido: " + String.valueOf(numeroRecibido));
			System.out.println("Resultado: Menor que 50");
		}
	}
}
