package entrega1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class testFecha {
    public static void main(String[] args) {
        Fecha fecha = Fecha.parse("2024-03-19");
        LocalDate localDate = LocalDate.of(2024, 03, 19);

        System.out.println("Fecha: " + fecha);
        System.out.println("LocalDate: " + localDate);
        
        System.out.println("\nTest esAñoBisiesto:");
        System.out.println("Fecha.esañoBisiesto(): " + Fecha.esAñoBisiesto(fecha.año()));
        System.out.println("LocalDate.isLeapYear(): " + localDate.isLeapYear());
        
        System.out.println("\nTest nombreMes");
        System.out.println("Fecha.diasEnMes(): " + Fecha.diasEnMes(fecha.año(), fecha.mes()));
        System.out.println("LocalDate.lengthOfMonth(): " + localDate.lengthOfMonth());
        
        System.out.println("\nTest diaSemana");
        System.out.println("Día de la semana: " + fecha.diaSemana());
        System.out.println("localDate.getDayOfWeek().toString():" + localDate.getDayOfWeek());
        
        System.out.println("\nTest sumarDias");
        Fecha fechaSumada = fecha.sumarDias(10);
        LocalDate localDateSumada = localDate.plusDays(10);
        System.out.println("Fecha.sumardias(10): " + fechaSumada);
        System.out.println("LocalDate.plusDays(10): " + localDateSumada);
 
        System.out.println("\nTest restarDias");
        Fecha fechaRestada = fecha.restarDias(1000);
        LocalDate localDateRestada = localDate.minusDays(1000);
        System.out.println("Fecha.restarDias(10): " + fechaRestada);
        System.out.println("LocalDate.minusDays(10): " + localDateRestada);
 
        System.out.println("\nTest diferenciaEnDias");
        Integer diferenciaEnDias = fecha.diferenciaEnDias(Fecha.of(2025, 12, 30));
        long diferenciaEnDiasLocalDate = ChronoUnit.DAYS.between(localDate, LocalDate.of(2025, 12, 30));
        System.out.println("Fecha.diferenciaEnDias(fechaSumada): " + diferenciaEnDias);
        System.out.println("ChronoUnit.DAYS.between(localDate, localDateSumada): " + diferenciaEnDiasLocalDate);
        //Defensa
        System.out.println("\nTest restarDiasFechaDada");
        System.out.println("Fecha.restarDiasFechaDada(10): " + Fecha.restarDiasFechadada(fecha, 10));  //Miércoles, 28 de Febrero de 2024
        System.out.println("LocalDate.minusDays(10): " + localDate.minusDays(10));
        //System.out.println("Fecha.restarDiasFechaDada(-1): " + Fecha.restarDiasFechadada(fecha, -1)); //numDias debe ser positivo
        System.out.println("Fecha.restarDiasFechaDada(10): " + Fecha.restarDiasFechadada(fecha, 1000)); //numDias debe tener 3 cifras como máximo
    }
}