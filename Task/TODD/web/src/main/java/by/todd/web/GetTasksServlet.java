package by.todd.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.todd.api.Api;
import by.todd.api.Status;
import by.todd.entity.Task;
import by.todd.web.helper.DatabaseHelper;
import by.todd.web.helper.ResponseHelper;

/**
 * Created by sergey on 16.11.2014.
 */
public class GetTasksServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            final String owner = req.getParameter(Api.GetTask.PARAM_OWNER);
            if (owner == null || owner.isEmpty()) {
                throw new NumberFormatException("Incorrect owner");
            }

            final String pOffset = req.getParameter(Api.GetTask.PARAM_OFFSET);
            final int offset;
            if (pOffset != null) {
                if (pOffset.isEmpty()) {
                    throw new NumberFormatException("Incorrect offset");
                } else {
                    offset = Integer.valueOf(pOffset);
                    if (offset < 0) {
                        throw new NumberFormatException("Incorrect offset");
                    }
                }
            } else {
                offset = 0;
            }

            final String pLimit = req.getParameter(Api.GetTask.PARAM_LIMIT);
            final int limit;
            if (pLimit != null) {
                if (pLimit.isEmpty()) {
                    throw new NumberFormatException("Incorrect limit");
                } else {
                    limit = Integer.valueOf(pLimit);
                    if (limit < 0) {
                        throw new NumberFormatException("Incorrect limit");
                    }
                }
            } else {
                limit = 0;
            }

            final List<Task> tasks = DatabaseHelper.getTasks(owner, offset, limit);

            JSONArray jatasks = new JSONArray();
            for (int i = 0; i < tasks.size(); i++) {
                jatasks.put(tasks.get(i).toJSONObject());
            }
            JSONObject result = new JSONObject();
            result.put(Task.TASKS, jatasks);
            ResponseHelper.writeOk(resp, result);
        } catch (JSONException e) {
            ResponseHelper.write(resp, Status.INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException e) {
            ResponseHelper.write(resp, Status.BAD_REQUEST);
        }
    }

}
