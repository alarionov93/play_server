package controllers;

import models.Article;
import models.Magazine;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

import static controllers.routes.*;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by sanya on 22.01.16.
 */
public class ArticlesController {
    static play.data.Form<Article> articleForm = play.data.Form.form(Article.class);

    public static Result article(Long id) {
        return ok(views.html.article.render(Article.get(id)));
    }

    public static Result articles() {
        return ok(views.html.articles.render(Article.all(), articleForm, Magazine.all()));
    }

    public static Result newArticle() {
        return ok(views.html.newArticle.render(articleForm, Magazine.all()));
    }

    public static Result addArticle() {
        DynamicForm requestData = Form.form().bindFromRequest();
        Long magazine_id = Long.parseLong(requestData.get("magazine_id"));

        Form<Article> a_filled = articleForm.bindFromRequest();
        if (a_filled.hasErrors()) {
            return badRequest(
                    views.html.articles.render(Article.all(), a_filled, Magazine.all())
            );
        } else {
            Article.create(a_filled.get(), magazine_id);
            return redirect(ArticlesController.articles());
        }
    }

    public static Result deleteArticle(Long id) {
        Article.delete(id);
        return redirect(ArticlesController.articles());
    }

    //TODO: use form, such as in addBook() method to change book!
    public static Result updateArticle(Long id) {
        DynamicForm requestData = Form.form().bindFromRequest();
        Long magazine_id = Long.parseLong(requestData.get("magazine_id"));

        Form<Article> filled = articleForm.bindFromRequest();
        if (!filled.hasErrors()) {
            Article.change(id, filled.get(), magazine_id);
            return redirect(ArticlesController.articles());
        } else {
            //TODO: what we can do here?
            return badRequest(
                    views.html.articles.render(Article.all(), filled, Magazine.all())
            );
        }
    }
}
