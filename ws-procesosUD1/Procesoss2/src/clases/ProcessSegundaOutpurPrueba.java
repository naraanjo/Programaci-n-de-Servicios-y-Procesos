package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ProcessSegundaOutpurPrueba {

	private static final String DELIMITER = "\n";
	private static final String PWS = "powershell";
	private static final String COMMAND_HELP = "help";
	private static final String COMMAND_TREE = "tree";
	private static final String COMMAND_DIR = "dir";

	public static void main(String[] args) throws IOException, InterruptedException {

		// En caso de error pone el mensaje de error, en la salida estandar
		Process proc1 = new ProcessBuilder(PWS, COMMAND_HELP).redirectErrorStream(true).start(); // Proceso-1
																									// -porwershell +
																									// comando,
		Process proc2 = new ProcessBuilder(PWS, COMMAND_TREE).redirectErrorStream(true).start(); // Proceso-2
																									// -powershell +
																									// comando

		// Thread.sleep(6000);
		// Recoge informacion del procoeso1 - no legible
		InputStream inputStream1 = proc1.getInputStream();
		//Recoge informacion del proceso2 - no legible
		InputStream inputStream2 = proc2.getInputStream();

		// Para poder pasar el flujo de bytes ilegible, a caracteres legibles
		BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream1, "UTF-8")); // Añado el input ilegible, para poder leerlo
		BufferedReader br2 = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));

		
		var lines1 = br1.lines(); // Vuelca el contenido de cada bufferReader, todas las lineas
		var lines2 = br2.lines(); 
		
		osea
		
		// Thread.sleep(6000);ay entie
		
		// Recoge los datos de forma similar a una lista -  Esto ya se queda como una cadena normal
		String s1 = lines1.collect(Collectors.joining(DELIMITER)); // Separa los datos segun el demiliter, lo logico mediante /n
		String s2 = lines2.collect(Collectors.joining(DELIMITER));


		// Al terminal el proceso imprime el contenido por defecto
		proc1.onExit().defaultExecutor().execute(() -> System.out.println(s1));
		proc2.onExit().defaultExecutor().execute(() -> System.out.println(s2));

		
		// Lo mismo que antes pero con un proceso-3
		Process proc3 = new ProcessBuilder(PWS, COMMAND_DIR).redirectErrorStream(true).start();
		InputStream inputStream3 = proc3.getInputStream();
		BufferedReader br3 = new BufferedReader(new InputStreamReader(inputStream3, "UTF-8"));
		var lines3 = br3.lines();
		String s3 = lines3.collect(Collectors.joining(DELIMITER));
		proc3.onExit().defaultExecutor().execute(() -> System.out.println(s3));

		Thread.sleep(3000);
		
		// El padre espera a que terminen los hijos -  ya que si no no podremos ver el resultado
		proc1.waitFor();
		proc2.waitFor();
		proc3.waitFor();
		
		// Destruye todos los procesos
		proc1.destroy();
		proc2.destroy();
		proc3.destroy();

	}
}
