package ejemploExamen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PH2 {

	public static void main(String[] args) throws Exception {
		String linea = "";
		int numero = Integer.parseInt(args[0]);

		if(args.length!= 1) {
			throw new Exception("ERROR EN EL NUMERO DE ARGUMENTOS" );
		}
		// Recojo la info del padre

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			linea = br.readLine();
		}

		if (linea.equals("si")) {
			System.out.println("[PH2]: SI ES MULTIPLO " + numero);
		} else {
			System.out.println("[PH2]: NO ES MULTIPLO " + numero);
		}

	}
}
