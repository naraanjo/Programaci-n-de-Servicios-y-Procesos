package lecturaArchivoAlumnos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Clase con funcionamiento autonomo
public class Hijo {
	
    public static void main(String[] args) throws Exception {
        // Variables para máximo y mínimo
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        String alumMax = "";
        String alumMin = "";
        String ruta = "";
        
    	// Leo los datos que envia el PH
    	try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
    		
    		  String nombre = "";
              int edad = 0;
              boolean fin = false;
              boolean control = false;

              while (!fin) {

                  // Leer nombre válido
                  do {
                	//  System.out.println("Introduce el nombre");
                      String linea = br.readLine();
                      if (linea == null) {
                          fin = true;
                          break;
                      }
                      nombre = linea.trim();
                  } while (nombre.isEmpty()); // ignorar líneas vacías

                  if (fin || nombre.equals("*")) {
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
                      } catch (Exception e) {
                          control = true; // si no es número, seguir leyendo
                      }
                  } while (control);

                  if (fin) break;

                  // Actualizar máximo y mínimo
                  if (edad > max) {
                      max = edad;
                      alumMax = nombre;
                  }
                  if (edad < min) {
                      min = edad;
                      alumMin = nombre;
                  }
              }
    	}
    	
    	  // Imprimir resultados  - Los debe leer el PP
        System.out.println("Alumno con mayor edad: " + alumMax + " (" + max + ")");
        System.out.println("Alumno con menor edad: " + alumMin + " (" + min + ")");
    }

}
