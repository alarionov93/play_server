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

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long id; //fields can not be even protected !!

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="magazine_id", referencedColumnName="id")
    public Magazine magazine;

//    @Column(name="author")
//    public String author;

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
//        DateTime jDate = new DateTime(article.date); //TODO: understand why this code did not work as it was desired!!!
//        article.date = jDate.toDate(); //TODO: make a subscriber to date field creation and update in Printed.class?
        article.jDate = new DateTime(article.date);
        article.date = article.jDate.toDate();
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
            articleToUpdate.jDate = new DateTime(updated.date); //TODO: set such assignment as here in all models!!!
            articleToUpdate.date = articleToUpdate.jDate.toDate();
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
}