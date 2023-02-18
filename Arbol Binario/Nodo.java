package IA;

	public class Nodo 
	{
		String nombre;
		Nodo nodoizq;
		Nodo nododer;
		
	public Nodo(String nomb) 
	    {
			this.nombre= nomb;
			this.nododer=null;
			this.nodoizq= null;
			
		}
	public String getNombre() 
        {
			return nombre;
		}
	
	public Nodo getNodoizq() 
        {
			return nodoizq;
		}
	
	public Nodo getNododer() 
		{
			return nododer;
		}
	public void setNodoizq(Nodo nodoizq) 
		{
		this.nodoizq = nodoizq;
		}
	public void setNododer(Nodo nododer) 
		{
		this.nododer = nododer;
		}

	public String toString() 
		{
			return nombre + "";
		}
	}
