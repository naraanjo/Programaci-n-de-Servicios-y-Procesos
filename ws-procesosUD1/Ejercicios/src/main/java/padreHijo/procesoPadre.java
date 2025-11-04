package padreHijo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class procesoPadre {

	public static void main(String[] args) {
	
		   try {
	            // 1️⃣ Creamos el proceso hijo
	            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "padreHijo", "Ejercicios.src/main/java/procesoHijo");
	            Process hijo = pb.start();

	            // 2️⃣ Obtenemos el OutputStream para enviar datos al hijo
	            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(hijo.getOutputStream()));

	            // 3️⃣ Enviamos varias líneas
	            escritor.write("Hola hijo");
	            escritor.newLine();
	            escritor.write("¿Cómo estás?");
	            escritor.newLine();
	            escritor.write("fin"); // Señal para terminar
	            escritor.newLine();
	            escritor.flush(); // Muy importante para que el hijo reciba los datos

	            // 4️⃣ Capturamos la salida del hijo (stdout)
	            BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
	            String linea;
	            while((linea = br.readLine()) != null) {
	                System.out.println("PADRE> " + linea);
	            }

	            // 5️⃣ Esperamos a que termine el hijo
	            hijo.waitFor();
	            System.out.println("El proceso hijo terminó con código " + hijo.exitValue());

	        } catch(IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	}

}
