package epam.com.memoryoptimization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by Mike on 19.10.2014.
 */
public class Utils {

    //DST_WIDTH and DST_HEIGHT
    public static final int DST_WIDTH = 256;
    public static final int DST_HEIGHT = 256;
    public static final Random RANDOM = new Random(); //create constant for random generation

    public static Bitmap getIcon(Context ctx, int type) {
        final Bitmap src = BitmapFactory.decodeResource(ctx.getResources(),
                type);
        //if src == null throw exception
        if (src == null) {
            throw new NullPointerException("The image could not be decoded.");
        }
        return Bitmap.createScaledBitmap(src, DST_WIDTH, DST_HEIGHT, true);
    }

    public static int getRandomResId() {
        final int i = RANDOM.nextInt(8);
        switch (i) {
            case 1: return R.drawable.ic_fb;
            case 2: return R.drawable.ic_g_plus;
            case 3: return R.drawable.ic_linked;
            case 4: return R.drawable.ic_odnoklassniki;
            case 5: return R.drawable.ic_twitter;
            case 6: return R.drawable.ic_vk;
        }
        return R.drawable.ic_launcher;
    }
}
