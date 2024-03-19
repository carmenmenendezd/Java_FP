package entrega1;

import java.util.ArrayList;
import java.util.List;

public class Funciones {
	public static String primo (Integer n) {
		if (n<=1) {
			throw new IllegalArgumentException("el numero debe ser mayor que 1");
		}
		for (Integer i=2; i<n; i++) {
			if (n % i==0) {
				return n +" es compuesto";
	            }
	        }
	        return n +" es primo";
	    }
	public static Integer factorial (Integer n) {
		if (n<0) {
			throw new IllegalArgumentException("el numero debe ser natural");
		}
		if (n==0) {
            return 1;
        }
        Integer factorial=1;
        for (Integer i=1; i<=n; i++) {
            factorial *=i;
        }
        return factorial;
    }
	
	public static Integer combinatorio (Integer n, Integer k) {
		if (n<k) {
            throw new IllegalArgumentException("n debe ser mayor o igual que k");
		}
		Integer combinatorio=factorial(n)/(factorial(k)*factorial(n-k));
        return combinatorio;
	}
	
	public static Integer stirling (Integer n, Integer k) {
		if (n<k) {
            throw new IllegalArgumentException("n debe ser mayor o igual que k");
		}
		if (k==0 || n==0) {
			return 0;
		} 
		else if (k==n) {
			return 1;
		} 
		else {
			return k*stirling(n-1,k)+ stirling(n-1, k-1);
		     }
	}
	
	 public static List<Integer> diflista(List<Integer> lista) {
		 if (lista.size()<2) {
	            throw new IllegalArgumentException("La lista debe tener 2 números al menos");
		 }
	     List<Integer> res = new ArrayList<>();
	     for (int i = 1; i < lista.size(); i++) {
	    	 int dif = lista.get(i) - lista.get(i - 1);
	         res.add(dif);
	        }
	     return res;
	 }
	 
	 public static String Maslarga(List<String> listaCadenas) {
		 String masLarga = "";
	     int longitudMayor = 0; 

	     for (String cadena : listaCadenas) {
	    	 if (cadena.length() >longitudMayor) {
	    		 masLarga = cadena;
	             longitudMayor = cadena.length();
	         } 
	    	 else if (cadena.length()==longitudMayor) {
	        	 masLarga += " y " + cadena;
	    	 }
	      }

	      return masLarga;
	  }
	    

}