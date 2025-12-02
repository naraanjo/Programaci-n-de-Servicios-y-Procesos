package ejercicioFilosofos;

public class Lanzador {
    public static void main(String[] args) {
        
        int NUM_PROCESOS = 5; // Número de filósofos 
        
        
        Filosofo filosofos[] = new Filosofo[NUM_PROCESOS];
        Thread hilos[] = new Thread[NUM_PROCESOS];
        
        // Instancio el gestor (la mesa) con 5 palillos.
        GestorPalillos gestorPalillos = new GestorPalillos(NUM_PROCESOS);
        
        System.out.println("Mesa preparada. Iniciando cena...");

        // Bucle para crear los filósofos del 1 al 4.
        for (int i = 1; i < NUM_PROCESOS; i++) {
            // El filósofo 'i' usa el palillo 'i' (izq) y 'i-1' (der).
            // Le paso 'i' como ID.
            filosofos[i] = new Filosofo(gestorPalillos, i, i - 1, i);
            hilos[i] = new Thread(filosofos[i]);
            hilos[i].start();
        }

        // Caso especial: El filósofo 0.
        // Para cerrar el círculo, usa el palillo 0 y el palillo 4 (el último).
        filosofos[0] = new Filosofo(gestorPalillos, 0, 4, 0);
        hilos[0] = new Thread(filosofos[0]);
        hilos[0].start();
        
        // No hago join() porque es un bucle infinito, el programa seguirá corriendo
        // hasta que se detenga manualmente.
    }
}