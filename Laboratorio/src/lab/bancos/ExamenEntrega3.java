package lab.bancos;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;

public class ExamenEntrega3 {
	
	public static List<Empleado> empleadosNacidosDiaMes(Banco banco, String ini, String fin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        MonthDay fechaInicio = MonthDay.parse(ini, formatter);
        MonthDay fechaFin = MonthDay.parse(fin, formatter);
        if (fechaInicio.isAfter(fechaFin)) {
            System.out.println("ini tiene que ser antes que fin");
            return null;
        }
        String[] parts = ini.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        Set<Empleado> empleados = banco.empleados().todos();

        return empleados.stream()
                .filter(empleado -> {
                    LocalDate fechaNacimiento = empleado.persona().fechaDeNacimiento().toLocalDate();
                    MonthDay mesDiaNacimiento = MonthDay.of(fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
                    return !mesDiaNacimiento.isBefore(fechaInicio) && !mesDiaNacimiento.isAfter(fechaFin);
                })
                .collect(Collectors.toList());
    }
	    

	
	public static Map<Character, List <Empleado>> empleadosConLetraDNI(Banco banco, Set<Character> letras) {
		Set<Empleado> empleados = banco.empleados().todos();

        return empleados.stream()
                .filter(empleado -> letras.contains(empleado.dni().charAt(empleado.dni().length() - 1)))
                .collect(Collectors.groupingBy(
                        empleado ->empleado.dni().charAt(empleado.dni().length()-1) ));
	}
	public static Double clientesEdadMedia(Banco banco, Integer m) {
		if (m==null) {
			System.out.println("m no puede ser nula");
			return null;
		}
		if (m<0) {
			System.out.println("m no puede ser negativa");
			return null;
		}
        Predicate<Persona> edadMayorM = persona -> persona.edad() > m;
        
        Set<String> dniclientesMayorM = banco.cuentas().todas().stream()
                .flatMap(cuenta -> banco.personas().todos().stream()
                        .filter(persona ->persona.dni().equals(cuenta.dni) && edadMayorM.test(persona))
                        .map(Persona::dni))
                .collect(Collectors.toSet());

        if (dniclientesMayorM.isEmpty()) {
            return null;
        }

        double totalEdad = banco.personas().todos().stream()
                .filter(persona -> dniclientesMayorM.contains(persona.dni()))
                .mapToInt(Persona::edad)
                .sum();

        double cantidadPersonas = dniclientesMayorM.size();
        return totalEdad / cantidadPersonas;
    }
	public static Set<String> clientesConMenosPrestamos(Banco banco, int n) {
		if (n<0) {
	        System.out.println("n no puede ser negativa");
	        return null;
	    }
	    Map<String, Long> prestamosPorCliente = banco.prestamos().todos().stream()
	            .collect(Collectors.groupingBy(Prestamo::dniCliente, Collectors.counting()));

	    Set<String> clientesConPrestamos = prestamosPorCliente.keySet();

	    return prestamosPorCliente.entrySet().stream()
	            .sorted(Map.Entry.comparingByValue())
	            .limit(n)
	            .map(Map.Entry::getKey)
	            .filter(clientesConPrestamos::contains)
	            .collect(Collectors.toSet());
	}
	
	
	//////-------------------------TESTS------------------------------------
	public static void testempleadosentre (String ini, String fin){
		Banco banco = Banco.of();
        List<Empleado> empleados = ExamenEntrega3.empleadosNacidosDiaMes(banco, ini, fin);
        System.out.println("Empleados nacidos entre el " + ini + " y el " + fin + ":");
        empleados.forEach(empleado -> System.out.println(empleado));
		
	}
	public static void testempleadosDNI (){
		Banco banco = Banco.of();
		 Set<Character> letras = new HashSet<>(Arrays.asList('C', 'M'));
	     Map<Character, List<Empleado>> resultado = empleadosConLetraDNI(banco, letras);
	     resultado.forEach((letra, empleados) -> {
	            System.out.println("Letra DNI: " + letra);
	            empleados.forEach(empleado -> System.out.println(empleado)); });
	}
	public static void testclientesmedia (Integer m) {
		Banco banco = Banco.of();
		System.out.println("Edad media de clientes mayores de una edad específica:");
        Double edadMedia = clientesEdadMedia(banco, m);
        System.out.println(edadMedia);
	}
	public static void testclientesmenos (int n) {
		Banco banco = Banco.of();
		System.out.println("Clientes con menos préstamos:");
        Set<String> clientesMenosPrestamos = ExamenEntrega3.clientesConMenosPrestamos(banco, n);
        System.out.println(clientesMenosPrestamos);
	}
	 
	public static void main(String[] args) {
		ExamenEntrega3.testempleadosentre("01/01", "12/02");
		ExamenEntrega3.testempleadosentre("01/07", "12/02");  //ini posterior
		ExamenEntrega3.testempleadosentre("2432/43", "12/02"); //mal dia y mes
		ExamenEntrega3.testempleadosDNI();
		ExamenEntrega3.testclientesmedia(45);
		ExamenEntrega3.testclientesmedia(-5);  //m no puede ser negativa
		ExamenEntrega3.testclientesmedia(null); //m no puede ser nula
		ExamenEntrega3.testclientesmenos(-5);  //n no puede ser negativa
		ExamenEntrega3.testclientesmenos(2);
	}
}
