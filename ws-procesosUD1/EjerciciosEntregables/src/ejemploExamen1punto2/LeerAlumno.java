package ejemploExamen1punto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeerAlumno {

    public static void main(String[] args) throws Exception {
        // Variables para máximo y mínimo
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        String alumMax = "";
        String alumMin = "";
        String ruta = "";

        if (args.length == 0)
            throw new Exception("Numero de argumentos invalidos");

        ruta = args[0];
        System.out.println(ruta);
        File archivo = new File(ruta);

        // Lee el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String nombre = "";
            int edad = 0;
            boolean fin = false;
            boolean control = false;

            while (!fin) {

                // Leer nombre válido
                do {
                    String linea = br.readLine();
                    if (linea == null) {
                        fin = true;
                        break;
                    }
                    nombre = linea.trim();
                } while (nombre.isEmpty()); // ignorar líneas vacías

                if (fin || nombre.equals("*")) {
                    fin = true;
                    break;
                }

                // Leer edad válida
                do {
                    try {
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

        // Imprimir resultados en un archivo de salida
        System.out.println("Alumno con mayor edad: " + alumMax + " (" + max + ")");
        System.out.println("Alumno con menor edad: " + alumMin + " (" + min + ")");
    }
}
