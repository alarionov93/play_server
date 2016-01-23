package models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sanya on 20.01.16.
 */
@MappedSuperclass //this annotation is used for telling ebean that models have a parent class
public abstract class Printed extends Model {

    public String title;

    public DateTime date = new DateTime();

    public String date__str() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = formatter.parseDateTime(date.toString().split("T")[0]);
        return dt.toString().split("T")[0];
    }

    public int numberOfPages;

    public static Finder<Long, Printed> find;

    static {
        find = new Finder<>(Long.class, Printed.class);
    }

    public List<Printed> getAllByYear(DateTime date) {
        List<Printed> printedByYear;
        SimpleDateFormat dateS = new SimpleDateFormat("yyyy-MM-dd");
        dateS.format(date);
        printedByYear = Printed.find.where().between("date", new DateTime(), date).findList();

        return printedByYear;
    }
}
