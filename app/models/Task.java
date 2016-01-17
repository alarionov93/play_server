package models;

import play.api.data.*;
import play.db.ebean.*;
import javax.persistence.*;
import play.data.validation.Constraints.*;
import java.util.*;

/**
 * Created by sanya on 17.01.16.
 */

@Entity
public class Task extends Model{

    @Id
    public Long id;

    @Required
    public String label;

    public static Finder<Long,Task> find;

    static {
        find = new Finder(
                Long.class, Task.class
        );
    }

    public static List<Task> all() {
        return find.all();
    }

    public static void create(Task task) {
        task.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
