package by.todd.web;

/**
 * Created by sergey on 16.11.2014.
 */
public class SyncServlet extends BaseServlet {

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        final User user = SecurityHelper.getCurrentUser(req);
        if (user == null) {
            ResponseHelper.write(resp, Status.UNAUTHORIZED);
            return;
        }

        try {
            final String raw = InputHelper.getRaw(req);

            final JSONObject jo = new JSONObject(raw);
            final JSONArray tasks = jo.getJSONArray(Task.TASKS);

            int length = tasks.length();
            List<Task> list = new ArrayList<Task>(length);

            for (int i = 0; i < length; i++) {
                JSONObject task = (JSONObject) tasks.get(i);
                String title = task.getString(Task.TITLE);
                String content = task.getString(Task.CONTENT);
                String timestamp = task.getString(Task.TIMESTAMP);
                String tags = task.getString(Task.TAGS);
                list.add(new Task(user.getUserId(), title, content, timestamp, tags));
            }

            DatabaseHelper.addTasks(resp, list);
        } catch (IOException e) {
            ResponseHelper.write(resp, Status.BAD_REQUEST);
        } catch (JSONException e) {
            ResponseHelper.write(resp, Status.INTERNAL_SERVER_ERROR);
        }
    }*/
}
