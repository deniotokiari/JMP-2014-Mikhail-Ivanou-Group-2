package com.epam.jmp.garbagecollection;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.util.HashMap;


public class SampleActivity extends ListActivity {

    public static final String EXTRA_FILES = "com.epam.jmp.garbagecollection.EXTRA_FILES";
    public static final String EXTRA_IS_OPTIMIZED = "com.epam.jmp.garbagecollection.EXTRA_IS_OPTIMIZED";

    private static final int LAYOUT_RES_ID = R.layout.adapter_sample;

    public static final boolean DEFAULT_IS_OPTIMIZED = false;
    HashMap<String, Bitmap> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final Intent intent = getIntent();
        String[] files = null;
        boolean isOptimized = DEFAULT_IS_OPTIMIZED;
        if (intent != null) {
            files = intent.getStringArrayExtra(EXTRA_FILES);
            isOptimized = intent.getBooleanExtra(EXTRA_IS_OPTIMIZED, DEFAULT_IS_OPTIMIZED);
            mMap = new HashMap<String, Bitmap>();
        }
        setListAdapter(new ImageAdapter(getApplicationContext(), files, isOptimized));
    }

    private class ImageAdapter extends ArrayAdapter<String> {

        private final boolean isOptimized;

        public ImageAdapter(Context context, String[] files, boolean isOptimized) {
            super(context, LAYOUT_RES_ID, files);
            this.isOptimized = isOptimized;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView view = (ImageView) getLayoutInflater().inflate(LAYOUT_RES_ID, parent, false);
            final String imagePath = getItem(position);
            bindImage(imagePath, view);
            return view;
        }

        private void bindImage(String imagePath, ImageView imageView) {
            try {
                Bitmap bitmap = null;
                if (isOptimized) {
                    bitmap = mMap.get(imagePath);
                    if (bitmap == null) {
                        bitmap = decodeSampledBitmapFromResource(getContext(), imagePath, 100, 100);
                        mMap.put(imagePath, bitmap);
                    }
                } else {
                    bitmap = BitmapFactory.decodeStream(getAssets().open(imagePath));
                }
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                imageView.setImageResource(R.drawable.ic_launcher);
            }
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Context context, String path,
                                                         int reqWidth, int reqHeight) throws IOException {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getAssets().open(path), null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(context.getAssets().open(path), null, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}

