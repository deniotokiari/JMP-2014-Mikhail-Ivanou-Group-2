package by.todd.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class Task {

    public static final String TASK = "Task";

    public static final String TASKS = "tasks";
    private List<Task> mTasks = Collections.EMPTY_LIST;

    public static final String USER = "user";
    private String mUser = "";

    public static final String TITLE = "title";
    private String mTitle = "";

    public static final String CONTENT = "content";
    private String mContent = "";

    public static final String TIMESTAMP = "timestamp";
    private String mTimestamp = "";

    public static final String TAGS = "tags";
    private String mTags = "";

    public Task(String user, String title, String content, String timestamp, String tags) {
        mUser = user;
        mTitle = title;
        mContent = content;
        mTimestamp = timestamp;
        mTags = tags;
    }

    public Task(String user, JSONObject task) throws JSONException {
        mUser = user;
        mTitle = task.getString(Task.TITLE);
        mContent = task.getString(Task.CONTENT);
        mTimestamp = task.getString(Task.TIMESTAMP);
        mTags = task.getString(Task.TAGS);
    }

    public Task(List<Task> tasks) {
        mTasks = tasks;
    }


    public String getUser() {
        return mUser;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public String getTags() {
        return mTags;
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put(Task.USER, mUser);
        jo.put(Task.TITLE, mTitle);
        jo.put(Task.CONTENT, mContent);
        jo.put(Task.TIMESTAMP, mTimestamp);
        jo.put(Task.TAGS, mTags);
        return jo;
    }
}
