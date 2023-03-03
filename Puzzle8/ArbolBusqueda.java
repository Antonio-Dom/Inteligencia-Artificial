
package Puzzle8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class ArbolBusqueda {
    
    Nodo raiz;
    String objetivo;
    
    public ArbolBusqueda(Nodo raiz, String objetivo)
    {
        this.raiz = raiz;
        this.objetivo = objetivo;
    }
    
    //Busqueda por Anchura
    public void busquedaPorAnchura()
    {
    	
    	
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList();
        Queue<Nodo> estadosPorVisitar = new LinkedList();
        while(!nodoActual.getEstado().equals(objetivo))
        {
            estadosVisitados.add(nodoActual.getEstado());
            //Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos();	
            for (String hijo : hijos) {
                if(!estadosVisitados.contains(hijo))
                {
                    
                    //Crear nuevo Nodo.
                    Nodo nHijo = new Nodo(hijo);
                    nHijo.setPadre(nodoActual);
                    estadosPorVisitar.add(nHijo);
                }
            }
            nodoActual = estadosPorVisitar.poll();
        }
        
        System.out.println(nodoActual.imprimir(nodoActual));
    }
    
    public void busquedaPorProfundidad()
    {
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList();
        Stack<Nodo> estadosPorVisitar = new Stack<Nodo>();
        while(!nodoActual.getEstado().equals(objetivo))
        {
            estadosVisitados.add(nodoActual.getEstado());
            //Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos();	
            for (String hijo : hijos) {
                if(!estadosVisitados.contains(hijo))
                {
                    //System.out.println("---Metiendo nuevo Nodo");
                    //Crear nuevo Nodo.
                    Nodo nHijo = new Nodo(hijo);
                    nHijo.setPadre(nodoActual);
                    estadosPorVisitar.add(nHijo);
                }
            }
            nodoActual = estadosPorVisitar.pop();
        }
        System.out.println(nodoActual.imprimir(nodoActual));
    }
  
    
    
    public void busquedaPorLaHeuristica(int Heuristica)
    {
    	Comparator<Nodo> heuristica = null;
    	switch (Heuristica) {
	    	case 1:
	    		heuristica = heuristica1();
	    		break;
	    	case 2:
	    		heuristica = heuristica2();
	    		break;
	    	case 3:
	    		heuristica = heuristica3();
	    		break;
    	}
        Nodo nodoActual = raiz;
        Collection<String> estadosVisitados = new ArrayList();
        PriorityQueue<Nodo> estadosPorVisitar = new PriorityQueue<Nodo>(heuristica);
        while(!nodoActual.getEstado().equals(objetivo))
        {
            estadosVisitados.add(nodoActual.getEstado());
            //Generar a los Nodos Hijos
            Collection<String> hijos = nodoActual.generaHijos();	
            for (String hijo : hijos) {
                if(!estadosVisitados.contains(hijo))
                {
                    
                    //Crear nuevo Nodo.
                    Nodo nHijo = new Nodo(hijo);
                    nHijo.setPadre(nodoActual);
                    estadosPorVisitar.add(nHijo);
                }
            }
            nodoActual = estadosPorVisitar.poll();
        }
        
        System.out.println(nodoActual.imprimir(nodoActual));
    }
    
    
    public Comparator<Nodo> heuristica1() {
        Comparator<Nodo> compara = new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
            	return Heuristica1(raiz.getEstado());
            }
        };
        return compara;
    }
    //Heuristica 1
    public int Heuristica1(String estado) {
		
		int heuristica = 0;
		
		for(int a = 0; a < estado.length(); a++) {
			if(estado.charAt(a) != objetivo.charAt(a)) {
				heuristica++;
			}
		}
		return heuristica;
	}
    
    public Comparator<Nodo> heuristica2() {
        Comparator<Nodo> compara = new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
            	return Heuristica2(raiz.getEstado());
            }
        };
        return compara;
    }
    
    //Heristica 2
	private int Heuristica2(String estado) {
		
		int h = 0;
		int posicion1 = 0; 
		int posicion2 = 0;
		int valor = 0;
		
		for(int i = 0; i < estado.length(); i++) {
			valor= 0;
			posicion1 = (int)estado.charAt(i);
			posicion2 = (int)objetivo.charAt(i);
			valor = posicion1 - posicion2;
			valor = Math.abs(valor);
			h = valor + h;
		}
		return h;
	}

    
    public Comparator<Nodo> heuristica3() {
        Comparator<Nodo> compara = new Comparator<Nodo>() {
            @Override
            public int compare(Nodo n1, Nodo n2) {
            	return Heuristica3(raiz.getEstado());
            }
        };
        return compara;
    }

	public int Heuristica3(String estado) {
			
		int contador = 0 ;
    	for(int i = 0; i < objetivo.length(); i++) {
    		
    		if(estado.charAt(i) != objetivo.charAt(i) ) {
    			contador ++;
    		}
    	}
    	return contador;
		
	
	}
    
   
    
   
    
    
    
}
