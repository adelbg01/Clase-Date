package es.unileon.prg;

//Definimos la clase Date, a la que pertenecen los objetos day, month y year.

class Date {
    private int day;
    private int month;
    private int year;

    //Los siguientes métodos lanzarán una excepción si la fecha introducida es incorrecta

    public void setYear (int year) {
		this.year = year;
	}
    
      public Date (int day, int month, int year) throws DateException {
		this.month = month;
		this.setMonth(month);
		this.day = day;
		this.setDay(day);
		this.year = year;
		this.setYear(year);
	}

    public void setDay(int day) throws DateException {
		if ( day < 1 || day > getDaysOfMonth(this.month) ) {
			throw new DateException("Date error: Day " + day + " of month " + this.month + " not valid");			
		}
		this.day = day;
	}

    public void setMonth (int month) throws DateException {
		if ( month < 1 || month > 12) {
			throw new DateException("Date error: Month " + month + " not valid");
		}
		this.month = month;
	}

    //Este método toString convierte las fechas introducidas a un formato DD/MM/YYYY.

    public String toString(){
        return this.day+"/"+this.month+"/"+this.year+" ";
    }

    //Creamos también métodos que nos devuelvan el valor del dia, mes o año introducido.

    int getDay(){
        return this.day;
    }
    int getMonth(){
        return this.month;
    }
    int getYear(){
        return this.year;
    }

    //Para jugar con los métodos, vamos a crear un objeto que contenga la fecha de hoy.

    Date today = new Date(4, 11, 2022);

    //Creamos un método que nos diga si dos fechas pertenecen al mismo año, mes y dia, comparando cada parámetro de los introducidos con el objeto today.

    boolean isSameYear(Date another){
        return (this.year==another.getYear());
    }
    boolean isSameMonth(Date another){
        return (this.month==another.getMonth());
    }
    boolean isSameDay(Date another){
        return (this.day==another.getDay());
    }
    boolean isSame(Date another){
        return ((this.month==another.getMonth())&&(this.year==another.getYear())&&(this.day==another.getDay()));
    }

    //Creamos un método que nos diga el nombre de cada mes.
    //Para ello hacemos un switch con los 12 valores distintos que puede tomar el mes, atribuyendole a cada uno su nombre.
    //Dejamos un espacio al final de cada nombre para poder escribirlos todos seguidos en el metodo getMonthsTillEndYear.

    String getMonthName(int month){
        String monthName="";
        switch(month) {
            case 1: monthName="Enero ";
            break;
            case 2: monthName="Febrero ";
            break;
            case 3: monthName="Marzo ";
            break;
            case 4: monthName="Abril ";
            break;
            case 5: monthName="Mayo ";
            break;
            case 6: monthName="Junio ";
            break;
            case 7: monthName="Julio ";
            break;
            case 8: monthName="Agosto ";
            break;
            case 9: monthName="Septiembre ";
            break;
            case 10: monthName="Octubre ";
            break;
            case 11: monthName="Noviembre ";
            break;
            case 12: monthName="Diciembre ";
            break;
        }
        return monthName;
    }

    //Ahora creamos un método que nos diga en qué estación del año estamos:
    //Primavera: del 20 de marzo al 21 de junio. 
    //Verano: del 22 de junio al 23 de septiembre.
    //Otoño: del 23 de septiembre al 22 de diciembre.
    //Invierno: del 22 de diciembre al 20 de marzo.
    //Para ello comprobamos con qué estación se corresponde el dia y el mes de la fecha introducida.

    String getSeason(){
        String season = "";
        if (((this.month==3) && (this.day>=20))||(this.month==4)||(this.month==5)||((this.month==6)&&(this.day<=21))){
            season="Primavera";}
        else if (((this.month==6)&&(this.day>=21))||(this.month==7)||(this.month==8)||((this.month==9)&&(this.day<=23))){
            season="Verano";}
        else if (((this.month==9)&&(this.day>=23))||(this.month==10)||(this.month==11)||((this.month==12)&&(this.day<=22))){
            season="Otoño";}
        else season="Invierno";
        return season;
    }

    //Vamos a crear un método que, dada una fecha, nos diga los meses que falta para que se acabe el año.
    //Para ello crearemos un bucle con for, que tome el número del mes, le sume uno, recoja su valor y repita la operacion hasta el mes 12.
    //Nos apoyamos en el método getMonthName para atribuirle un nombre a todos los meses.

    StringBuffer getMonthsTillEndYear(){
        StringBuffer monthsTillEndYear=new StringBuffer("Los meses que faltan para que acabe el año son: ");
        for(int i=this.month; i<12; ++i) 
            monthsTillEndYear.append(getMonthName(this.month));
        return monthsTillEndYear;
    }
        
    //El siguiente es un metodo que nos dice cuántos dias tiene cada mes.
    //Para ello emplearemos un switch con los 12 meses del año y su cantidad de dias correspondiente.

    int getDaysOfMonth(int month){
        int daysOfMonth=0;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: daysOfMonth = 31;
            break;
            case 2: daysOfMonth = 28;
            break;
            case 4:
            case 6:
            case 9:
            case 11: daysOfMonth = 30;
            break;
            
        }
        return daysOfMonth;
    }
    
    //Este método me devuelve todas las fechas hasta el fin de mes.
    //Para ello empleamos un for, que recoja el dia del mes y vaya sumandole uno hasta el número de dias que tiene el mes.
    //Nos apoyamos en el método getDaysOfMonth para saber cuántos dias tiene el mes.
    //El resultado será un String con todas las fechas seguidas en formato DD/MM/YYYY.

    String getDatesTillEndMonth() throws DateException{
        int currentDay=this.day;
        Date datesTillEndMonth = new Date(currentDay, this.month, this.year);
        for (int j=this.day; j<=getDaysOfMonth(this.month); ++j) 
            currentDay=j;
        return (datesTillEndMonth.toString());    
    }

    //Vamos a crear un método que, dada una fecha, devuelva los meses con el mismo número de días que el introducido.
    //Para ello emplearemos un switch con los tres posibles números de dias: 28, 30 y 31.
    //El resultado será un StringBuffer con todos los meses, al que luego suprimiremos el mes actual, recogiendo el primer y último índice de la palabra.
    
    StringBuffer getMonthsSameDays(int month){
        StringBuffer monthsSameDays=new StringBuffer("");
        int thisMonthNameStart=monthsSameDays.indexOf(getMonthName(month));
        int thisMonthNameEnd=monthsSameDays.lastIndexOf(getMonthName(month));
            switch (getDaysOfMonth(month)){
                case 28: monthsSameDays.append("Febrero es el único mes con 28 dias");
                break;
                case 30: monthsSameDays.append("Abril Junio Septiembre Noviembre");
                    monthsSameDays.delete(thisMonthNameStart, thisMonthNameEnd);
                break;
                case 31: monthsSameDays.append("Enero Marzo Mayo Julio Agosto Octubre Diciembre");
                    monthsSameDays.delete(thisMonthNameStart, thisMonthNameEnd);
                break;
            }
        return monthsSameDays;
    } 

    //El siguiente método nos devolverá el número de días que han pasado desde que empezó el año
    //Para ello emplearemos un for, que recoja el número del mes actual, decremente su valor hasta enero y recoja los dias de cada mes
    //Nos apoyaremos en el método getDaysOfMonth() para sumar al contador el número de días de cada mes

    int getDaysSinceStartYear(){
        int daysSinceStartYear=this.day-1;
        for (int l=this.month; l>=1; --l)
            daysSinceStartYear=daysSinceStartYear+getDaysOfMonth(l);
        return daysSinceStartYear;
        }
        

    //Este método nos dirá cuantos intentos le lleva generar una fecha random que coincida con la del dia de hoy.
    //Vamos a emplear un while, que genere un número de mes y de día aleatorios.
    //Mientras el número de dia y de mes no coincidan con los actuales, el contador seguirá sumando intentos.

    int getRandomDateAccuracy(){
        int counter =0;
        int randomDay=(int) (Math.random()*30+1);
        int randomMonth=(int) (Math.random()*11+1);
        while ((randomDay!=today.getDay())&&(randomMonth!=today.getMonth())){
            counter=counter+1;
        }
        return counter;
    }

    //Con este método, dada una fecha, y sabiendo el día de la semana en que empezó el año (sábado), devolverá el día de la semana de hoy.
    //Emplearemos un switch con los posibles restos de dividir el dia del año entre 7, atribuyendo a cada uno un dia de la semana.
    //Nos apoyaremos en el metodo getDaysSinceStartYear() para saber el dia del año que es.

    String getWeekDay(){
        String weekDay="";
        int dayOfTheYear=today.getDaysSinceStartYear()+1;
            switch (dayOfTheYear%7){
            case 0: weekDay="Sábado";
            break;
            case 1: weekDay="Domingo";
            break;
            case 2: weekDay="Lunes";
            break;
            case 3: weekDay="Martes";
            break;
            case 4: weekDay="Miercoles";
            break;
            case 5: weekDay="Jueves";
            break;
            case 6: weekDay="Viernes";
            }
        return weekDay;
    }

   
}
