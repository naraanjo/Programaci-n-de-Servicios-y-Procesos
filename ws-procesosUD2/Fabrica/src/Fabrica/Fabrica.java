package Fabrica;

public class Fabrica {

	public static void main(String args[]) {
		GestorFabrica gest = new GestorFabrica();

		Thread pc = new Thread(new Operario("pc", gest));
		Thread pcarcasa = new Thread(new Operario("pcarcasa", gest));
		Thread monta = new Thread(new Operario("monta", gest));

		pc.start();
		pcarcasa.start();
		monta.start();

	}
}

class GestorFabrica {

	int numeroCarcasas = 0;
	int numeroChips = 0;

	public void arrancarFabrica(String tipo) throws InterruptedException {

		while (true) {
			if (tipo.equals("pc")) {
				crearChip();
			} else if (tipo.equals("pcarcasa")) {
				crearCarcasa();
			} else {

				montarProducto();

			}
		}
	}

	public void montarProducto() throws InterruptedException {

		synchronized (this) {

			while (numeroChips - 2 < 0 || numeroCarcasas - 1 < 0) {

				wait();

			}

			System.out.println("Creando producto");
			System.out.println("Numero de carcasas: " + numeroCarcasas + " - Numero de chips: " + numeroChips);
			numeroChips -= 2;
			numeroCarcasas--;
		}

		Thread.sleep(300);

		synchronized (this) {
			System.out.println("Producto creado");
			System.out.println("Numero de carcasas: " + numeroCarcasas + " - Numero de chips: " + numeroChips);
			notifyAll();
		}
	}

	public synchronized void crearCarcasa() throws InterruptedException {

		while (numeroCarcasas == 10) {

			wait();

		}

		System.out.println("Creando carcasa");
		Thread.sleep(2000);
		numeroCarcasas++;
		System.out.println("Carcasa creada");
		notifyAll();

	}

	public synchronized void crearChip() throws InterruptedException {

		while (numeroChips == 10) {

			wait();

		}

		System.out.println("Creando chip");
		Thread.sleep(2000);
		numeroChips++;
		System.out.println("Chip creado");
		notifyAll();
	}

}

class Operario implements Runnable {

	private String tipo;
	private GestorFabrica gestor;

	// Constructor
	public Operario(String tipo, GestorFabrica gestor) {

		this.tipo = tipo;
		this.gestor = gestor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			gestor.arrancarFabrica(tipo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}