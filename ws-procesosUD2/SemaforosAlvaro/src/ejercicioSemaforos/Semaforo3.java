package ejercicioSemaforos;

import java.util.concurrent.Semaphore;
/**
 * Clase que guarda nuestro recurso compartido: el acumulador.
 */
class Acumula {
    public static int acumulador = 0;
}

/**
 * Hilo sumador que incrementa el valor de la clase Acumula.
 */
class Sumador extends Thread {
	

    private int cuenta;
    private Semaphore sem;
    
    Sumador (int hasta, int id, Semaphore sem) {
        this.cuenta = hasta;
        this.sem = sem;
    }
    
    /**
     * Método que realiza la operación crítica (sumar).
     * * HE MODIFICADO este método. Antes no tenía protección interna.
     * Aunque el semáforo de abajo permita entrar a varios hilos a la vez,
     * aquí he implementado un MONITOR usando 'synchronized'.
     * Al sincronizar sobre 'Acumula.class', garantizo que, aunque el semáforo
     * deje pasar a 2 o más hilos a esta zona, SOLO UNO podrá ejecutar la instrucción
     * 'acumulador++' en un instante preciso. Esto es lo que garantiza el resultado exacto.
     */
    public void sumar() {
        synchronized (Acumula.class) {
            Acumula.acumulador++;
        }
    }
    
    public void run () {
        for (int i = 0; i < cuenta; i++) {
            try {
                // Solicito permiso al semáforo.
                // Como he configurado el semáforo con 5 permisos (ver main),
                // hasta 5 hilos podrán pasar de esta línea simultáneamente.
                sem.acquire();      
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Entro a sumar. Gracias al monitor interno de ese método,
            // la suma es segura (atómica).
            sumar();
            
            // Libero el permiso del semáforo para que entre otro hilo.
            sem.release();
        }
    }
}

public class Semaforo3 {
    private static Sumador sumadores[];
    
    // HE MODIFICADO la inicialización del semáforo.
    // Permitimos mayor concurrencia (hasta 5 hilos "trabajando" a la vez),
    // pero delegamos la seguridad del dato al monitor 'synchronized' dentro de la clase Sumador.
    private static Semaphore semaphore = new Semaphore(5);
    
    public static void main (String[] args) {
        // Si no pasas argumentos, asumo 10 hilos por defecto para que funcione al darle al Play.
        int n_sum = (args.length > 0) ? Integer.parseInt(args[0]) : 10;
        
        sumadores = new Sumador[n_sum];
        
        System.out.println("Iniciando " + n_sum + " hilos con un Semáforo de 5 permisos...");
        
        for (int i = 0; i < n_sum; i++) {
            
            sumadores[i] = new Sumador(1000000, i, semaphore);
            sumadores[i].start();
        }
        
        for (int i = 0; i < n_sum; i++) {
            try {
                sumadores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Cálculo del resultado esperado para verificar
        System.out.println("Acumulador final: " + Acumula.acumulador);
        System.out.println("Esperado (si son 10 hilos * 1000000 sumas): " + (n_sum * 1000000));
    }
}