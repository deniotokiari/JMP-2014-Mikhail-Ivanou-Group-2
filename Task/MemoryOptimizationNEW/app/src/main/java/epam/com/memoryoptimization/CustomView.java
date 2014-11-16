package epam.com.memoryoptimization;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mike on 19.10.2014.
 */
public class CustomView extends View {

    private Bitmap mIcon; //naming convention https://source.android.com/source/code-style.html

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.L)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mIcon = getBitmap(R.drawable.ic_linked);
    }

    public void setNewIcon(int res) {
        mIcon = getBitmap(res);
    }

    private Bitmap getBitmap(int res) {
        return BitmapFactory.decodeResource(getContext().getResources(),
                res);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF destinationRect = new RectF(0, 0, getMeasuredWidth(), getMeasuredWidth());
        Rect originalRect = new Rect(0, 0, mIcon.getWidth(), mIcon.getHeight());
        canvas.drawBitmap(mIcon, originalRect, destinationRect, null);
    }

}
