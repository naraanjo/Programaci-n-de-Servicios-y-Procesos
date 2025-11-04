package Procesos1.Procesos1;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// // Caso de que sea menor que cero -- NO hay proceso para ejecutar
    			if (args.length <= 0) {
    				System.err.println("Se necesita un programa a ejecutar");
    				System.exit(-1);
    			}
    			
    			ProcessBuilderClase pb = new ProcessBuilderClase(args);
    			try {
    				Process process = pb.start();
    				int retorno = process.waitFor(); // Devuelve cero si termino correctamente
    				
    				System.out.println("La ejecucion de " + Arrays.toString(args) + " devuelve " + retorno);
    			} catch (IOException ex) {
    				System.err.println("Excepción de E/S!!");
    				System.exit(-1); // Finaliza con error, diferente de cero
    			}catch (InterruptedException ex) {
    				System.err.println("El proceso hijo finalizó de forma incorrecta");
    				System.exit(-1); // Finaliza con error diferente de cero
    			}    }
}
