package controllers;

import models.Task;
import play.data.Form;
import play.mvc.*;

public class TasksController extends Controller {

    static play.data.Form<Task> taskForm = play.data.Form.form(Task.class);

    public static Result index() {
        return redirect(routes.TasksController.tasks());
    }

    public static Result task(Long id) {
        return ok(views.html.task.render(Task.get(id)));
    }

    public static Result tasks() {
        return ok(views.html.tasks.render(Task.all(), taskForm));
    }

    public static Result newTask() {
        Form<Task> filled = taskForm.bindFromRequest();
        if (filled.hasErrors()) {
            return badRequest(
                    views.html.tasks.render(Task.all(), filled)
            );
        } else {
            Task.create(filled.get());
            return redirect(routes.TasksController.tasks());
        }
    }

    public static Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.TasksController.tasks());
    }

}
