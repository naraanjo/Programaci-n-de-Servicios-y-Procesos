package ejercicio2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Proceso1 {

	public static void main(String[] args) throws Exception {

		// Leo la informacion enviada por el padre
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			
			String nombre = "";
			int edad = 0;
			boolean fin = false;
			boolean control = false;

			while (!fin) {

				// Leer nombre válido
				do {
					// System.out.println("Introduce el nombre");
					String linea = br.readLine();
					if (linea == null) {
						fin = true;
						break;
					}
					nombre = linea.trim();
				} while (nombre.isEmpty()); // ignorar líneas vacías

				if (fin ) {
					System.out.println("fin");
					fin = true;
					break;
				}
				
				  // Leer edad válida
                do {
                    try {
                  	  //System.out.println("Introduce la edad");
                        control = false;
                        String lineaEdad = br.readLine();
                        if (lineaEdad == null) {
                            fin = true;
                            break;
                        }
                        edad = Integer.parseInt(lineaEdad.trim());
                        if (edad < 1 || edad > 99) control = true;
                        
                        if(edad> 18) {
                        	System.out.println("Edad: " + edad + "|" + "Nombre: " + nombre.toUpperCase());
                        }else {
                        	System.out.println("Edad: " + edad + "|" + "Nombre: " + nombre.toLowerCase());
                        }
                    } catch (Exception e) {
                        control = true; // si no es número, seguir leyendo
                    }
                } while (control);
                

                if (fin) break;

			}
			
			
			
		}
	}

}
