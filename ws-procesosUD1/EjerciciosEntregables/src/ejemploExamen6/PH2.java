package ejemploExamen6;

import java.io.File;

public class PH2 {

	// Recibe la cantidad de lineas del fichero y ruta de un archivo
	// +10 linea - crea nomberArchivo_E
	// 10 lineas - nmobreArchivo_S
	
	public static void main(String[] args) throws Exception {
		
		if (args.length != 2 ) {
			throw new Exception("Numero de argumentos invalidos");
		}
	
		// Recogo los args
		int cantidadLinea = Integer.parseInt(args[0]);
		String rutaArchivo = args[1];
		
		// Logica del proceso-2
		if(cantidadLinea >= 10 ) {
			File archivoE = new File(rutaArchivo+"_E");
			archivoE.createNewFile();
			System.out.println("Creando archivo de mas de 10 lineas");
		}else {
			File archivoS = new File(rutaArchivo + "_S");
			archivoS.createNewFile();
			System.out.println("Creando archivo de menos de 10 lineas");
		}
		
		
	}
}
