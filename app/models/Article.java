package models;

import org.joda.time.DateTime;
import play.db.ebean.*;
import javax.persistence.*;
import play.data.validation.Constraints.*;
import java.util.*;
/**
 * Created by sanya on 19.01.16.
 */

@Entity
public class Article extends Printed {

//    public Article(String title) {
//        this.title = title;
//    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long id; //fields can not be even protected !!

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="magazine_id", referencedColumnName="id")
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

    public static void create(Article article, Long id) {
        Magazine magazine = Magazine.find.byId(id);
        article.magazine = magazine;
        magazine.articles.add(article);
        article.save();
    }

    public static void change(Long id, Article updated, Long magazine_id) {
        //TODO: make it more convenient!
        if(updated.title.length() > 0 && updated.date != null && updated.numberOfPages > 0) {
            Article articleToUpdate = find.byId(id);
            Magazine magazine =  Magazine.find.byId(magazine_id);
            articleToUpdate.title = updated.title;
            articleToUpdate.date = updated.date;
            articleToUpdate.magazine = magazine;
            articleToUpdate.numberOfPages = updated.numberOfPages;
            articleToUpdate.save();
        } else {
            //TODO:
        }
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public List<Printed> byYear(DateTime dateTime) {
        return super.getAllByYear(dateTime);
    }
}