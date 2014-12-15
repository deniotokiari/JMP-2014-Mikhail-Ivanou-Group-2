package by.todd.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import by.todd.entity.User;

/**
 * Created by sergey on 15.11.2014.
 */
public class ToddApplication extends Application {

    private static String mOwner;
    private static SharedPreferences mPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        mPreferences = getSharedPreferences(".prefs", Context.MODE_PRIVATE);
        mOwner = mPreferences.getString(User.EMAIL, "");
    }

    public static String getOwner() {
        return mOwner;
    }

    public static void setOwner(String owner) {
        mOwner = owner;
        mPreferences.edit().putString(User.EMAIL, owner).commit();
    }
}
