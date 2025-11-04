package ejemploExamen2;

import java.util.ArrayList;

public class aleatorios {

	// Genera 40 numeros aleatorios que le pasara al padre
	public static void main(String[] args) throws Exception {
	
		
		for(int i= 0; i<40; i++) {
			
			int aleator = (int) (Math.random() * (100-1+1)+1);
			System.out.println(aleator); // Paso al padre la info 
		}
	
	}
}
