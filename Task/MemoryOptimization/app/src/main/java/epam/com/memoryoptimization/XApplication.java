package epam.com.memoryoptimization;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

/**
 * Created by Mike on 19.10.2014.
 */
public class XApplication extends Application { //class is not used in android manifest

    public static Activity mCurrentActivity;
    private static Fragment mCurrentFragment; //variable is not used
    private static long mLastActivity; //variable is not used


    @Override
    public void onCreate() {
        super.onCreate();
        mLastActivity = System.currentTimeMillis();

    }
}
