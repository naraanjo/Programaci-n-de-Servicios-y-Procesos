package primo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class PH1 {

	public static void main(String[] args) throws Exception {

		int numeroRecibido;
		// ---- LEO LA INFO DEL PP ----
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")))) {

			// Recibo el numero - parseo String - Int
			numeroRecibido = Integer.parseInt(br.readLine());

		}

		// Verifico si es primo
		if (esPrimo(numeroRecibido)) {
			System.out.println("[PH1] " + numeroRecibido + " es prÍmo."); // Envio a PP
			System.out.println(String.valueOf(numeroRecibido)); // Envio a PP
		} else {
			System.out.println("[PH1] " + numeroRecibido + " no es prÍmo."); // Envio a PP
			System.out.println(String.valueOf(numeroRecibido)); // Envio a PP
		}

	}

	// Función para verificar si un número es primo
	public static boolean esPrimo(int numero) {
		if (numero <= 1)
			return false; // 0 y 1 no son primos
		if (numero == 2)
			return true; // 2 es primo
		if (numero % 2 == 0)
			return false; // múltiplos de 2 no son primos

		// Verifico divisores impares hasta la raíz cuadrada del número
		for (int i = 3; i <= Math.sqrt(numero); i += 2) {
			if (numero % i == 0) {
				return false;
			}
		}
		return true;
	}

}
