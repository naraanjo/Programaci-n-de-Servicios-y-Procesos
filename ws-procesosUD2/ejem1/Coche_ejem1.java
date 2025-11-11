package ejem1;


public class Coche_ejem1 implements Runnable {
	private String simbolo;
	
	
	public Coche_ejem1(String sim) {
		this.simbolo = sim;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		while(true) {
			System.out.print(simbolo);
		}
	}
}
