package controllers;

import models.Article;
import models.Magazine;
import play.data.Form;
import play.mvc.Result;
import views.html.*;
import views.html.newArticle;

import static controllers.routes.*;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

/**
 * Created by sanya on 23.01.16.
 */
public class MagazinesController {
    static play.data.Form<Magazine> magazineForm = play.data.Form.form(Magazine.class);

    public static Result magazine(Long id) {
        return ok(views.html.magazine.render(Magazine.get(id)));
    }

    public static Result magazines() {
        return ok(magazines.render(magazineForm, Magazine.all()));
    }

    public static Result newMagazine() {
        return ok(newMagazine.render(magazineForm));
    }

    public static Result addMagazine() {
        Form<Magazine> m_filled = magazineForm.bindFromRequest();
        if (m_filled.hasErrors()) {
            return badRequest(
                    views.html.magazines.render(m_filled, Magazine.all())
            );
        } else {
            Magazine.create(m_filled.get());
            return redirect(MagazinesController.magazines());
        }
    }

    public static Result deleteMagazine(Long id) {
        Magazine.delete(id);
        return redirect(MagazinesController.magazines());
    }

    //TODO: use form, such as in addBook() method to change book!
    public static Result updateMagazine(Long id) {
        Form<Magazine> filled = magazineForm.bindFromRequest();
        if (!filled.hasErrors()) {
            Magazine.change(id, filled.get());
            return redirect(MagazinesController.magazines());
        } else {
            //TODO: what we can do here?
            return badRequest(
                    views.html.magazines.render(filled, Magazine.all())
            );
        }
    }
}
