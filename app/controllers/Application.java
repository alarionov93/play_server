package controllers;

import models.Task;
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
        return TODO;
    }

    public static Result deleteTask(Long id) {
        return TODO;
    }
}
