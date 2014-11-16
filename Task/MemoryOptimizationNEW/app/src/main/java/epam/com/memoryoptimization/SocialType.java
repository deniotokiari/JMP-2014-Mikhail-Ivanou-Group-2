package epam.com.memoryoptimization;

/**
 * Created by Mike on 22.11.13.
 */
public class SocialType {
    /*
    Enums often require more than twice as much memory as static constants.
    You should strictly avoid using enums on Android.
    (http://developer.android.com/training/articles/memory.html#YourApp)
     */

    //In this case we can use int variables (but it not flexible)
    public static final int FACEBOOK = R.drawable.ic_fb;
    public static final int LINKED_IN = R.drawable.ic_linked;
    public static final int TWITTER = R.drawable.ic_twitter;
    public static final int GOOGLE_PLUS = R.drawable.ic_g_plus;
    public static final int VKONTAKTE = R.drawable.ic_vk;
    public static final int ODNOKLASSNIKI = R.drawable.ic_odnoklassniki;

}

