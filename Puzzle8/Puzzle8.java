package Puzzle8;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Puzzle8 {
	
	public static String estadoRapido = "1524 3786";
	public static String estadoMedio  = "1 5723486";
	public static String estadoLento  = "12578 346";
	
    public static String estadoFinal = "12345678 ";
    
    public static void main(String[] args) {
        
        String Facil = "Rapido:   "+estadoRapido, Medio = "Medio:     "+estadoMedio;
        String Dificil= "Lento:      "+estadoLento, tab ="         ";
    	
    	ArbolBusqueda arbol;
    	long inicio, fin;
    	double tiempoTotalAnchura, tiempoTotalProfundidad, tiempoTotalheuristica1, tiempoTotalheuristica2;

    	String [] estados = { estadoRapido, estadoMedio, estadoLento };
    	String [] NombreEstados = { Facil, Medio, Dificil };
    	
    	String [] cabezera = {"Estados Iniciales ","Anchura","Profundida","Heuristica 1","Heuristica 2"};
        String[][] datos = new String[5][4] ;
        int i = -1;

        for (String estado : estados) { i++;
    	
	    	arbol = new ArbolBusqueda(new Nodo(estado), estadoFinal);
	    
	        inicio = System.nanoTime();
	        arbol.busquedaPorAnchura();
	        fin = System.nanoTime();
	        tiempoTotalAnchura = (double) (fin - inicio) / 1000000000;
	        
			inicio = System.nanoTime();
			arbol.busquedaPorProfundidad();
	        fin = System.nanoTime();
	        tiempoTotalProfundidad = (double) (fin - inicio) / 1000000000;
	        
	        inicio = System.nanoTime();
	        arbol.busquedaPorLaHeuristica(1);
	        fin = System.nanoTime();
	        tiempoTotalheuristica1 = (double) (fin - inicio) / 1000000000;
	        
	        inicio = System.nanoTime();
	        arbol.busquedaPorLaHeuristica(2);
	        fin = System.nanoTime();
	        tiempoTotalheuristica2 = (double) (fin - inicio) / 1000000000;
	        
	        String [] fila = { NombreEstados[i], tab +Double. toString(tiempoTotalAnchura),
	        		tab + Double.toString(tiempoTotalProfundidad) , tab +Double.toString(tiempoTotalheuristica1),
	        		tab + Double.toString(tiempoTotalheuristica2) };
	        
	        datos[i] = fila;
    
	    }
        
        muestraTabla(datos,cabezera);
	      
	}
    
    public static void muestraTabla(String[][] datos, String []cabezera) {

	    JFrame ventana = new JFrame();
	    DefaultTableModel mod = new DefaultTableModel(datos,cabezera);
	    JTable tabla= new JTable(mod);
	    JLabel label;
	    JScrollPane scroll = new JScrollPane(tabla);
	    scroll.setBounds(40,40,620,71);
	    
	    label = new JLabel("(Tiempo en segundos)");
	    label.setBounds(40,8,160,30);
	    
	    ventana.setLayout(null);
	    ventana.setSize(720,200);
	    ventana.setTitle("Tabla comparativa");
	    ventana.setLocationRelativeTo(null);
	    ventana.add(label);
	    ventana.add(scroll);
	    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ventana.setVisible(true);
    	
    }
    
}