package models;

import play.db.ebean.*;
import javax.persistence.*;
import play.data.validation.Constraints.*;
import java.util.*;
/**
 * Created by sanya on 19.01.16.
 */

@Entity
public class Book extends Printed {

    @Required
    public String title;

    public Date date = new Date();

    public static Finder<Long, Book> find;

    static {
        find = new Finder<>(
                Long.class, Book.class
        );
    }

    public Book() {}

    public Book(Long id, String title, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.numberOfPages = numberOfPages;
    } //this is used for creating Book from json string, now is not needed no more

    public static List<Book> all() {
        return find.all();
    }

    public static Book get(Long id) {
        return find.byId(id);
    }

    public static void create(Book book) {
        book.save();
    }

    public static void change(Long id, Book updated) {
        Book bookToUpdate = find.byId(id);
        if (updated.title != null) {//TODO: make it more convenient!
            bookToUpdate.title = updated.title;
            bookToUpdate.numberOfPages = updated.numberOfPages;
            bookToUpdate.date = updated.date;
        }
        bookToUpdate.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static List<Book> allFromYear(Date date) {
        return find.where("date="+new Date().toString()).findList();
    }
}
