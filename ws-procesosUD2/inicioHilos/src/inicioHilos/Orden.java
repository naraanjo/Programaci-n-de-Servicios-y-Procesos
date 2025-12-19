package inicioHilos;

class Barrera {
    private int cont = 0;
    private final int LIMITE = 5;

    public synchronized void esperarBarrera(int id) throws InterruptedException {
        cont++;
        System.out.println("Corredor " + id + " llega a la barrera. (" + cont + "/" + LIMITE + ")");

        while (cont < LIMITE) {
            wait();
        }

        System.out.println("¡Se alcanza el límite! Corredor " + id + " pasa la barrera.");

        cont--; // Permitimos que otros hilos puedan usar la barrera
        notifyAll();
    }
}

class Corredor22 implements Runnable {
    private int id;
    private Barrera barrera;

    public Corredor22(int id, Barrera barrera) {
        this.id = id;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        try {
            barrera.esperarBarrera(id);
            System.out.println("Corredor " + id + " sigue corriendo.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Orden {
    public static void main(String[] args) {
        Barrera barrera = new Barrera();
        int totalCorredores = 15;

        for (int i = 1; i <= totalCorredores; i++) {
            new Thread(new Corredor22(i, barrera)).start();
        }
    }
}
