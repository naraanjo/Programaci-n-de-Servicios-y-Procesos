package AlCuadrado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PH1 {

	public static void main(String[] args) throws Exception {
		
		ArrayList<Integer> listaNumerosRecibidos = new ArrayList<Integer>();
		
		// Leo los datos enviados del PP
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String linea = "";
			
			while((linea = br.readLine()) != null) {
				int numeroRecibido = Integer.parseInt(linea);
				listaNumerosRecibidos.add(numeroRecibido); // Lista con todos los numero de PP
			}
		}
		
		// Recorro la lista
		// Haciendo los cuadrados
		for(int i = 0; i < listaNumerosRecibidos.size() ; i ++) {
			int cuadrado = listaNumerosRecibidos.get(i) * listaNumerosRecibidos.get(i);
			listaNumerosRecibidos.set(i, cuadrado);
		}
		
		// Lista actualiza con los cuadrados
		// Envio la salida al PP
		for (Integer num : listaNumerosRecibidos) {
			System.out.println(num.toString());
		}
		
	}
}
