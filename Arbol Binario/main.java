package IA;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Jesus Antonio Dominguez Vargas
		Arbol ArbolBinario = new Arbol();
		//Inserciones
		System.out.println("Inserciones:\n");
		ArbolBinario.Insertar("Juan");
		ArbolBinario.Insertar("Jesus");
		ArbolBinario.Insertar("Jose");
		ArbolBinario.Insertar("Eduardo");
		ArbolBinario.Insertar("Lara");
		ArbolBinario.Insertar("Daniel");
		ArbolBinario.Imprimir(ArbolBinario.getRaiz());
		
		System.out.println("\nSe ha encontrado a "+ArbolBinario.buscarNodo("Jose"));
		
		
		
	}

}

