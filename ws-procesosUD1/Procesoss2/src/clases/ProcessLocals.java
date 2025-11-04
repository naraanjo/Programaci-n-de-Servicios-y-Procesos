package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ProcessLocals {
	// ruta a los .class porque los vamos a invocar
	static File path = new File("C:\\Users\\Alvaro\\workSpaces\\ws-procesos\\Procesoss2\\bin");
	// static File path = new File(".\\bin");
	private final static File FILE = new File("exportT.txt");
	private final static String ENTER = "\n";

	public static void main(String[] args) throws IOException, InterruptedException {

		// Proceso hijo-1
		Process procesoHijo1 = new ProcessBuilder("java", "clase.TransformarString").redirectErrorStream(true)
				.directory(path).start();

		// Proceso hijo-2
		Process procesoHijo2 = new ProcessBuilder("java", "clase.ExportFile", FILE.toString()).redirectErrorStream(true)
				.directory(path).start();

		// OutputStream: Datos que envia de padre --> hijo
		OutputStream osProcTrStream = procesoHijo1.getOutputStream();
		// InputStream: Datos que envia de hijo --> padre
		InputStream isProcTrStream = procesoHijo1.getInputStream();

		// OutputStream: Datos que envia el padre --> hijo
		OutputStream osProcExStream = procesoHijo2.getOutputStream();

		System.out.println("Introduce texto a formatear:");

		Scanner sc = new Scanner(System.in, "UTF-8");


		BufferedReader brTr = new BufferedReader(new InputStreamReader(isProcTrStream, Charset.forName("UTF-8")));
		// BufferedReader brTr = new BufferedReader(new
		// OputStreamReader(osProcTrStream,Charset.forName("UTF-8")));
		String line = "";

		do {
			line = sc.nextLine();
			
			// El padre le pasa al proceso hijo1 informacion en bytes
			osProcTrStream.write((line + ENTER).getBytes());
			osProcTrStream.flush();

			// El padre lee la informacion del proceso hijok
			String lineTr = brTr.readLine();
			System.out.println(lineTr);

			// El padre envia la informacion al otro hijo
			osProcExStream.write((lineTr + ENTER).getBytes());
			osProcExStream.flush();

		} while (!line.isBlank());

		if (procesoHijo2.waitFor() == 0 && procesoHijo1.waitFor() == 0)
			System.out.println("Terminó la ejecución");
		else
			System.out.println("Se produjo un fallo");

	}
}
