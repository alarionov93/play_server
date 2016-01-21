package controllers;

import models.Article;
import play.data.Form;
import play.mvc.Result;

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
        return ok(views.html.articles.render(Article.all(), articleForm));
    }

    public static Result newArticle() {
        return ok(views.html.newArticle.render(articleForm));
    }

    public static Result addArticle() {
        Form<Article> filled = articleForm.bindFromRequest();
        if (filled.hasErrors()) {
            return badRequest(
                    views.html.articles.render(Article.all(), filled)
            );
        } else {
            Article.create(filled.get());
            return redirect(routes.ArticlesController.articles());
        }
    }

    public static Result deleteArticle(Long id) {
        Article.delete(id);
        return redirect(routes.ArticlesController.articles());
    }

    //TODO: use form, such as in addBook() method to change book!
    public static Result updateArticle(Long id) {
        Form<Article> filled = articleForm.bindFromRequest();
        if (!filled.hasErrors()) {
            Article.change(id, filled.get());
            return redirect(routes.ArticlesController.articles());
        } else {
            //TODO: what we can do here?
            return badRequest(
                    views.html.articles.render(Article.all(), filled)
            );
        }
    }
}
