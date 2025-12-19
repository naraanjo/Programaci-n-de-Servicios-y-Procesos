package Ficha4;

import java.util.ArrayList;

public class Ficha4 {

	public final static int numeroCajas = 3;
	public final static int numeroClientes = 10;
	
	public static void main(String args[]) throws InterruptedException {
		
		// Creacion del recurso compartido
		GestorCajas gestorCaja = new GestorCajas(numeroCajas);
		
		// Lista para almacenar los hilos
		ArrayList<Thread> listaHilos = new ArrayList<Thread>();
		
		// Creacion de los clientes
		for(int i = 1; i <= numeroClientes; i++) {
			Thread cliente = new Thread(new Cliente(i, gestorCaja));
			cliente.start(); // Arranco los hilos
			listaHilos.add(cliente);
		}
		
		// No termine el programa hasta que terminen los hilos
		for(int i = 0; i < numeroClientes; i++) {
			listaHilos.get(i).join();
		}
		
		// Mensaje que se deberia de monstrar al final
		System.out.println("Cajas manejadas correctamente");
	}
}


	
