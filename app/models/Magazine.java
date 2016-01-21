package models;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by sanya on 21.01.16.
 */
@Entity
public class Magazine extends Printed {

    @OneToMany(mappedBy = "magazine")
    public List<Article> articles;
}
