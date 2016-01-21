package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Task;
import play.data.Form;
import play.mvc.*;
import com.fasterxml.jackson.core.*;

import java.util.List;

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

    public static Result jsonTasks() {
        ObjectMapper mapper = new ObjectMapper();
        List<Task> list = Task.all();
        String json = "";
        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ok(json);
    }

}
