package ejem4;

class hiloDurmiente extends Thread {


	public void run() {

		long i;
		while(true) {
			System.out.println("Dormimos...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Si salta esta excepción, no hace nada...
			}
			// Espera activa.
			System.out.println("Espera activa...");
			//0x7FFFFFFF es el valor positivo máximo (32 bits)
			for (i = 0; i < 0x7FFFFFFF; ++i)
				;
		}
	} 
}


public class ej1Sleep {

	public static void main(String[] args) {

		hiloDurmiente hd = new hiloDurmiente();
		hd.start();

		while(true)
			;

	}

}