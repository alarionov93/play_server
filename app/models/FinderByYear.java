package models;

import org.joda.time.DateTime;
import play.db.ebean.Model;

import java.util.*;

/**
 * Created by sanya on 25.01.16.
 */
public class FinderByYear extends Model implements Iterator<Printed> {
    public static TreeMap<Integer, Printed> getAllByYear(DateTime date) { // TODO: test this method properly!!
        List<Article> articles = Article.find.where().between("date", date, new DateTime()).findList();
        List<Book> books = Book.find.where().between("date", date, new DateTime()).findList();
        List<Magazine> magazines = Magazine.find.where().between("date", date, new DateTime()).findList();

        TreeMap<Integer, Printed> allByYear = new TreeMap<>();
        for (Printed p:articles) {
            allByYear.put(index++, p);
        }

        for (Printed p:books) {
            allByYear.put(index++, p);
        }

        for (Printed p:magazines) {
            allByYear.put(index++, p);
        }

        return allByYear;
    }

    public ArrayList<Printed> printed;
    public static int index;

    public FinderByYear (ArrayList<Printed> printed) {
        this.printed = printed;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return !(printed.size() == index);
    }

    @Override
    public Printed next() {
        try {
            if (hasNext())
                return printed.get(index++);
            else
                throw new NoSuchElementException("Next element not found");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void remove() {
        printed.remove(--index);
    }
}
