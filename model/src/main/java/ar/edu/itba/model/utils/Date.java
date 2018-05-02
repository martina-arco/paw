package ar.edu.itba.model.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date>{

    private final static String format = "%02d/%02d/%04d";

    public enum Unit{
        DAY, WEEK, MONTH, YEAR
    }

    public static Date timeTravel(final Unit unit,final int amount,final Date date){
        Calendar calendar = new GregorianCalendar(date.year,date.month,date.day);
        int param;
        switch(unit){
            case DAY:
                param = Calendar.DAY_OF_YEAR;
                break;
            case WEEK:
                param = Calendar.WEEK_OF_YEAR;
                break;
            case MONTH:
                param = Calendar.MONTH;
                break;
            case YEAR:
                param = Calendar.YEAR;
                break;
            default:
                throw new IllegalArgumentException();
        }
        calendar.add(param,amount);
        return new Date(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));

    }

    private final int day, month, year;

    public Date(final int day,final int month,final int year){
        if(!isValid(day, month, year))
            throw new IllegalArgumentException();
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isValid(final int day,final int month,final int year){
        try{
            Calendar calendar = new GregorianCalendar(year, month, day);
            calendar.setLenient(false);
            calendar.get(Calendar.DAY_OF_MONTH);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Date date) {
        if(year > date.year)
            return 1;
        else if(year < date.year)
            return -1;
        else if(month > date.month)
            return 1;
        else if(month < date.month)
            return -1;
        else if(day > date.day)
            return 1;
        else if(day < date.day)
            return -1;
        return 0;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString(){
        return String.format(format,day,month, year);
    }
}
