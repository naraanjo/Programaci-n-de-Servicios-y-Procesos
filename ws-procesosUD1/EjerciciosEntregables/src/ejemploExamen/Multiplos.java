package ejemploExamen;



public class Multiplos {
	
	
	public static void main(String[] args) throws Exception {
		

		if(args.length!= 1) {
			throw new Exception("ERROR EN EL NUMERO DE ARGUMENTOS" );
		}
		
		int numeroRecibido = Integer.parseInt(args[0]);
		
		
		// Escribo al PP
			
			if(numeroRecibido % 5 == 0) {
				System.out.println("si" );
			}else {
				System.out.println("no");
			}
			
		}
	}

