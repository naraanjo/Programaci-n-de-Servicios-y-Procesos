package ejemploExamen4Cadena;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileWriter;


public class Lanzador {

	public void Lanzar() {
		Scanner teclado = new Scanner(System.in);
		try {
			// Pido una cadena al usuario
			System.out.println("Introduce una cadena");
			String cadena = teclado.nextLine(); // Leo la cadena del usuario
			
			
			String rutaBin = new File("bin").getAbsolutePath();		
			
			// Inicializo y creo el proceso
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", rutaBin,"ejemploExamen4Cadena.reverse", cadena); // Paso la cadena al PH
			pb.redirectError(new File("src/ejemploExamen4Cadena/errores.txt"));
			
			// Creacion del proceso
			Process proceso = pb.start();
			proceso.waitFor(); // Padre espera a que termine
			
			// Leo la salida del PH - EXITCODE VALUE
			int exitCode = proceso.exitValue();
			
					switch (exitCode) {
					// Segun que sea la linea
					case 1: {
						System.out.println("Cadena vacia o longitud cero");
						break;
					}
					
					case 2:{
						System.out.println("Palindromo");
						break;
					}
					
					case 3:{
						System.out.println("No palindromo");
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + exitCode);
					}
				
			
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		Lanzador l = new Lanzador();
		l.Lanzar();
	}
}
