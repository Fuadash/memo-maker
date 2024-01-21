package main.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

//Easier to use dates
public class MultiDate {
    public String month;
    public String date;
    public String time;

    // Constructs a MultiDate at the current time
    public MultiDate() {
        Date moment = new Date();
        month = new SimpleDateFormat("yyyy-MM").format(moment);
        date = new SimpleDateFormat("yyyy-MM-dd").format(moment);
        time = new SimpleDateFormat("HH:mm").format(moment);
    }

    // Constructs a MultiDate at a given time
    public MultiDate(Date targetDate) {
        this.month = new SimpleDateFormat("yyyy-MM").format(targetDate);
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(targetDate);
        this.time = new SimpleDateFormat("HH:mm").format(targetDate);
    }
}
