package inicioHilos;

import java.util.concurrent.Semaphore;

public class EjercicioImpresora {

	public static void main(String[] args) {
		
		// Creamos el recurso compartido (impresora)
		Impresora impresora = new Impresora();
		
		// Creamos tres hilos empleados que intentaran usar la impresora
		Thread empleado1 = new Thread(new Empleado(impresora), "Empleado-1");
		Thread empleado2 = new Thread(new Empleado(impresora), "Empleado-2");
		Thread empleado3 = new Thread(new Empleado(impresora), "Empleado-3");

		// Inicamos los hilos
		empleado1.start();
		empleado2.start();
		empleado3.start();
	}
}
	
	
	// Clase impresora, a la que quieren acceder varias personas
	 class Impresora{
		
		// Creacion del semaforo, donde le indicamos
		// que solo puede acceder una persona a la vez (1)
		private Semaphore semaforo = new Semaphore(1);
		
		public void imprimirDocumento(String nombreEmpleado) {
			
			try {
				System.out.println(nombreEmpleado + " --> Intenta acceder a la impresora");
				
				// Si el semafor esta en (1), el empleado pasa y el semaforo pasa a (0)
				// Si el semaforo esta en (0), al hilo le toca esperar
				semaforo.acquire(); // Bloquea al hilo si no hay espacio
				
				// Inicio --> seccion critica
				System.out.println("--> " + nombreEmpleado + " obtuvo el permiso y esta imprimiendo");
				
				Thread.sleep(2000); // Simulamos que tarda dos segundos en imprimir
				
				// Fin
				System.out.println(nombreEmpleado + " Termino de imprimir y sale ");
				
				// Libera el recurso y le suma uno al semaforo
				semaforo.release();
				
			}catch (Exception e) {

			}
			
		}
	}
	
	// Clase que define la tarea del hilo (Runnable)
	class Empleado implements Runnable {
	    private Impresora impresora;

	    public Empleado(Impresora impresora) {
	        this.impresora = impresora;
	    }

	    @Override
	    public void run() {
	        // Obtenemos el nombre del hilo actual para saber quién es
	        String nombre = Thread.currentThread().getName();
	        impresora.imprimirDocumento(nombre);
	    }
	}


