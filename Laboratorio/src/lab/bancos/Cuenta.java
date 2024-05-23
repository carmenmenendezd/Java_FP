package lab.bancos;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cuenta {
	public String iban;
    public String dni;
    public LocalDateTime fechaCreacion;
    public Double saldo;

    Cuenta(String iban, String dni, LocalDateTime fechaCreacion, Double saldo) {
        this.iban = iban;
        this.dni = dni;
        this.fechaCreacion = fechaCreacion;
        this.saldo = saldo;
    }

    public static Cuenta of(String iban, String dni, LocalDateTime fechaCreacion2, Double saldo) {
        return new Cuenta(iban, dni, fechaCreacion2, saldo);
    }

    public static Cuenta parse(String text) {
        String[] partes = text.split(",");
        String iban = partes[0].strip();
        String dni = partes[1].strip();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaCreacion = LocalDateTime.parse(partes[2].strip(), formatter);
        Double saldo = Double.parseDouble(partes[3].strip());
        return Cuenta.of(iban, dni, fechaCreacion, saldo);
    }

    public void ingresar(Double c) {
        saldo += c;
    }

    public void retirar(Double c) {
        saldo -= c;
    }

    @Override
    public String toString() {
        return String.format("%s,%.2f", iban, saldo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return iban.equals(cuenta.iban) &&
                dni.equals(cuenta.dni) &&
                fechaCreacion.equals(cuenta.fechaCreacion) &&
                saldo.equals(cuenta.saldo);
    }

    @Override
    public int hashCode() {
        return iban.hashCode() + dni.hashCode() + fechaCreacion.hashCode() + saldo.hashCode();
    }
}
