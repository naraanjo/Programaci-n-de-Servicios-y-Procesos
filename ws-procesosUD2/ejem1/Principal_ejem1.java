package ejem1;


public class Principal_ejem1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coche_ejem1 coche = new Coche_ejem1("1");
		Coche_ejem1 coche2 = new Coche_ejem1("2");
		Coche_ejem1 coche3 = new Coche_ejem1("3");

		/* ESTO NO HACERLO, USAR START PARA INICIAR UN HILO
		coche.run();
		coche2.run();
		coche3.run();*/
		
		//runnable no tiene start, hay que crear un objeto thread y usar start 
		new Thread (coche).start();
		new Thread (coche2).start();
		new Thread (coche3).start();
		
		while(true) {
			System.out.print("****");
		}
	}

}