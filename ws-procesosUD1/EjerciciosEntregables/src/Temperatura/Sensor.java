package Temperatura;

import java.io.IOException;

public class Sensor {

	public static void main(String[] args) throws IOException {
		
		// Numero aleatorio entre 10 y 50
		int aleator = (int) (Math.random() * (50 - 10 + 1)+ 10);
		
		System.out.println(aleator);
		if(aleator > 30) {
			System.out.println(true);
		}else {
			System.out.println(false);
		}
	}
}
