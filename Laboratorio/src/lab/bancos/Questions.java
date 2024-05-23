package lab.bancos;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;

public class Questions {

	//	Vencimiento de los prestamos de un cliente
	public static Set<LocalDate> vencimientoDePrestamosDeCliente(Banco banco,String dni) {
		Set<Prestamo> prestamosCliente = banco.prestamosDeCliente(dni);
	    return prestamosCliente.stream()
	            .map(Prestamo::fechaVencimiento)
	            .collect(Collectors.toSet());
	}
	//	Persona con más prestamos
	public static Persona clienteConMasPrestamos(Banco banco) {
		return banco.prestamos().todos().stream()
                .collect(Collectors.groupingBy(Prestamo::dniCliente, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(dni -> banco.personas().personaDni(dni).orElse(null))
                .orElse(null);
	}
	//	Cantidad total de los crétditos gestionados por un empleado
	public static Double cantidadPrestamosPmpledado(Banco banco,String dni) {
		Set<Prestamo> prestamosEmpleado = banco.prestamosDeEmpleado(dni);
		return prestamosEmpleado.stream()
	            .mapToDouble(Prestamo::cantidad)
	            .sum();
	            
	}
	//	Empleado más longevo
	public static Persona empleadoMasLongevo(Banco banco) {
		return banco.empleadoMasJoven().orElse(null);
	}
	//	Interés mínimo, máximo y medio de los préstamos
	public static record Info(Double min, Double max, Double mean) {
		public String toString() {
			return String.format("(%.2f, %.2f, %.2f)",this.min(),this.max(),this.mean());
		}
	}
	public static  Info rangoDeIntereseDePrestamos(Banco banco) {
		double min = banco.prestamos().todos().stream().mapToDouble(Prestamo::interes).min().orElse(0);
        double max = banco.prestamos().todos().stream().mapToDouble(Prestamo::interes).max().orElse(0);
        double mean = banco.prestamos().todos().stream().mapToDouble(Prestamo::interes).average().orElse(0);
        return new Info(min, max, mean);
	}

	//	Número de préstamos por mes y año
	public static record Info2(Integer mes, Integer año) {
		public String toString() {
			return String.format("(%d,%d)",this.mes(),this.año());
		}
	}
	public static Map<Info2,Integer> numPrestamosPorMesAño(Banco banco) {
		return banco.prestamos().todos().stream()
                .collect(Collectors.groupingBy(prestamo -> new Info2(prestamo.fechaComienzo().getMonthValue(), prestamo.fechaComienzo().getYear()), Collectors.summingInt(p -> 1)));
	}
	
	public static void main(String[] args) {
		Banco banco = Banco.of();

       
        System.out.println("Vencimientos de préstamos para un cliente:");
        Set<LocalDate> vencimientos = Questions.vencimientoDePrestamosDeCliente(banco, "50000187G");
        System.out.println(vencimientos);

        System.out.println("Cliente con más préstamos:");
        Persona clienteMasPrestamos = Questions.clienteConMasPrestamos(banco);
        System.out.println(clienteMasPrestamos);

   
        System.out.println("Cantidad total de préstamos gestionados por un empleado:");
        Double cantidadPrestamos = Questions.cantidadPrestamosPmpledado(banco, "34759012D");
        System.out.println(cantidadPrestamos);

   
        System.out.println("Empleado más longevo:");
        Persona empleadoMasLongevo = Questions.empleadoMasLongevo(banco);
        System.out.println(empleadoMasLongevo);


        System.out.println("Rango de intereses de préstamos:");
        Questions.Info rangoIntereses = Questions.rangoDeIntereseDePrestamos(banco);
        System.out.println(rangoIntereses);

      
        System.out.println("Número de préstamos por mes y año:");
        Map<Questions.Info2, Integer> numPrestamosPorMesAno = Questions.numPrestamosPorMesAño(banco);
        System.out.println(numPrestamosPorMesAno);
    }
}
