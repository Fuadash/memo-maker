package main.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

//Easier to use dates
public class MultiDate {
    public String month;
    public String date;
    public String time;

    public MultiDate() {
        Date moment = new Date();
        month = new SimpleDateFormat("yyyy-MM").format(moment);
        date = new SimpleDateFormat("yyyy-MM-dd").format(moment);
        time = new SimpleDateFormat("HH:mm").format(moment);
    }
}
