package inicioHilos;

import java.util.ArrayList;
import java.util.Random;

public class AreasParalelo {

	public static void main(String[] args) {

		// Creo el recurso compartido
		ContadorCompartidoOperaciones contadorCompartido = new ContadorCompartidoOperaciones();

		// Lista donde guardare los triangulos (hilos)
		ArrayList<Thread> listaTriangulos = new ArrayList<Thread>();

		// Creo hilos
		for (int i = 0; i < 999; i++) {
			Thread triangulo = new Thread(new Triangulo(contadorCompartido), "Triangulo- " + i);
			listaTriangulos.add(triangulo);
		}

		// Recorro los hilos
		for (Thread thread : listaTriangulos) {
			thread.start();
		}

		// Indico al programa que no finalice hasta que terminen los hilos
		for (Thread thread : listaTriangulos) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Contador de operaciones: " + contadorCompartido.contadorDeOperaciones);
	}
}

// Clase compartida
// Caeda vez que se haga base x altura --> contador++
class ContadorCompartidoOperaciones {

	int contadorDeOperaciones = 0;

	public void sumarOpearacionContador(int base, int altura, Triangulo triangulo, String nombreParalelo) {

		try {

			synchronized (this) {
				System.out.println("PARALELO: --> " + nombreParalelo);
				triangulo.realizarCalculoArea(); // Operacion de uno en uno
				contadorDeOperaciones++;
			}

		} catch (Exception e) {

		}

	}

}

class Triangulo implements Runnable {
	Random aleator = new Random();

	int base = aleator.nextInt(5 - 2 + 1) + 2;
	int altura = aleator.nextInt(10 - 3 + 1) + 3;
	private ContadorCompartidoOperaciones contadorCompartido;

	public Triangulo(ContadorCompartidoOperaciones contadorCompartido) {
		this.contadorCompartido = contadorCompartido;
	}

	public void realizarCalculoArea() {

		int resultado = base * altura;

		System.out.println("Area del paralelo: " + resultado);
	}

	@Override
	public void run() {
		// Obtenemos el nombre del hilo
		String nombre = Thread.currentThread().getName();
		contadorCompartido.sumarOpearacionContador(base, altura, this, nombre);
	}
}