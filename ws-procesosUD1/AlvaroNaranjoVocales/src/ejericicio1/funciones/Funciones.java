package ejericicio1.funciones;

import java.util.ArrayList;
import java.util.List;

public class Funciones {

	// Compruebo que se introduzca una vocal
	// Al llamar a la funcion - control de vocal = null 
	public static boolean validarVocal(String vocalIntroducida) {
		
		ArrayList<Character> listaVocales = new ArrayList<Character>(List.of('a','e','i','o','u'));
		
		char vocal = vocalIntroducida.toLowerCase().charAt(0);
		
		if(listaVocales.contains(vocal)) {
			return true;
		}else {
			throw new IllegalArgumentException("No se ha introducido una vocal || Se introdujo una consonante, simbolo, numero o espacio");
		
		}
		
		
	}
}
