package com.epam.jmp.garbagecollection;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class SampleActivity extends ListActivity {

    public static final String EXTRA_FILES = "com.epam.jmp.garbagecollection.EXTRA_FILES";
    public static final String EXTRA_IS_OPTIMIZED = "com.epam.jmp.garbagecollection.EXTRA_IS_OPTIMIZED";

    private static final int LAYOUT_RES_ID = R.layout.adapter_sample;

    public static final boolean DEFAULT_IS_OPTIMIZED = false;
    private LruCache<String, Bitmap> mMemoryCache;
    private TextView mInfo;
    private Handler mHandler;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mInfo.setText("Used memory: " + getUsedMemorySize() + "kb of " + (Runtime.getRuntime().maxMemory() / 1024) + "kb");
            mHandler.postDelayed(this, 1000L);
        }
    };

    public static long getUsedMemorySize() {
        long freeSize = 0L;
        long totalSize = 0L;
        long usedSize = -1L;
        try {
            Runtime info = Runtime.getRuntime();
            freeSize = info.freeMemory();
            totalSize = info.totalMemory();
            usedSize = totalSize - freeSize;
        } catch (Exception e) {
        }
        return usedSize / 1024;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.gc();
        mHandler = new Handler(getMainLooper());
        mHandler.removeCallbacks(mRunnable);
        mHandler.post(mRunnable);
        setContentView(R.layout.activity_sample);

        final Intent intent = getIntent();
        String[] files = null;
        boolean isOptimized = DEFAULT_IS_OPTIMIZED;
        if (intent != null) {
            files = intent.getStringArrayExtra(EXTRA_FILES);
            isOptimized = intent.getBooleanExtra(EXTRA_IS_OPTIMIZED, DEFAULT_IS_OPTIMIZED);
            // Get max available VM memory, exceeding this amount will throw an
            // OutOfMemory exception. Stored in kilobytes as LruCache takes an
            // int in its constructor.
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;

            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // The cache size will be measured in kilobytes rather than
                    // number of items.
                    return bitmap.getByteCount() / 1024;
                }
            };
        }
        setListAdapter(new ImageAdapter(getApplicationContext(), files, isOptimized));
        mInfo = (TextView) findViewById(R.id.info);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }

    private class ImageAdapter extends ArrayAdapter<String> {

        private final boolean isOptimized;

        public ImageAdapter(Context context, String[] files, boolean isOptimized) {
            super(context, LAYOUT_RES_ID, files);
            this.isOptimized = isOptimized;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = (ImageView) getLayoutInflater().inflate(LAYOUT_RES_ID, parent, false);
            }
            final String imagePath = getItem(position);
            bindImage(imagePath, (ImageView) convertView);
            return convertView;
        }

        private void bindImage(final String imagePath, final ImageView imageView) {
            if (isOptimized) {
                final Bitmap bitmapFromMemCache = getBitmapFromMemCache(imagePath);
                if (bitmapFromMemCache != null) {
                    imageView.setImageBitmap(bitmapFromMemCache);
                } else {
                    imageView.setImageResource(R.drawable.ic_launcher);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Bitmap bitmap = decodeSampledBitmapFromResource(getContext(), imagePath, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageView.setImageBitmap(bitmap);
                                    }
                                });

                                addBitmapToMemoryCache(imagePath, bitmap);
                            } catch (Exception e) {

                            }
                        }
                    }).start();
                }
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(imagePath));
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        } catch (IOException e) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageResource(R.drawable.ic_launcher);
                                }
                            });
                        }
                    }
                }).start();
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

