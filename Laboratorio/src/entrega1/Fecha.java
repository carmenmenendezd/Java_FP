package entrega1;

public record Fecha (Integer año, Integer mes, Integer dia) {


    public String nombreMes() {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes-1];
    }

    public String diaSemana() {
        String[] dias = {"Sábado", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        Integer s = zeller(dia, mes, año);
        return dias[s];
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
                dia = diasEnMes(año, mes);
            } else {
                dia-=ndias;
                ndias=0;
            }
        }
        return new Fecha(año, mes, dia);
    }
    
    
    public Integer diferenciaEnDias(Fecha f) {
    	Integer d;
    	Fecha menor;
    	Fecha mayor;
    	int difdias = 0;
    	if (this==f) {
    		return 0;
    	}
    	
        if (this.año() > f.año() ||
            (this.año().equals(f.año()) && this.mes() > f.mes()) ||
            (this.año().equals(f.año()) && this.mes().equals(f.mes()) && this.dia() > f.dia())) {
            d = -1;
            menor=f;
            mayor=this;
        } else {
            d = 1;
            menor=this;
            mayor=f;
        }
       
    	while (!menor.equals(mayor)) {
            menor = menor.sumarDias(1);
            difdias++;
        }
    	return d*difdias;
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
    public static Fecha restarDiasFechadada(Fecha fecha, Integer numDias) {
        int dia=fecha.dia;
        int mes=fecha.mes;
        int año=fecha.año;
        if (numDias > 999) {
            throw new IllegalArgumentException("numDias debe tener 3 cifras como máximo");
        }
        if (numDias < 1) {
            throw new IllegalArgumentException("numDias debe ser positivo");
        }
        while (numDias > 0) {
            int diasMes=diasEnMes(año, mes);
            if (numDias>=dia) {
                numDias-=dia;
                dia=diasMes;
                mes--;
                if (mes<1) {
                    mes=12;
                    año--;
                }
                dia = diasEnMes(año, mes);
            } else {
                dia-=numDias;
                numDias=0;
            }
        }
        return new Fecha(año, mes, dia);
    }

}
