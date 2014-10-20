package epam.com.memoryoptimization;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

/**
 * Created by Mike on 19.10.2014.
 */
public class XApplication extends Application {

    public static Activity mCurrentActivity;
    private static Fragment mCurrentFragment;
    private static long mLastActivity;


    @Override
    public void onCreate() {
        super.onCreate();
        mLastActivity = System.currentTimeMillis();

    }
}
