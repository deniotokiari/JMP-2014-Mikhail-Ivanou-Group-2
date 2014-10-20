package epam.com.memoryoptimization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

/**
 * Created by Mike on 19.10.2014.
 */
public class IconManager {
//use singleton

    private Context mContext;//variable is not used
    private HashMap<SocialType, Bitmap> mBitmaps;


    public IconManager(Context ctx) {
        ctx = mContext; //dead code
        mBitmaps = new HashMap<SocialType, Bitmap>();
        Bitmap value = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.ic_fb);
        mBitmaps.put(SocialType.FACEBOOK, value);
        Bitmap value2 = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.ic_g_plus);
        mBitmaps.put(SocialType.GOOGLE_PLUS, value2);
        Bitmap value3 = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.ic_vk);
        mBitmaps.put(SocialType.VKONTAKTE, value3);
        Bitmap value4 = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.ic_twitter);
        mBitmaps.put(SocialType.TWITTER, value4);
        Bitmap value5 = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.ic_linked);
        mBitmaps.put(SocialType.LINKED_IN, value4);
        Bitmap value6 = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.ic_odnoklassniki);
        mBitmaps.put(SocialType.ODNOKLASSNIKI, value6);
    }

    public Bitmap getIcon(SocialType type) {
        return mBitmaps.get(type);
    }

}
