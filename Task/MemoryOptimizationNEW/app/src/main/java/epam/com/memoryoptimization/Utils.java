package epam.com.memoryoptimization;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by Mike on 19.10.2014.
 */
public class Utils {


    private static Bitmap bitmap;

    public static Bitmap getIcon(Context ctx, SocialType type) {
        Bitmap value = BitmapFactory.decodeResource(ctx.getResources(),
                type.getResId());
        bitmap = Bitmap.createScaledBitmap(value, 256, 256, true);
        return bitmap;
    }

    public static int getRandomResId() {
        int i = new Random().nextInt(8);
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
