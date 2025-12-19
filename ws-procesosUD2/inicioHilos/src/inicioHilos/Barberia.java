package inicioHilos;

import java.util.Random;

public class Barberia {
    // Número de sillas y barberos
    public static int numeroSillas = 12;
    public static int numeroBarberos = 2;

    public static void main(String[] args) {

        // Recurso compartido
        GestorSillas gestor = new GestorSillas(numeroSillas);

        // Crear barberos
        for (int i = 0; i < numeroBarberos; i++) {
            Thread barbero = new Thread(new Barbero(gestor, "Barbero-" + i));
            barbero.start();
        }

        // Crear clientes continuamente
        for (int i = 0; i < 100; i++) {
            Thread cliente = new Thread(new Cliente(gestor, "Cliente-" + i));
            cliente.start();

            // Llegada aleatoria de clientes
            try {
                Thread.sleep(new Random().nextInt(500) + 500); // 0.5 a 1 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Clase que gestiona las sillas y el estado de los clientes
class GestorSillas {
    private final boolean[] sillasLibres;        // true = silla libre, false = ocupada
    private final boolean[] clienteAtendido;     // true = cliente está siendo atendido
    private int siguienteCliente = 0;            // índice circular para atender clientes

    public GestorSillas(int numSillas) {
        sillasLibres = new boolean[numSillas];
        clienteAtendido = new boolean[numSillas];
        
        
        for (int i = 0; i < numSillas; i++) {
            sillasLibres[i] = true;
            clienteAtendido[i] = false;
        }
    }

    // Método que usa un cliente para sentarse
    public synchronized int sentarse(String nombreCliente) {
    	
    	
        for (int i = 0; i < sillasLibres.length; i++) {
            if (sillasLibres[i]) {
                sillasLibres[i] = false;
                clienteAtendido[i] = false;
                System.out.println(nombreCliente + " se sienta en la silla " + i);
                notifyAll(); // Despierta a barberos dormidos
                return i;
            }
        }
        
        System.out.println(nombreCliente + " no encontró silla libre y se va");
        return -1; // No hay silla libre
    }

    // Método que usa el barbero para atender clientes
    public synchronized int atenderSiguienteCliente(String nombreBarbero) throws InterruptedException {
    	
        while (true) {
        	
            for (int i = 0; i < sillasLibres.length; i++) {
                // Atención circular: empieza desde "siguienteCliente"
                int idx = (siguienteCliente + i) % sillasLibres.length;

                // Si la silla está ocupada y el cliente no ha sido atendido
                if (!sillasLibres[idx] && !clienteAtendido[idx]) {
                    clienteAtendido[idx] = true;               // Marcamos al cliente como atendido
                    siguienteCliente = (idx + 1) % sillasLibres.length; // Avanzamos el índice circular
                    System.out.println(nombreBarbero + " atiende al cliente en silla " + idx);
                    return idx;
                }
            }
            // Si no hay clientes, barbero duerme hasta que llegue alguno
            System.out.println(nombreBarbero + " no tiene clientes y duerme");
            wait();
        }
    }

    // Liberar la silla después de atender
    public synchronized void liberarSilla(int i, String nombreBarbero) {
        sillasLibres[i] = true;
        clienteAtendido[i] = false;
        System.out.println(nombreBarbero + " termina de atender y libera la silla " + i);
    }
}

// Barbero
class Barbero implements Runnable {
    private final GestorSillas gestor;
    private final String nombre;
    private final Random random = new Random();

    public Barbero(GestorSillas gestor, String nombre) {
        this.gestor = gestor;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int silla = gestor.atenderSiguienteCliente(nombre);
                // Tiempo de afeitado aleatorio: 1-3 segundos
                Thread.sleep(1000 + random.nextInt(2000));
                gestor.liberarSilla(silla, nombre);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Cliente
class Cliente implements Runnable {
    private final GestorSillas gestor;
    private final String nombre;
    private final Random random = new Random();

    public Cliente(GestorSillas gestor, String nombre) {
        this.gestor = gestor;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        int silla = gestor.sentarse(nombre);
        if (silla != -1) {
            // Espera simulando el tiempo sentado antes de ser atendido
            try {
                Thread.sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
