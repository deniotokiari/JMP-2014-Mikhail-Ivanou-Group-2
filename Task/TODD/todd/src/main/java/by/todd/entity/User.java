package by.todd.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class User {

    public static final String TABLE_NAME = "User";

    public static final String NAME = "name";
    private String mName = "";

    public static final String EMAIL = "email";
    private String mEmail = "";

    public User(String name, String email) {
        mName = name;
        mEmail = email;
    }

    public User(JSONObject user) throws JSONException {
        mName = user.getString(User.NAME);
        mEmail = user.getString(User.EMAIL);
    }

    public User(Map<String, Object> map) {
        mName = (String) map.get(User.NAME);
        mEmail = (String) map.get(User.EMAIL);
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put(User.NAME, mName);
        jo.put(User.EMAIL, mEmail);
        return jo;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }
}
