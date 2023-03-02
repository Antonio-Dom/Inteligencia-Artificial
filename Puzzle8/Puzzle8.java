package Puzzle8;

import java.util.Collection;
import java.util.Comparator;

public class Puzzle8 {
	//Busqueda facil
	public static String estadoInicial = "41275386 ";
	//Busqueda dificil
   //public static String estadoInicial = "2465 7813";

    public static String estadoFinal = "12345678 ";
    
    public static void main(String[] args) {
    	
        ArbolBusqueda a = new ArbolBusqueda(new Nodo(estadoInicial), estadoFinal);
   
        long inicio1 = System.nanoTime();
        a.busquedaPorLaHeuristica(1);
        long fin1 = System.nanoTime();
        long tiempoTotalheuristica1 = fin1 - inicio1;
        System.out.println("Fin heurística 1 \n");
        long inicio2 = System.nanoTime();
        a.busquedaPorLaHeuristica(2);
        long fin2 = System.nanoTime();
        long tiempoTotalheuristica2 = fin2 - inicio2;
        System.out.println("Fin heurística 2 \n");
       // long inicio3 = System.nanoTime();
       // a.busquedaPorLaHeuristica(3);
       // long fin3 = System.nanoTime();
       // long tiempoTotalheuristica3 = ( fin3 - inicio3) / 1000000000;
        System.out.println("Fin heurística 3 \n");
        System.out.println("Tiempo por la heuristica 1: "+ tiempoTotalheuristica1);
        System.out.println("Tiempo por la heuristica 2: "+ tiempoTotalheuristica2);
        //System.out.println("Tiempo por la heuristica 3:  \n"+ tiempoTotalheuristica3);
        
        
       
    }
}