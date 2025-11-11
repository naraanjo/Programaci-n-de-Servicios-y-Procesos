package ejem2;


public class Coche_ejem2 extends Thread{
	private String simbolo;
	
	
	public Coche_ejem2(String sim) {
		this.simbolo = sim;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		while(true) {
			System.out.print(simbolo);
			System.out.print(this.simbolo);
		}
	}
	
	
}
