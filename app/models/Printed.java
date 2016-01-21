package models;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sanya on 20.01.16.
 */
@MappedSuperclass //this annotation is used for telling ebean that models have a parent class
public abstract class Printed extends Model {
    @Id
    public Long id; //fields can not be even protected !!

    public String title;

    public Date date = new Date();

    public String date__str() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public int numberOfPages;
}
