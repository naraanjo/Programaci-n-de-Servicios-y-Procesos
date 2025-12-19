import java.util.concurrent.Semaphore;

// ================= MONITOR DE LA BARBERÍA =================
class Barberia {

    // --------- RECURSOS ---------

    // Cuántos clientes pueden cortarse el pelo a la vez
    private Semaphore sillasDeCorte = new Semaphore(3);

    // Cuántos clientes pueden esperar sentados
    private Semaphore sillasDeEspera = new Semaphore(4);

    // Número de clientes que están esperando a un barbero
    // Empieza en 0 porque al inicio no hay clientes
    private Semaphore clientesEsperando = new Semaphore(0);

    // Número de barberos listos para atender
    // Empieza en 0 porque los barberos duermen
    private Semaphore barberosDisponibles = new Semaphore(0);

    // ================= MÉTODO DEL CLIENTE =================
    public void llegaCliente(int idCliente) throws InterruptedException {

        // 1️⃣ Intenta sentarse directamente para cortar el pelo
        if (sillasDeCorte.tryAcquire()) {

            System.out.println("Cliente " + idCliente + " se sienta DIRECTAMENTE a cortar");

            // Avisa de que hay un cliente esperando
            clientesEsperando.release();

            // Espera a que un barbero le llame
            barberosDisponibles.acquire();

            cortarPelo(idCliente);

            // Libera la silla de corte
            sillasDeCorte.release();
            return;
        }

        // 2️⃣ Si no hay silla de corte, intenta esperar
        if (sillasDeEspera.tryAcquire()) {

            System.out.println("Cliente " + idCliente + " se sienta en la SALA DE ESPERA");

            // Avisa de que hay un cliente esperando
            clientesEsperando.release();

            // Espera a que un barbero le llame
            barberosDisponibles.acquire();

            // Cuando lo llaman, deja la silla de espera
            sillasDeEspera.release();

            // Y pasa a una silla de corte
            sillasDeCorte.acquire();

            cortarPelo(idCliente);

            // Libera la silla de corte
            sillasDeCorte.release();
            return;
        }

        // 3️⃣ Si no hay sitio ni para cortar ni para esperar
        System.out.println("Cliente " + idCliente + " se va (barbería llena)");
    }

    // ================= MÉTODO DEL BARBERO =================
    public void trabajaBarbero(int idBarbero) throws InterruptedException {

        while (true) {

            // El barbero duerme hasta que haya un cliente
            clientesEsperando.acquire();

            System.out.println("Barbero " + idBarbero + " llama a un cliente");

            // Da permiso a UN cliente para pasar
            barberosDisponibles.release();

            // Simula el tiempo del corte
            Thread.sleep(1000);
        }
    }

    // ================= CORTE DE PELO =================
    private void cortarPelo(int idCliente) throws InterruptedException {
        System.out.println("Cliente " + idCliente + " cortándose el pelo");
        Thread.sleep(1000);
        System.out.println("Cliente " + idCliente + " termina y se va");
    }
}
