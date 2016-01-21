package models;

import play.db.ebean.*;
import javax.persistence.*;
import play.data.validation.Constraints.*;
import java.util.*;
/**
 * Created by sanya on 19.01.16.
 */

@Entity
public class Article extends Printed {

    @Required
    public String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id", referencedColumnName="id")
    public Magazine magazine;

    public static Finder<Long, Article> find;

    static {
        find = new Finder<>(
                Long.class, Article.class
        );
    }

    public static List<Article> all() {
        return find.all();
    }

    public static Article get(Long id) {
        return find.byId(id);
    }

    public static void create(Article article) {
        article.save();
    }

    public static void change(Long id, Article updated) {
        //TODO: make it more convenient!
        Article articleToUpdate = find.byId(id);
        articleToUpdate.title = updated.title;
        articleToUpdate.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }
}