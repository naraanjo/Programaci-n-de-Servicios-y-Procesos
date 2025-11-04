package pruebas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pruebas {

	public static void main(String[] args) {

		
		// Random
		int min = -10;
		int max = 10;

		int numero = (int)(Math.random() * (max - min + 1)) + min;
		System.out.println("Número aleatorio entre -10 y 10: " + numero);
		
		// Random tamaño array
	     // Creamos un array de ejemplo de 10 posiciones
        int[] numeros2 = new int[10];

        // Generar una posición aleatoria entre 0 y numeros.length - 1
        int posAleatoria = (int)(Math.random() * numeros2.length);
        
		
		ArrayList<String> nombres = new ArrayList<>();
		nombres.add("Carlos");
		nombres.add("Ana");
		nombres.add("Beatriz");
		nombres.add("David");

		nombres.sort(null); // Ordena de a-z
		Collections.sort(nombres, Collections.reverseOrder());
		System.out.println(nombres);

		// NUMEROS
		// Crear ArrayList de números
		ArrayList<Integer> numeros = new ArrayList<>();
		numeros.add(10);
		numeros.add(5);
		numeros.add(20);
		numeros.add(3);
		numeros.add(15);

		// Mostrar la lista original
		System.out.println("Lista original: " + numeros);

		// Obtener máximo y mínimo usando Collections
		int max = Collections.max(numeros);
		int min = Collections.min(numeros);
		System.out.println("Máximo: " + max);
		System.out.println("Mínimo: " + min);

		// Ordenar la lista de menor a mayor
		Collections.sort(numeros);
		System.out.println("Lista ordenada ascendente: " + numeros);

		// Ordenar la lista de mayor a menor
		Collections.sort(numeros, Collections.reverseOrder());
		System.out.println("Lista ordenada descendente: " + numeros);

		ArrayList<Character> letras = new ArrayList<>(List.of('a', 'b', 'c'));

		// Ahora sí puedes agregar o eliminar elementos
		letras.add('d');
		letras.remove(Character.valueOf('b'));
		
		StringBuilder ejemploStringBuilder = new StringBuilder();
		ejemploStringBuilder.append("Hola");
		
		 Random rand = new Random();
	     int num = rand.nextInt(41) - 20; // genera entre -20 y 20
	     System.out.println(num);
		
	  // De letra a ASCII
	        System.out.print("Introduce una letra o carácter: ");
	        char letra = sc.next().charAt(0); // Toma solo el primer carácter
	        int ascii = (int) letra; // conversión de letra a ASCII
	        System.out.println("El código ASCII de '" + letra + "' es: " + ascii);

	        // De ASCII a letra
	        System.out.print("Introduce un código ASCII (número): ");
	        int codigo = sc.nextInt();
	        char caracter = (char) codigo; // conversión de ASCII a letra
	        System.out.println("El carácter correspondiente a " + codigo + " es: '" + caracter + "'");
	        
		/*
		 *   try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividimos la línea en palabras usando espacios y tabulaciones
                String[] palabras = linea.trim().split("\\s+");
                if(palabras.length == 1 && palabras[0].isEmpty()) continue; // línea vacía
                contador += palabras.length;
            }*/
	}

}
