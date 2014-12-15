package by.todd.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.todd.api.Status;
import by.todd.entity.Task;
import by.todd.web.helper.DatabaseHelper;
import by.todd.web.helper.InputHelper;
import by.todd.web.helper.ResponseHelper;

/**
 * Created by sergey on 16.11.2014.
 */
public class AddTaskServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final String raw = InputHelper.getRaw(req);

            final JSONObject jo = new JSONObject(raw);
            final JSONArray tasks = jo.getJSONArray(Task.TASKS);

            int length = tasks.length();
            List<Task> list = new ArrayList<Task>(length);

            for (int i = 0; i < length; i++) {
                list.add(new Task((JSONObject) tasks.get(i)));
            }

            DatabaseHelper.addTasks(resp, list);
        } catch (IOException e) {
            ResponseHelper.write(resp, Status.BAD_REQUEST);
        } catch (JSONException e) {
            ResponseHelper.write(resp, Status.INTERNAL_SERVER_ERROR);
        }
    }
}
