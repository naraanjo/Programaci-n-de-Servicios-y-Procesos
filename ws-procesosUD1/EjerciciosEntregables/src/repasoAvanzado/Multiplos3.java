package repasoAvanzado;

public class Multiplos3 {
	
	
	public static void main(String[] args) throws Exception {
	
		// Recibe 1 numero
		if(args.length != 1) {
			throw new Exception("Numero de argumentos invalidos");
		}
		
		// Numero que recibo
		// Parseo
		int numeroRecibido = Integer.parseInt(args[0]);
		
		// Paso al PP, si es multiplo o no
		if(numeroRecibido % 3 == 0) {
			System.out.println("si " + String.valueOf(numeroRecibido));
		}else {
			System.out.println("no " + String.valueOf(numeroRecibido));
		}
	}

}
