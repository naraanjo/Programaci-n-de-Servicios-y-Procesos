package Ficha4;

// Clientes que acceden a las cajas
class Cliente implements Runnable{
	
	// Numero de cada cliente (Id)
	private int idCliente;
	// Recurso compartido
	private GestorCajas gestorCajas;
	
	// Constructor
	public Cliente(int idCliente, GestorCajas gestorCajas) {
		this.idCliente = idCliente;
		this.gestorCajas = gestorCajas;
	}

	@Override
	public void run() {
		
		try {
			gestorCajas.accederCaja(idCliente);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
	