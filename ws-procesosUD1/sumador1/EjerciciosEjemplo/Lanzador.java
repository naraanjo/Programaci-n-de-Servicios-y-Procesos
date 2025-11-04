package sumador1;

import java.io.*;

public class Lanzador {
    public void lanzarSumadorL(Integer n1, Integer n2, String fichResul){
            String clase1="SumadorL";
            ProcessBuilder pb;
            File path = new File("C:\\Users\\Alvaro\\workSpaces\\ws-ficheros\\sumador\\bin\\");
            //File path = new File(".\\bin\\");
            try {
            	pb = new ProcessBuilder("java",clase1, n1.toString(), n2.toString()).directory(path);
            	pb.redirectError(new File("errorLanzador.txt"));
            	pb.redirectOutput(new File(fichResul));
            	pb.start();
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            
    }
    public static void main(String[] args){
            Lanzador l=new Lanzador();
            l.lanzarSumadorL(1, 25, "res1.txt");
            l.lanzarSumadorL(26, 50, "res2.txt");
            l.lanzarSumadorL(51, 75, "res3.txt");
            l.lanzarSumadorL(76, 100, "res4.txt");
            System.out.println("Ok");
    }
}
