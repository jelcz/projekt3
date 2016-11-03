package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jelcz on 2016-10-14.
 */
public class MyLogger {

    public void log(String text){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        System.out.println(dateFormat.format(cal.getTime()) + " - " +text);
    }
}
