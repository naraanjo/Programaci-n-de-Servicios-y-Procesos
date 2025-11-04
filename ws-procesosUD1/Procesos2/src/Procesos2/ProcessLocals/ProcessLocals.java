package Procesos2.ProcessLocals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class ProcessLocals {
	//ruta a los .class porque los vamos a invocar
		static File path = new File("C:\\Users\\Alvaro\\workSpaces\\Procesos2\\bin");
		//static File path = new File(".\\bin");
		private final static File FILE = new File("exportT.txt");
		private final static String ENTER = "\n";

		public static void main(String[] args) throws IOException, InterruptedException {

			Process processTr = new ProcessBuilder("java","clase.TransformarString")
					.redirectErrorStream(true).directory(path).start();	
			Process processEx = new ProcessBuilder("java","clase.ExportFile",FILE.toString())
					.redirectErrorStream(true).directory(path).start();

			//OutputStream: stream de datos de salida asociado a la entrada estandar del proceso hijo processTr
			OutputStream osProcTrStream = processTr.getOutputStream();
			//InputStream: stream de datos de entrada asociado a la salida estandar del proceso hijo processTr
			InputStream isProcTrStream = processTr.getInputStream();
			
			//OutputStream: stream de datos de salida asociado a la entrada estandar del proceso hijo processEx
			OutputStream osProcExStream = processEx.getOutputStream();

			System.out.println("Introduce texto a formatear:");

			InputStreamReader ipsr = new InputStreamReader(System.in, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(ipsr);
			BufferedReader brTr = new BufferedReader(new InputStreamReader(isProcTrStream,Charset.forName("UTF-8")));
			//BufferedReader brTr = new BufferedReader(new OputStreamReader(osProcTrStream,Charset.forName("UTF-8")));
			String line = "";

			do {
				line = br.readLine();

				osProcTrStream.write((line+ENTER).getBytes());
				osProcTrStream.flush();

				String lineTr = brTr.readLine();
				System.out.println(lineTr);

				osProcExStream.write((lineTr+ENTER).getBytes());
				osProcExStream.flush();

			} while (!line.isBlank());

			if (processEx.waitFor()==0 && processTr.waitFor() ==0)
				System.out.println("Terminó la ejecución");
			else
				System.out.println("Se produjo un fallo");

		}
}
