package entrega1;

import java.util.ArrayList;
import java.util.List;

public class testfunciones {
	public static void main(String[] args) {
		//System.out.println(Funciones.primo(1)); //el numero debe ser mayor que 1
		System.out.println(Funciones.primo(2));
		System.out.println(Funciones.primo(10));
		
		//System.out.println(Funciones.factorial(-3)); //el numero debe ser natural
		System.out.println(Funciones.factorial(0));
		System.out.println(Funciones.factorial(4));
		
		//System.out.println(Funciones.combinatorio(4,6));  //n debe ser mayor o igual que k
		//System.out.println(Funciones.combinatorio(5,-1)); //el numero debe ser natural
		System.out.println(Funciones.combinatorio(3,2));
		System.out.println(Funciones.combinatorio(5,0));
		
		System.out.println(Funciones.stirling(10,8));
		System.out.println(Funciones.stirling(5,5)); 
		System.out.println(Funciones.stirling(5,0));
		//System.out.println(Funciones.stirling(5,7));  // n debe ser mayor o igual que k
		
		 List<Integer> lista_int = new ArrayList<>(List.of(1, 0, 2, 10, -3));
	     System.out.println(Funciones.diflista(lista_int));
	     //System.out.println(Funciones.diflista(null));  //Cannot invoke "java.util.List.size()" because "lista" is null
	     List<Integer> lista_int2 = new ArrayList<>(List.of(1));
	     //System.out.println(Funciones.diflista(lista_int2));  //La lista debe tener 2 números al menos
	     
	     List<String> lista_str = new ArrayList<>(List.of("Antonio", "Manolo", "José"));
	     System.out.println(Funciones.Maslarga(lista_str));
	     List<String> lista_str2 = new ArrayList<>(List.of("José", "Juan", "Raúl", "Teo"));
	     System.out.println(Funciones.Maslarga(lista_str2));
	     //System.out.println(Funciones.Maslarga(null)); //Cannot invoke "java.util.List.iterator()" because "listaCadenas" is null
	     
	     
	     //Defensa
	     //A
	     System.out.println(Funciones.P2(10, 4, 2)); //8
	     
	     //System.out.println(Funciones.P2(4, 3, 5)); //i debe ser menor que k+1
	     //B
	     System.out.println(Funciones.C2(5, 2));
	     //System.out.println(Funciones.C2(1, 2)); //n debe ser mayor que k
	     //System.out.println(Funciones.C2(5, -1)); // ambos numeros deben ser natural
	     //C
	     System.out.println(Funciones.S2(5, 2));
	     //System.out.println(Funciones.S2(5, 6));  //n debe ser mayor o igual que k
	}
}
	
