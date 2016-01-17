package models;

import play.api.data.*;
import play.data.validation.Constraints.*;
import java.util.*;

/**
 * Created by sanya on 17.01.16.
 */

public class Task {

    public Long id;

    @Required
    public String label;

    public static List<Task> all() {
        return new ArrayList<Task>();
    }

    public static void create(Task task) {
    }

    public static void delete(Long id) {
    }

}
