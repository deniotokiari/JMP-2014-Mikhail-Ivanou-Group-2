package epam.com.memoryoptimization;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

/**
 * Created by Mike on 19.10.2014.
 */
public class IconManager {

    private static final int INITIAL_CAPACITY = 6;
    private final SparseArray<Bitmap> mMapBitmaps;
    /*
    Putting a single entry into a HashMap requires the allocation of an additional entry object
    that takes 32 bytes (see the previous section about optimized data containers).

    Take advantage of optimized containers in the Android framework, such as SparseArray,
    SparseBooleanArray, and LongSparseArray. The generic HashMap implementation can be quite memory
    inefficient because it needs a separate entry object for every mapping.
    Additionally, the SparseArray classes are more efficient because they avoid the system's
    need to autobox the key and sometimes value (which creates yet another object or two per entry).
    And don't be afraid of dropping down to raw arrays when that makes sense.
     */

    //Use singleton pattern for this class
    private static IconManager sInstance;

    private IconManager(Context ctx) {
        final Resources resources = ctx.getResources();//create local variable
        mMapBitmaps = new SparseArray<Bitmap>(INITIAL_CAPACITY);//initial capacity for array (dont get extra memory)

        mMapBitmaps.put(SocialType.FACEBOOK, BitmapFactory.decodeResource(resources,
                R.drawable.ic_fb));
        mMapBitmaps.put(SocialType.GOOGLE_PLUS, BitmapFactory.decodeResource(resources,
                R.drawable.ic_g_plus));
        mMapBitmaps.put(SocialType.VKONTAKTE, BitmapFactory.decodeResource(resources,
                R.drawable.ic_vk));
        mMapBitmaps.put(SocialType.TWITTER, BitmapFactory.decodeResource(resources,
                R.drawable.ic_twitter));
        mMapBitmaps.put(SocialType.LINKED_IN, BitmapFactory.decodeResource(resources,
                R.drawable.ic_linked));
        mMapBitmaps.put(SocialType.ODNOKLASSNIKI, BitmapFactory.decodeResource(resources,
                R.drawable.ic_odnoklassniki));
    }

    public Bitmap getIcon(int type) {
        return mMapBitmaps.get(type);
    }

    public static IconManager get() {
        if (sInstance == null) {
            sInstance = new IconManager(XApplication.getContext());
        }
        return sInstance;
    }
}
