package models;

//import org.joda.time.DateTime;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sanya on 20.01.16.
 */
@MappedSuperclass //this annotation is used for telling ebean that models have a parent class
public abstract class Printed extends Model {

    public String title;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    public DateTime jDate = new DateTime(); //TODO: do something with this - forms are getting invalid date error

    public Date date = jDate.toDate();
    //TODO: make a constructor with input date assignment!!!
//    protected DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//    public DateTime date = formatter.parseDateTime(new DateTime().toString().split("T")[0]);

    public String date__str() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String date = jDate.toString().split("T")[0];
        DateTime dt = formatter.parseDateTime(date);
        return dt.toString().split("T")[0];
    }

    public int numberOfPages;

    public static Finder<Long, Printed> find;

    static {
        find = new Finder<>(Long.class, Printed.class);
    }
}
