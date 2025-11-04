package EjercicioContarVocales;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LanzadorVocales {
    
    public void lanzarContador(String archivoEntrada, String vocal, String archivoSalida) {
     // Clase a ejecutar
     String clase = "EjercicioContarVocales.ContadorVocales";
     // Directorio actual
     File path = new File(".");
     // Constructor del proceso
     ProcessBuilder pb;
     
     try {
    	 // Validar que el archivo de entrada existe
    	 pb = new ProcessBuilder("java", "-cp", "bin", clase, archivoEntrada, vocal, archivoSalida).directory(path);
    	 // Redirigir errores al mismo flujo que la salida estándar
    	 pb.redirectError(new File("errorVocales.txt"));
    	 // Redirigir la salida estándar a un archivo
    	 pb.redirectOutput(new File(archivoSalida));
    	 // Iniciar el proceso
		 pb.start();
 
     } catch (IOException e) {
		 e.printStackTrace();
	 }
     
    }
    public static void main(String[] args) {
    	Scanner teclado = new Scanner(System.in);
    	String vocal = "";
    	// Pedir vocal con validación
    	do {
    		System.out.print("Introduce una vocal (a, e, i, o, u): ");
			vocal = teclado.nextLine().trim();
    	}while (!vocal.matches("[aeiouAEIOUáéíóúÁÉÍÓÚ]") || vocal.length() != 1);
    	//Cerrar el scanner"
    	teclado.close();
    	
    	// Crear instancia de LanzadorVocales y lanzar el contador pasandole los parámetros
    	LanzadorVocales l = new LanzadorVocales();
    	l.lanzarContador("entrada.txt", vocal, "Resultado" +vocal+ ".txt");
    	
    }
}