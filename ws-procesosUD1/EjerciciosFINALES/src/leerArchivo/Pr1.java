package leerArchivo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Pr1 {

	public static void main(String[] args) throws Exception {

		// Leo la info enviada por el padre
		// Es linea a linea el contenido del archivo

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			String nombre = "";
			boolean fin = false;
			boolean controlPrecio = false;
			int precio = 0;
			int contadorNombresLeidos = 0;
			int contadorPreciosLeidos = 0;

			while (!fin) {

				// Validar NO VACIO | Nombre
				do {

					String linea = br.readLine();

					if (linea == null) { // Se acaba
						fin = true;
						break;
					}

					// Si es una -X-, indica fin+
					if (linea.equals("-X-")) {
						System.out.println("FIN"); // Envio al padre para que no leas mas
						fin = true;
						break;
					}

					nombre = linea.trim();

					// Notifico
					if (!nombre.isEmpty()) {
						System.out.println("Nombre valido: " + nombre);
						contadorNombresLeidos++;
					}
				} while (nombre.isEmpty()); // Si esta vacio || Lee otra vez

				// Validar precio valido
				do {

					try {
						controlPrecio = false; // Reseteo la variable

						// Leo el precio
						String lineaPrecio = br.readLine();

						// Fin del archivo
						if (lineaPrecio == null) {
							fin = true;
							break;
						}

						// Validaciones logicas del precio
						precio = Integer.parseInt(lineaPrecio.trim());
						if (precio <= 0)
							controlPrecio = true;

						// Notifico
						if (precio > 0) {
							System.out.println("Precio valido: " + precio);
							contadorPreciosLeidos++;
						}
					} catch (Exception e) {
						// Caso de que no sea un numero
						controlPrecio = true;
					}

				} while (controlPrecio); // Vuelve a leer si no era valido

			}

		}
	}
}
