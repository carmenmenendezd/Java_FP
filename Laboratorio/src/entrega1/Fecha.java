package entrega1;

public record Fecha (Integer año, Integer mes, Integer dia)implements Comparable<Fecha>  {


    public String nombreMes() {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes-1];
    }

    public String diaSemana() {
        String[] dias = {"Sábado", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        int h = zeller(dia, mes, año);
        return dias[h];
    }


    public Fecha sumarDias(Integer ndias) {
        int dia=this.dia;
        int mes=this.mes;
        int año=this.año;
        while (ndias>0) {
            int diasEnMes =diasEnMes(año, mes);
            int diasRestantes = diasEnMes - dia + 1;
            if (ndias>=diasRestantes) {
                ndias-=diasRestantes;
                dia=1;
                mes++;
                if (mes>12) {
                    mes=1;
                    año++;
                }
            } else {
                dia+=ndias;
                ndias=0;
            }
        }
        return new Fecha(año, mes, dia);
    }

    public Fecha restarDias(Integer ndias) {
        int dia=this.dia;
        int mes=this.mes;
        int año=this.año;
        while (ndias > 0) {
            int diasMes=diasEnMes(año, mes);
            if (ndias>=dia) {
                ndias-=dia;
                dia=diasMes;
                mes--;
                if (mes<1) {
                    mes=12;
                    año--;
                }
            } else {
                dia-=ndias;
                ndias=0;
            }
        }
        return new Fecha(año, mes, dia);
    }
    
    @Override
    public int compareTo(Fecha otraFecha) {
        if (this.año != otraFecha.año) {
            return Integer.compare(this.año, otraFecha.año);
        } else if (this.mes != otraFecha.mes) {
            return Integer.compare(this.mes, otraFecha.mes);
        } else {
            return Integer.compare(this.dia, otraFecha.dia);
        }
    }
    public int diferenciaEnDias(Fecha otraFecha) {
        Integer d=null;
        Integer difdias=0;
        Fecha menor=null;
        Fecha mayor=null;

        if (this.equals(otraFecha)) {
            return 0;
        } else if (this.compareTo(otraFecha)>0) {
            d=1;
            menor=otraFecha;
            mayor=this;
        } else {
            d=-1;
            menor=this;
            mayor=otraFecha;
        }
        while (!menor.equals(mayor)) {
            menor = menor.sumarDias(1);
            difdias++;
        }
        return d * difdias;
    }
    //Representacion cadena 
    
    @Override
    public String toString() {
        return String.format("%s, %d de %s de %d", diaSemana(), dia, nombreMes(), año);
    }
    
    //Metodos factoria
    public static Fecha of(int año, int mes, int dia) {
        return new Fecha(año, mes, dia);
    }
    
    public static Fecha parse(String fechaStr) {
        String[] partes = fechaStr.split("-");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato de fecha incorrecto");
        }
        int año = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        return new Fecha(año, mes, dia);
    }
    
   
    //Otros metodos
    public static boolean esAñoBisiesto(int año) {
        return (año%4==0 && año %100!=0) || (año%400 == 0);
    }
    
    public static int zeller(int dia, int mes, int año) {
        if (mes<3) {
            año--;
            mes += 12;
        }
        int K = año%100;
        int J = año/100;
        return (dia+13*(mes+1)/5+K+ K/4 + J/4 + 5*J) %7;
    }
    
    public static int diasEnMes(int año, int mes) {
        if (mes==2) {
            if (esAñoBisiesto(año)) {
                return 29; 
            } else {
                return 28; 
            }
        } else if (mes==4 || mes==6 || mes==9 || mes==11) {
            return 30; 
        } else {
            return 31; 
        }
    }
    

}
