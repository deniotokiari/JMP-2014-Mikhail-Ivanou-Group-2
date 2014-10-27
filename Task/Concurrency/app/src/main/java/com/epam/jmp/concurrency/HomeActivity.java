package com.epam.jmp.concurrency;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.epam.jmp.concurrency.adapter.AdapterChannels;
import com.epam.jmp.concurrency.adapter.AdapterTopChannels;
import com.epam.jmp.concurrency.adapter.AdapterTopListings;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class HomeActivity extends Activity {

    public static final String TAG = "Concurrency:Activity";

    private final StoreHelper mStoreHelper = new StoreHelper();
    private Handler mHandler;

    private ListView mChannels;
    private AdapterChannels mAdapterChannels;

    private ListView mTopChannels;
    private AdapterTopChannels mAdapterTopChannels;

    private ListView mTopListing;
    private AdapterTopListings mAdapterTopListings;

    private Context mContext;

    private final Runnable mRunnableUpdate = new Runnable() {
        @Override
        public void run() {
                Log.d(TAG, "mRunnableUpdate");
                mAdapterChannels = new AdapterChannels(mContext, mStoreHelper.getChannels());
                mChannels.setAdapter(mAdapterChannels);

                mAdapterTopChannels = new AdapterTopChannels(mContext, mStoreHelper.getTopChannels());
                mTopChannels.setAdapter(mAdapterTopChannels);

                mAdapterTopListings = new AdapterTopListings(mContext, mStoreHelper.getTopListings());
                mTopListing.setAdapter(mAdapterTopListings);

                mHandler.postDelayed(mRunnableUpdate, Constants.TIME_SCREEN_UPDATE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHandler = new Handler(getMainLooper());
        mChannels = (ListView) findViewById(R.id.Channels);
        mTopChannels = (ListView) findViewById(R.id.TopChannels);
        mTopListing = (ListView) findViewById(R.id.TopListing);
        mContext = this;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStoreHelper.start();
        mHandler.postDelayed(mRunnableUpdate, Constants.TIME_SCREEN_UPDATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mStoreHelper.stop();
        mHandler.removeCallbacks(mRunnableUpdate);
    }
}
