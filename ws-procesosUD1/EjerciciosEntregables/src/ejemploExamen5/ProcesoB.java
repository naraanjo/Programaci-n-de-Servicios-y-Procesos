package ejemploExamen5;

public class ProcesoB {
	
	
	public static void main(String[] args) throws Exception {
		
		// Debe de recibir un solo args
		if(args.length != 1) {
			throw new Exception("Numero de argumentos no valido");
		}
		
		// Recibo la palabra de PP
		String palabra = args[0];
		
		// Cuento la cantidad de caracteres de la palabra
		int cantidadCaracteres = palabra.length();
		
		// Escribo unicamente el numero de caracteres
		System.out.println(cantidadCaracteres); // Recibe el PP y lo pasa a PHC
		
	}
}
