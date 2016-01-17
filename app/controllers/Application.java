package controllers;

import models.Task;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    static play.data.Form<Task> taskForm = play.data.Form.form(Task.class);

    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    public static Result tasks() {
        return ok(views.html.index.render(Task.all(), taskForm));
    }

    public static Result newTask() {
        Form<Task> filled = taskForm.bindFromRequest();
        if (filled.hasErrors()) {
            return badRequest(
                    views.html.index.render(Task.all(), filled)
            );
        } else {
            Task.create(filled.get());
            return redirect(routes.Application.tasks());
        }
    }

    public static Result deleteTask(Long id) {
        return TODO;
    }
}
