package by.todd.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Task {

    public static final String TABLE_NAME = "Task";

    public static final String TASKS = "tasks";
    private List<Task> mTasks = Collections.EMPTY_LIST;

    public static final String OWNER = "owner";
    private String mOwner = "";

    public static final String TITLE = "title";
    private String mTitle = "";

    public static final String CONTENT = "content";
    private String mContent = "";

    public static final String TIMESTAMP = "timestamp";
    private String mTimestamp = "";

    public static final String TAGS = "tags";
    private String mTags = "";

    public Task(String owner, String title, String content, String timestamp, String tags) {
        mOwner = owner;
        mTitle = title;
        mContent = content;
        mTimestamp = timestamp;
        mTags = tags;
    }

    public Task(JSONObject task) throws JSONException {
        mOwner = task.getString(Task.OWNER);
        mTitle = task.getString(Task.TITLE);
        mContent = task.getString(Task.CONTENT);
        mTimestamp = task.getString(Task.TIMESTAMP);
        mTags = task.getString(Task.TAGS);
    }

    public Task(Map<String, Object> map) {
        mOwner = (String) map.get(Task.OWNER);
        mTitle = (String) map.get(Task.TITLE);
        mContent = (String) map.get(Task.CONTENT);
        mTimestamp = (String) map.get(Task.TIMESTAMP);
        mTags = (String) map.get(Task.TAGS);
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put(Task.OWNER, mOwner);
        jo.put(Task.TITLE, mTitle);
        jo.put(Task.CONTENT, mContent);
        jo.put(Task.TIMESTAMP, mTimestamp);
        jo.put(Task.TAGS, mTags);
        return jo;
    }

    public Task(List<Task> tasks) {
        mTasks = tasks;
    }

    public String getOwner() {
        return mOwner;
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

}
