package padreHijo;

import java.util.Scanner;

public class procesoHijo {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        System.out.println("El hijo empieza a leer datos:");
        while(sc.hasNextLine()) {
            String linea = sc.nextLine();
            if(linea.equalsIgnoreCase("fin")) break; // "fin" termina la lectura
            System.out.println("Hijo recibió: " + linea);
        }
        System.out.println("Hijo finalizado");
        sc.close();
    }
}