package areasTrianguloEntregable2;

/**
 * Clase Monitor para el contador.
 */
public class Contador {
    private int valor = 0;

    /**
     * Incrementa el valor del contador de forma segura (sincronizada).
     */
    public synchronized void incrementar() {
        valor++;
    }

    /**
     * Devuelve el valor actual del contador.
     */
    public int getValor() {
        return valor;
    }
}