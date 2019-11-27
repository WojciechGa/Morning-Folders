package start;

import java.util.Calendar;

public interface Days {

    //tests if day is working
    public static boolean isWorkingDay(Calendar c){
        boolean test;
        int day =  c.get(Calendar.DAY_OF_WEEK);
        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
            test = false;
        }
        else{
            test = true;
        }
        return test;
    }

    //subtracts one day if this is not working
    static Calendar previousWorkingDay(Calendar c){
        do{
            c.add(Calendar.DAY_OF_MONTH, -1);
        }while(!isWorkingDay(c));
        return c;
    }

    //checks if Calendar.DAY_OF_WEEK is Monday
    static boolean isMonday(Calendar c){
        boolean test = false;
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            test = true;
        }
        return test;
    }


}
