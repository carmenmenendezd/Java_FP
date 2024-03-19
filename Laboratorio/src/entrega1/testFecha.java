package entrega1;
 
public class testFecha {
	public static void main(String[] args) {
        testnombreMes();
        testdiaSemana();
        testsumarDias();
        testrestarDias();
        testdiferenciaEnDias();
    }

    public static void testnombreMes() {
        Fecha fecha=Fecha.of(2024, 03, 19);
        String nombreMes=fecha.nombreMes();
        System.out.println("Test nombreMes");
        System.out.println("Nombre del mes: "+nombreMes);
    }

    public static void testdiaSemana() {
        Fecha fecha=Fecha.of(2024, 03, 19);
        String diaSemana=fecha.diaSemana();
        System.out.println("\nTest diaSemana");
        System.out.println("Día de la semana: " +diaSemana);
    }

    public static void testsumarDias() {
        Fecha fecha=Fecha.of(2024, 03, 19);
        Fecha fechaNueva=fecha.sumarDias(5);
        System.out.println("\nTest sumarDias");
        System.out.println("Fecha después de sumar 5 días: " +fechaNueva);
    }

    public static void testrestarDias() {
        Fecha fecha = Fecha.of(2024, 03, 19);
        Fecha fechaNueva = fecha.restarDias(3);
        System.out.println("\nTest restarDias");
        System.out.println("Fecha después de restar 3 días: "+fechaNueva);
    }

    public static void testdiferenciaEnDias() {
        Fecha fecha1 = Fecha.of(2024, 03, 17);
        Fecha fecha2 = Fecha.of(2024, 04, 18);
        Integer diferencia = fecha1.diferenciaEnDias(fecha2);
        System.out.println("\nTest restarDias");
        System.out.println("Diferencia en días: "+diferencia);
    }
}