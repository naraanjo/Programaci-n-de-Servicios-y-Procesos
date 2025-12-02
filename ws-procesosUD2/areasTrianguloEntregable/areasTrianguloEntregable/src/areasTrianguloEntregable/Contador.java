package areasTrianguloEntregable;

/**
 * Clase Monitor para el contador.
 * Asegura la integridad del contador en un entorno multi-hilo
 * mediante el uso de sincronización.
 */
public class Contador {
    // El contador que será compartido e incrementado.
    private int valor = 0;

    /**
     * Incrementa el valor del contador de forma segura (sincronizada).
     */
    public synchronized void incrementar() {
        // La sección crítica: solo un hilo puede ejecutar esto a la vez.
        valor++;
    }

    /**
     * Devuelve el valor actual del contador.
     */
    public int getValor() {
        return valor;
    }
}
