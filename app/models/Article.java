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

    public Date date = new Date();

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

    public static void delete(Long id) {
        find.ref(id).delete();
    }

//    public static List<Article> allFromYear(Date date) {
//        return find.where("date="+new Date().toString()).findList();
//    }
}