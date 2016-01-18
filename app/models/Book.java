package models;

import play.db.ebean.*;
import javax.persistence.*;
import play.data.validation.Constraints.*;
import java.util.*;
/**
 * Created by sanya on 19.01.16.
 */

@Entity
public class Book extends Model {

    @Id
    public Long id;

    @Required
    public String title;

    public Date date = new Date();

    public static Finder<Long, Book> find;

    static {
        find = new Finder<>(
                Long.class, Book.class
        );
    }

    public static List<Book> all() {
        return find.all();
    }

    public static Book get(Long id) {
        return find.byId(id);
    }

    public static void create(Book book) {
        book.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static List<Book> allFromYear(Date date) {
        return find.where("date="+new Date().toString()).findList();
    }
}
