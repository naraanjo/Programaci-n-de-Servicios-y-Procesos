package Procesos1.Procesos1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ExportFile {
	public static void main(String[] args) throws IOException {

		try (InputStreamReader ipsr = new InputStreamReader(System.in, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(ipsr);
				//FileWriter: para escribir caracteres en un archivo
				//FileOutputStream: para escribir bytes en un archivo
				FileWriter fw = new FileWriter(new File("ExportarAFile.txt"));
				BufferedWriter bw = new BufferedWriter(fw)) {

			String line = "";
			do {
				line = br.readLine();
				bw.write(line);
				bw.newLine();
			} while (!line.isBlank());
		}catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}


	}

}
