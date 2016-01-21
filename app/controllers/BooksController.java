package controllers;

import models.Book;
import play.data.Form;
import play.mvc.Result;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;


public class BooksController {

    static play.data.Form<Book> bookForm = play.data.Form.form(Book.class);

    public static Result book(Long id) {
        return ok(views.html.book.render(Book.get(id)));
    }

    public static Result books() {
        return ok(views.html.books.render(Book.all(), bookForm));
    }

    public static Result newBook() {
        Form<Book> filled = bookForm.bindFromRequest();
        if (filled.hasErrors()) {
            return badRequest(
                    views.html.books.render(Book.all(), filled)
            );
        } else {
            Book.create(filled.get());
            return redirect(routes.BooksController.books());
        }
    }

    public static Result deleteBook(Long id) {
        Book.delete(id);
        return redirect(routes.BooksController.books());
    }

    //TODO: use form, such as in newBook() method to change book!
    public static Result updateBook(Long id) {
        Form<Book> filled = bookForm.bindFromRequest();
        if (!filled.hasErrors()) {
            Book.change(id, filled.get());
            return redirect(routes.BooksController.books());
        } else {
            return badRequest(
                    views.html.books.render(Book.all(), filled)
            );
        }
    }
}
