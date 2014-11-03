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

import java.util.Collections;

public class HomeActivity extends Activity {

    public static final String TAG = "Activity";

    private final StoreHelper mStoreHelper = new StoreHelper();
    private Handler mHandler;

    private ListView mListChannels;
    private AdapterChannels mAdapterChannels;

    private ListView mListTopChannels;
    private AdapterTopChannels mAdapterTopChannels;

    private ListView mListTopListing;
    private AdapterTopListings mAdapterTopListings;

    private Context mContext;

    private final Runnable mRunnableUpdate = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "mRunnableUpdate");

            mAdapterChannels.setData(mStoreHelper.getAllChannels());
            mAdapterChannels.notifyDataSetChanged();

            mAdapterTopChannels.setData(mStoreHelper.getTopChannels());
            mAdapterTopChannels.notifyDataSetChanged();

            mAdapterTopListings.setData(mStoreHelper.getTopListings());
            mAdapterTopListings.notifyDataSetChanged();

            mHandler.postDelayed(mRunnableUpdate, Constants.TIME_SCREEN_UPDATE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHandler = new Handler(getMainLooper());
        mListChannels = (ListView) findViewById(R.id.Channels);
        mListTopChannels = (ListView) findViewById(R.id.TopChannels);
        mListTopListing = (ListView) findViewById(R.id.TopListing);
        mContext = this;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);

        mAdapterChannels = new AdapterChannels(mContext, Collections.EMPTY_LIST);
        mListChannels.setAdapter(mAdapterChannels);

        mAdapterTopChannels = new AdapterTopChannels(mContext, Collections.EMPTY_LIST);
        mListTopChannels.setAdapter(mAdapterTopChannels);

        mAdapterTopListings = new AdapterTopListings(mContext, Collections.EMPTY_LIST);
        mListTopListing.setAdapter(mAdapterTopListings);
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
