package v;

import java.util.concurrent.Semaphore;

public class Canibal {

    public static void main(String args[]) {

        GestorAlimento gestor = new GestorAlimento();

        // Crear 15 caníbales
        for (int i = 0; i < 15; i++) {
            Thread hilo = new Thread(new Individuo("Canibal-" + i, gestor));
            hilo.start();
        }

        // Crear 1 cocinero
        Thread hiloCocinero = new Thread(new Individuo("Cocinero", gestor));
        hiloCocinero.start();
    }
}

// ============================================
// Clase que gestiona las raciones y sincronización
class GestorAlimento {
    private Semaphore cocinero = new Semaphore(0);      // Señal para despertar al cocinero
    private Semaphore esperarCocina = new Semaphore(0); // Caníbales esperan aquí si no hay raciones
    private Semaphore mutex = new Semaphore(1);         // Mutex para acceso a raciones y contador

    private int raciones = 0;            // Raciones disponibles
    private int esperandoCanibales = 0;  // Caníbales esperando comida

    // Método que ejecutan todos los hilos (caníbales y cocinero)
    public void empezar(String tipo) throws InterruptedException {
        if (tipo.startsWith("Canibal")) {

            mutex.acquire(); // Protege acceso a raciones y contador

            if (raciones > 0) {
                // Comer directamente
                raciones--;
                System.out.println(tipo + " comiendo. Raciones restantes: " + raciones);
                mutex.release();
            } else {
                // No hay raciones, se incrementa contador de esperando y despierta al cocinero
                esperandoCanibales++;
                System.out.println(tipo + " espera. Caníbales esperando: " + esperandoCanibales);
                cocinero.release();   // Despierta cocinero si está dormido
                mutex.release();

                // Espera a que el cocinero cocine
                esperarCocina.acquire();

                // Una vez liberado por el cocinero, comer
                mutex.acquire();
                raciones--;
                esperandoCanibales--;
                System.out.println(tipo + " comiendo tras cocinar. Raciones restantes: " + raciones);
                mutex.release();
            }

        } else if (tipo.equals("Cocinero")) {

            while (true) {
                cocinero.acquire(); // Espera a ser despertado por algún caníbal

                mutex.acquire();
                if (raciones == 0 && esperandoCanibales > 0) {
                    int aCocinar = Math.min(10, esperandoCanibales); // Cocinar solo lo necesario
                    raciones += aCocinar;
                    System.out.println("Cocinero cocinando " + aCocinar + " raciones. Total raciones: " + raciones);
                    // Libera los permisos para que los caníbales puedan comer
                    esperarCocina.release(aCocinar);
                }
                mutex.release();
            }
        }
    }
}

// ============================================
// Clase que representa cada hilo (caníbal o cocinero)
class Individuo implements Runnable {
    private String tipo;
    private GestorAlimento gestor;

    public Individuo(String tipo, GestorAlimento gestor) {
        this.tipo = tipo;
        this.gestor = gestor;
    }

    @Override
    public void run() {
        try {
            gestor.empezar(tipo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
