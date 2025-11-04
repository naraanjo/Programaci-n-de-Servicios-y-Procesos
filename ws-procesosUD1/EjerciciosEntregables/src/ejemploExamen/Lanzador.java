package ejemploExamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lanzador {

	// Funcion para generar numeros entre 2 y 10
	public void Lanzar(String[] args) {

		String[] listaNumeros = args;
		// Inicializo el proceso
		try {
			for (int i = 0; i < listaNumeros.length; i++) {

				ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen.Multiplos",
						listaNumeros[i]);
				ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen.PH2", listaNumeros[i]);

				pb.redirectErrorStream(true);
				pb2.redirectErrorStream(true);

				// Creacion del proceso
				Process proceso = pb.start();
				Process proceso2 = pb2.start();

				// Leo la salida del hijo
				try (BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
						BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(proceso2.getOutputStream()))) {
					String linea;
					while ((linea = br.readLine()) != null) {
						if (linea.equals("si")) {
							bw2.write("si");
						} else if (linea.equals("no")) {
							bw2.write("no");
						}
						bw2.newLine();
						bw2.flush();
					}
				}

				// 🔹 Leer la salida del proceso PH2 (para mostrar en consola)
                try (BufferedReader br2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()))) {
                    String salida;
                    while ((salida = br2.readLine()) != null) {
                        System.out.println(salida);
                    }
                }

			}

		} catch (Exception e) {
			System.out.println("Error al crear el proceso");
		}

	}

	public static void main(String[] args) throws IOException {

		Lanzador l = new Lanzador();
		l.Lanzar(args);
	}
}
