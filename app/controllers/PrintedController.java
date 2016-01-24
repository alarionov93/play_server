package controllers;

import models.FinderByYear;
import models.Magazine;
import models.Printed;
import org.joda.time.DateTime;
import java.util.List;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

/**
 * Created by sanya on 25.01.16.
 */
public class PrintedController {

//    static play.data.Form<Printed> printedForm = play.data.Form.form(Printed.class);

    public static Result getAllByYear() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String dateStr = requestData.get("date");
        if (!requestData.hasErrors() && dateStr.length() > 0) {
            Integer year = Integer.parseInt(dateStr.split("-")[0]);
            DateTime jDate = new DateTime().withDate(year, 12, 1);

            return ok(views.html.printedByYear.render(FinderByYear.getAllByYear(jDate)));
        } else {
            DateTime jDate = new DateTime().withDate(1500, 12, 1);

            return badRequest(views.html.printedByYear.render(FinderByYear.getAllByYear(jDate)));
        }
    }

    public static Result allByYear() {
        DateTime jDate = new DateTime().withDate(1500, 12, 1);

        return ok(views.html.printedByYear.render(FinderByYear.getAllByYear(jDate)));
    }
}
