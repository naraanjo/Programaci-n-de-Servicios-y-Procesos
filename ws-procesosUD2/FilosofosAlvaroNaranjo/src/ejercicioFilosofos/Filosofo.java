package ejercicioFilosofos;

import java.util.Random;

public class Filosofo implements Runnable {
    
    private GestorPalillos gestorPalillos;
    private int posPalilloIzq;
    private int posPalilloDer;
    private int id; // Necesito mi ID para saber si soy par o impar.

    public Filosofo(GestorPalillos g, int pIzq, int pDer, int id) {
        this.gestorPalillos = g;
        this.posPalilloIzq = pIzq;
        this.posPalilloDer = pDer;
        this.id = id;
    }

    public void run() {
        // Entro en mi bucle infinito de vida: pensar y comer.
        while (true) {
            try {
                pensar();
                // Aquí es donde evito el interbloqueo
                cogerPalillos(); 
                comer();
                soltarPalillos();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Implementación de la SOLUCIÓN ASIMÉTRICA para evitar interbloqueos (Deadlock).
     */
    private void cogerPalillos() {
        System.out.println("Filósofo " + id + " tiene hambre y quiere comer.");
        
        // Si soy un filósofo IMPAR (1, 3...):
        if (id % 2 != 0) {
            // Intento coger primero mi IZQUIERDA, luego mi DERECHA.
            gestorPalillos.cogerPalillo(posPalilloIzq);
            gestorPalillos.cogerPalillo(posPalilloDer);
        } 
        // filósofo PAR (0, 2, 4...):
        else {
            // Intento coger primero mi DERECHA, luego mi IZQUIERDA.
            // Al invertir el orden, rompo el ciclo de espera circular.
            gestorPalillos.cogerPalillo(posPalilloDer);
            gestorPalillos.cogerPalillo(posPalilloIzq);
        }
        
        // Si llego aquí, es que tengo los dos palillos.
        System.out.println("Filósofo " + id + " ha conseguido sus dos palillos.");
    }

    private void soltarPalillos() {
        // Suelto ambos palillos para que otros puedan usarlos.
        gestorPalillos.soltarPalillo(posPalilloIzq);
        gestorPalillos.soltarPalillo(posPalilloDer);
        System.out.println("Filósofo " + id + " ha terminado, suelta los palillos.");
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        esperarTiempoAzar();
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está COMIENDO arroz.");
        esperarTiempoAzar();
    }

    private void esperarTiempoAzar() throws InterruptedException {
        Random generador = new Random();
        // Genero una espera aleatoria entre 0 y 2 segundos aprox.
        int msAzar = generador.nextInt(2000); 
        Thread.sleep(msAzar);
    }
}