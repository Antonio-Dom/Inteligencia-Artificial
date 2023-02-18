package IA;

public class Arbol
	{
	
	Nodo raiz;

	public Arbol () 
	{
	
		raiz=null;
	
	}
	
//Metodo vacio(): boolean
	public boolean vacio() 
	{
		return raiz==null;
	
	}

//Metodo de Insertar()
	
public void Insertar(String nom) 
{
	Nodo n = new Nodo (nom);
        if(raiz==null) 
        {
            raiz=n;
        }else {
            Nodo aux = raiz;
            Nodo padre;
        while (true) 
        {
        	padre = aux;
            aux = aux.nodoizq;
            	if(aux==null) 
            	{
                    padre.nodoizq = n;
                    return;
                }
            }
        }
}

//Metodo para Imprimir en PreOrden
public void Imprimir(Nodo raiz) {
	if(raiz!=null) 
	{
		System.out.println(raiz);
		Imprimir(raiz.getNodoizq());
		Imprimir(raiz.getNododer());	
		
	}
}

//Imprimir raiz
public Nodo getRaiz() 
{
	return raiz;
}

//Metodo para buscar nodos
public Nodo buscarNodo(String nom) 
{
	return buscarNodo(raiz,nom);
	
}
private Nodo buscarNodo(Nodo n,String nom) 
{
	
	if(n == null) 
	{
		return null;
	}
	if(n.getNombre().equals(nom)) 
	{
		return n;
		
	}else if (nom.compareTo(n.getNombre())<0){
		return buscarNodo(n.getNodoizq(),nom);
		
	}else if (nom.compareTo(n.getNombre())>0){
		return buscarNodo(n.getNodoizq(),nom);
		
	}else{
		return buscarNodo(n.getNododer(),nom);
	}
}

}