package inicioHilos;

import java.util.Random;

public class SuperMarket {

    public static void main(String[] args) {
        int numeroCajas = 5;
        int numeroClientes = 20;

        GestorCajas gestor = new GestorCajas(numeroCajas);

        // Crear clientes
        for (int i = 0; i < numeroClientes; i++) {
            Thread cliente = new Thread(new Cliente3(gestor, "Cliente-" + i));
            cliente.start();

            // Llegada aleatoria de clientes
            try {
                Thread.sleep(new Random().nextInt(200) + 100); // 0.1 a 0.3 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Clase que gestiona las cajas
class GestorCajas {
    private final boolean[] cajasLibres;

    public GestorCajas(int numeroCajas) {
        cajasLibres = new boolean[numeroCajas];
        for (int i = 0; i < numeroCajas; i++) {
            cajasLibres[i] = true; // todas libres al inicio
        }
    }

    // Método para que un cliente use una caja
    public synchronized int ocuparCaja(String nombreCliente) {
        while (true) {
            for (int i = 0; i < cajasLibres.length; i++) {
                if (cajasLibres[i]) {
                    cajasLibres[i] = false; // ocupar caja
                    System.out.println(nombreCliente + " entra en la caja " + i);
                    imprimirEstadoCajas();
                    notifyAll(); // avisar a otros clientes
                    return i;
                }
            }
            // Si no hay cajas libres, esperar
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Liberar la caja
    public synchronized void liberarCaja(int i, String nombreCliente) {
        cajasLibres[i] = true;
        System.out.println(nombreCliente + " termina compra en caja " + i);
        imprimirEstadoCajas();
        notifyAll();
    }

    // Método para imprimir el estado de las cajas y número de libres
    private void imprimirEstadoCajas() {
        int libres = 0;
        StringBuilder estado = new StringBuilder("Estado cajas: [");
        for (int i = 0; i < cajasLibres.length; i++) {
            if (cajasLibres[i]) {
                estado.append("Libre");
                libres++;
            } else {
                estado.append("Ocupada");
            }
            if (i < cajasLibres.length - 1) {
                estado.append(", ");
            }
        }
        estado.append("] - Cajas libres: ").append(libres);
        System.out.println(estado.toString());
    }
}

// Cliente
class Cliente3 implements Runnable {
    private final GestorCajas gestor;
    private final String nombre;

    public Cliente3(GestorCajas gestor, String nombre) {
        this.gestor = gestor;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        int caja = gestor.ocuparCaja(nombre);

        // Simular tiempo de compra
        try {
            System.out.println(nombre + " realizando compra en caja " + caja);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gestor.liberarCaja(caja, nombre);
    }
}
