package dataframe;

import java.time.LocalDate;
import java.util.List;


public class TestDataFrame {


	public static void main(String[] args) {
		DataFrame d = DataFrame.parse("src/dataframe/personas.csv",List.of("Id","Nombre","Apellidos","Altura","Fecha_Nacimiento"));
		System.out.println(d);
		System.out.println("\nObteniendo la celda para la columna Nombre, para la fila cuyo valor Id es 73");
		System.out.println(d.cell("73", "Id", "Nombre")); 
		System.out.println("\nFiltrando filas con fecha posterior al 1/1/1998");
		System.out.println(d.filter(lista -> DataFrame.parse(lista.get(4), LocalDate.class).isAfter(LocalDate.of(1998, 1, 1)))); 
		System.out.println("\nCreando una columna con el email");
		System.out.println(d.addCalculatedColum("Email", lista -> lista.get(1)+lista.get(2)+"@email.com"));
		System.out.println("\nNombre de las columnas");
		System.out.println(d.columNames());
		System.out.println("\nNúmero de columnas");
		System.out.println(d.columNumber());
		System.out.println("\nColumna de Apellidos completa por indice y string");
		System.out.println(d.colum(2));
		System.out.println(d.colum("Apellidos"));
		System.out.println(d.colum(2, String.class));
		System.out.println(d.colum("Apellidos", String.class));
		System.out.println("\nComprobación de que no se repitan los Id");
		System.out.println(d.columAllDifferent("Id"));
		System.out.println("\nObteniendo la celda para la columna Nombre, para la fila 72");
		System.out.println(d.propertie(d.row(72), "Nombre"));
		System.out.println(d.cell(72, 1));
		System.out.println(d.cell(72, "Nombre"));
		System.out.println("\nNúmero de filas");
		System.out.println(d.rowNumber());
		System.out.println("\nObtener la fila número 3 mediante index e ID");
		System.out.println(d.row(3));
		System.out.println(d.row("4","Id"));
		System.out.println("\nTodas las filas");
		System.out.println(d.rows());
		System.out.println("\nHead");
		System.out.println(d.head());
		System.out.println(d.head(3));
		System.out.println("\nTail");
		System.out.println(d.tail());
		System.out.println(d.tail(3));
		System.out.println("\nSlice");
		System.out.println(d.slice(3, 5));
		System.out.println("\nOrdenar por orden alfabético sus apellidos (SortedBy)");
		System.out.println(d.sortBy(row -> row.get(d.columNames().indexOf("Apellidos")), false));
		System.out.println("\nAgrupar por fecha de nacimiento y calcular la altura promedio");
		System.out.println(d.groupBy(List.of("Fecha_Nacimiento"), "Altura_Promedio", 
			    (altura1, altura2) -> Double.toString((Double.parseDouble(altura1) + Double.parseDouble(altura2)) / 2), 
			    row -> row.get(d.columNames().indexOf("Altura"))));
		System.out.println("\nEliminar la columna altura");
		System.out.println(d.removeColum("Altura"));
		
		
		
	}

}
