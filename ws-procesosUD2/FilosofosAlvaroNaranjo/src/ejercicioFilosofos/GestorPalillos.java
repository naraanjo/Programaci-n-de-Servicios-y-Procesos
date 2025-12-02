package ejercicioFilosofos;

public class GestorPalillos {
    
    // Array que representa mis palillos. True = Libre, False = Ocupado.
    private boolean palilloLibre[];

    public GestorPalillos(int numPalillos) {
        // Inicializo mi array con el número de palillos que me digan.
        palilloLibre = new boolean[numPalillos];
        
        // Al principio, todos los aplillos libres
        for (int i = 0; i < numPalillos; i++) {
            palilloLibre[i] = true;
        }
    }

    /**
     * Método sincronizado para intentar coger un palillo específico.
     * Si está ocupado, hago esperar al hilo que me llama.
     */
    public synchronized void cogerPalillo(int pos) {
        // Mientras el palillo que piden NO esté libre...
        while (!palilloLibre[pos]) {
            try {
                // ... le digo al filósofo que espere (se bloquee) aquí.
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Si el bucle termina, es que el palillo está libre.
        // Lo marco como ocupado (false) y dejo que el filósofo se lo lleve.
        palilloLibre[pos] = false;
    }

    /**
     * Método para soltar un palillo.
     * Aviso a los que estén esperando 
     */
    public synchronized void soltarPalillo(int pos) {
        // Marco el palillo como libre.
        palilloLibre[pos] = true;
        
        // Despierto a todos los filósofos que estuvieran durmiendo en el wait()
        notifyAll();
    }
}