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

    public Magazine(String title) {
        this.title = title; //TODO: set number of pages
    }

    @OneToMany()
    public List<Article> articles;

    public static Finder<Long, Magazine> find;

    static {
        find = new Finder<>(
                Long.class, Magazine.class
        );
    }

    public static List<Magazine> all() {
        return find.all();
    }

    public static Magazine get(Long id) {
        return find.byId(id);
    }

    public static void create(Magazine magazine) {
        magazine.save();
    }

    public static void change(Long id, Magazine updated) {
        //TODO: make it more convenient!
        Magazine magazineToUpdate = find.byId(id);
        magazineToUpdate.title = updated.title;
        magazineToUpdate.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }
}
