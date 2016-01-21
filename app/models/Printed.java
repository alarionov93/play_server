package models;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;
import javax.persistence.*;

/**
 * Created by sanya on 20.01.16.
 */
@MappedSuperclass //this annotation is used for telling ebean that models have a parent class
public abstract class Printed extends Model {
    @Id
    public Long id; //fields can not be even protected !!

    public int numberOfPages;
}
