package dataframe;

import java.util.ArrayList;
import java.util.List;


public class EntregaExamen2 {
	public static DataFrame emptyDataFrame(DataFrame df) {
	    return DataFrameImpl.of(df.columNames(), new ArrayList<>());
	}
	
	public static DataFrame addDataFrame(DataFrame df1, DataFrame df2) {
		List<String> newcolum = new ArrayList<>(df1.columNames());
	    newcolum.addAll(df2.columNames());
	    List<List<String>> newvalue = new ArrayList<>();
	    int numrows = Math.min(df1.rowNumber(), df2.rowNumber());
	    for (int i = 0; i < numrows; i++) {
	        List<String> newrow = new ArrayList<>(df1.rows().get(i));
	        newrow.addAll(df2.row(i));
	        newvalue.add(newrow);
	    }
	    return DataFrameImpl.of(newcolum, newvalue);
	}
	public static DataFrame removeColumnIndex(DataFrame df, int ci) {
		if (ci < 0 || ci >= df.columNumber()) {
            System.out.println("Índice no válido");
            return null; 
        }
	    List<String> newcolum = new ArrayList<>(df.columNames());
	    newcolum.remove(ci);
	    List<List<String>> newvalue = new ArrayList<>();
	    for (List<String> row : df.rows()) {
	        List<String> newrow = new ArrayList<>(row);
	        newrow.remove(ci);
	        newvalue.add(newrow);
	    }
	    return DataFrameImpl.of(newcolum, newvalue);
	    
	}
	public static List<DataFrame> divideDataFrame (DataFrame df, int ci){
		if (ci < 0 || ci >= df.columNumber()) {
            System.out.println("Índice no válido");
            return null; 
        }

	 List<String> df1columnames = df.columNames().subList(0, ci + 1);
	 List<String> df2columnames = df.columNames().subList(ci + 1, df.columNumber());
	 List<List<String>> df1values = new ArrayList<>();
	 List<List<String>> df2values = new ArrayList<>();
	 for (List<String> row : df.rows()) {
	    List<String> row1 = row.subList(0, ci + 1);
	    List<String> row2 = row.subList(ci + 1, df.columNumber());
	    df1values.add(row1);
	    df2values.add(row2);
	    }
	 DataFrame df1 = DataFrameImpl.of(df1columnames, df1values);
	 DataFrame df2 = DataFrameImpl.of(df2columnames, df2values);
	 List<DataFrame> resultado = new ArrayList<>();
     resultado.add(df1);
     resultado.add(df2);
     return resultado;
	}
	
	public static void main(String[] args) {
		DataFrame d = DataFrame.parse("src/dataframe/personas.csv",List.of("Id","Nombre","Apellidos","Altura","Fecha_Nacimiento"));
		DataFrame d2=d.sortBy(row -> row.get(d.columNames().indexOf("Apellidos")), false);
		int n=2;
		int m=8;
		int z=-2;
		System.out.println(d);
		System.out.println("DataFrame vacío pero manteniendo el nombre de las columnas:");
		System.out.println(emptyDataFrame(d));
		System.out.println("DataFrame sumado a otro DataFrame");
		System.out.println(addDataFrame(d,d2));
		System.out.println("DataFrame sin la columna de indice n");
		System.out.println(removeColumnIndex(d,n));
		System.out.println("DataFrame sin la columna de indice m (m fuera de rango)");
		System.out.println(removeColumnIndex(d,m));
		System.out.println("DataFrame sin la columna de indice z(z negativo)");
		System.out.println(removeColumnIndex(d,z));
		System.out.println("Lista de datframe divido en dos en n");
		System.out.println(divideDataFrame(d,n));
		System.out.println("Lista de datframe divido en dos en m(m fuera de rango)");
		System.out.println(divideDataFrame(d,m));
		System.out.println("Lista de datframe divido en dos en z(z negativo)");
		System.out.println(divideDataFrame(d,z));
		
	}

}
