package epam.com.memoryoptimization;

import android.app.Application;
import android.content.Context;

//I've removed unused import classes
/**
 * Created by Mike on 19.10.2014.
 */
public class XApplication extends Application {
   /*
        ***Follow Field Naming Conventions***
    Non-public, non-static field names start with m.
    Static field names start with s.
    Other fields start with a lower case letter.
    Public static final fields (constants) are ALL_CAPS_WITH_UNDERSCORES.
     */

    /*
        ***SOMW LINKS***
    https://source.android.com/source/code-style.html
    http://developer.android.com/training/articles/memory.html
    https://developer.android.com/training/displaying-bitmaps/manage-memory.html
    */


    //I've removed unused variables mCurrentFragment, mLastActivity
    private static Context mApplicationContext;//I've renamed mCurrentActivity to mApplicationContext

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }

    public static Context getContext() {
        return mApplicationContext;
    }

}
