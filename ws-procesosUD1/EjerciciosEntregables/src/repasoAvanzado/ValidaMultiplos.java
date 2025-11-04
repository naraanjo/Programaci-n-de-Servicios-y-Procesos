package repasoAvanzado;

public class ValidaMultiplos {

	public static void main(String[] args) throws Exception {

		// Recibe 1 palabra
		if (args.length != 1) {
			throw new Exception("Numero de argumentos invalidos");
		}

		String palabraRecibida = args[0];

		if (palabraRecibida.contains("si")) {
			System.out.println("Multiplo recibido: " + palabraRecibida);
		} else if (palabraRecibida.contains("no")) {
			System.out.println("No multiplo: " + palabraRecibida);
		}
	}
}
