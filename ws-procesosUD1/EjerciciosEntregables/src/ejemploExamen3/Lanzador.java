package ejemploExamen3;

import java.io.File;


public class Lanzador {

	public  void Lanzar() {

		int contador = 0;
		try {

			// Llamao 10 veces al PH
			for (int i = 0; i < 10; i++) {
				contador += 10;

				// Cada vez 10 mas
					// Inicializo el proceso
					ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ejemploExamen3.lenguaje","src/ejemploExamen3/palabras.txt", String.valueOf(contador));
					pb.redirectError(new File("src/ejemploExamen3/erroresExamen3.txt"));

					// Creo el proceso
					Process proceso = pb.start();
					proceso.waitFor();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		Lanzador l = new Lanzador();
		l.Lanzar();
	}

}
