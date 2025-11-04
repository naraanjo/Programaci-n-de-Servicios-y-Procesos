package ejemploExamen5;

public class ProcesoC {

	public static void main(String[] args) throws Exception {
		
		// Debe recibir 1 args
		if(args.length != 1) {
			throw new Exception("Error - numero de argumentos invalidos");
		}
		
		// Numero recibido desde PP
		int numero = Integer.parseInt(args[0]);
		
		// Logica +5 caracteres -SI- / else -NO-
		// Info que sera leida por PP
		if(numero >= 5) {
			System.out.println("SI --> Tiene mas de 5 caracteres");
		}else {
			System.out.println("NO --> Tiene menos de 5 caracteres");
		}
		
	}
}
