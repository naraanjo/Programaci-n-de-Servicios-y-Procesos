package ejem2;


public class Principal_ejem2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coche_ejem2 coche = new Coche_ejem2("1");
		Coche_ejem2 coche2 = new Coche_ejem2("2");
		Coche_ejem2 coche3 = new Coche_ejem2("3");
		/*coche.start();
		coche2.start();
		coche3.start();*/
		/* ESTO NO HACERLO, USAR START PARA INICIAR UN HILO*/
		coche.run();
		coche2.run();
		coche3.run();
		 
		while(true) {
			System.out.print("****");
		}
	}

}
