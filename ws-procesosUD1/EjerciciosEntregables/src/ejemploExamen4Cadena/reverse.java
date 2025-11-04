package ejemploExamen4Cadena;

public class reverse {

	// ESTE PH NO VISUALIZA NADA EN PANTALLA
	// SOLO SACA SYSTEM.EXIT
	// RECOGIDO COMO EXITVALUE
	public static void main(String[] args) throws Exception {
		
		if(args.length != 1) {
			throw new Exception("Numero de argumentos invalidos");
		}
		
		String cadenaRecibida = args[0];
		
		if(cadenaRecibida.length() == 0 || cadenaRecibida.isBlank()) {
			System.exit(1);
		};
		
		// Si es palindroma
		StringBuilder cadenaReversa = new StringBuilder(cadenaRecibida);
		//Caso de que sea palindroma
		if(cadenaRecibida.equals(cadenaReversa.reverse().toString())) {
			System.exit(2);
		}else {
			System.exit(3); // Caso de NO palindroma
		}
	}
}
