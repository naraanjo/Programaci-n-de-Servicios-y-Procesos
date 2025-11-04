package ejemploExamen1punto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Lanzador {

	public void Lanzar(String[] args) throws Exception {

		if (args.length == 0)
			throw new Exception("No se han introducido argumentos a [PP]");

		// Creo el proceso
		try {
			System.out.println(args.length);
			// Recorro la cantidad de fichero que hay en los argumentos
			for (int i = 0; i < args.length; i++) {
				System.out.println(i);
				File archivo = new File("src/ejemploExamen1punto2/" + args[i]);
				if (!archivo.exists())
					throw new Exception("El archivo no existe");

				// Inicio y creacion del proceso
				ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen1punto2.LeerAlumno",
						archivo.toString());
				pb.redirectError(new File("src/ejemploExamen1punto2/errores2.txt"));
				Process proceso = pb.start();

				// Lectura de la salida del hijo
				try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
					String linea = "";
					while ((linea = br.readLine()) != null) {
						System.out.println("[PH] " + linea);
					}
				}
				proceso.waitFor();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar(args);
	}
}
