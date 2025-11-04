package ejerciciosEjemplo;

public class SumadorL {
	public static void main(String[] args){
        int resultado=0;
        int n1=Integer.parseInt(args[0]);
        int n2=Integer.parseInt(args[1]);
        
        for (int i=n1;i<=n2;i++){
                resultado=resultado+i;
        }
        System.out.println("resultado: " + resultado);
	}
}
