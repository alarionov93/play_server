package controllers;

import models.Book;
import play.mvc.*;
import play.data.Form;

import static play.mvc.Results.ok;

/**
 * Created by sanya on 19.01.16.
 */
public class BooksController {

    static play.data.Form<Book> taskForm = play.data.Form.form(Book.class);

    public static Result book(Long id) {
        return ok(views.html.book.render(Book.get(id)));
    }

    public static Result books() {
        return ok(views.html.books.render(Book.all()));
    }
}
