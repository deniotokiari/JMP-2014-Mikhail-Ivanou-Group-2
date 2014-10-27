package com.epam.jmp.concurrency;

import android.util.Log;

import com.epam.jmp.concurrency.comparator.ComparatorTopChannels;
import com.epam.jmp.concurrency.comparator.ComparatorTopListing;
import com.epam.jmp.concurrency.data.Channel;
import com.epam.jmp.concurrency.data.DummyData;
import com.epam.jmp.concurrency.data.Listing;
import com.epam.jmp.concurrency.data.Ratio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by sergey on 26.10.2014.
 */
public class StoreHelper {

    public static final String TAG = "Concurrency:Helper";

    private List<Channel> mChannels = new CopyOnWriteArrayList<Channel>();

    private Runner mRunnerChannels;
    private Runner mRunnerListings;
    private Runner mRunnerRatios;

    private Random mRandom = new Random();

    private final Runnable mGenChannels = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "[START] ChannelsGen");
            int id;
            String title, desc, image;
            Channel channel;
            for (int i = 0; i < Constants.COUNT_CHANNELS; i++) {
                id = mRandom.nextInt();
                title = DummyData.CHANNEL_TITLES[mRandom.nextInt(DummyData.CHANNEL_TITLES.length)];
                desc = DummyData.CHANNEL_DESC[mRandom.nextInt(DummyData.CHANNEL_DESC.length)];
                image = DummyData.IMAGES[mRandom.nextInt(DummyData.IMAGES.length)];
                channel = new Channel(id, title, desc, image);
                mChannels.add(i, channel);
            }
            Log.d(TAG, "[END] ChannelsGen");
        }
    };

    private final Runnable mGenListings = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "[START] ListingsGen");
            int id;
            String title;
            Listing listing;
            int sizeChannels = mChannels.size();
            for (int i = 0; i < sizeChannels; i++) {
                Channel channel = mChannels.get(i);
                List<Listing> listings = new CopyOnWriteArrayList<Listing>();
                for (int j = 0; j < Constants.COUNT_LISTINGS_PER_CHANNELS; j++) {
                    id = mRandom.nextInt(Constants.COUNT_LISTINGS);
                    title = DummyData.CHANNEL_DESC[mRandom.nextInt(DummyData.CHANNEL_DESC.length)];
                    listing = new Listing(id, channel, title);
                    listings.add(listing);
                }
                channel.setListings(listings);
            }
            Log.d(TAG, "[END] ListingsGen");
        }
    };

    private final Runnable mGenRatios = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "[START] RatiosGen");
            int sizeChannels = mChannels.size();
            for (int i = 0; i < sizeChannels; i++) {
                Channel channel = mChannels.get(i);
                List<Listing> listings = channel.getListings();
                int sizeListings = listings.size();
                for (int j = 0; j < sizeListings; j++) {
                    Listing listing = listings.get(j);
                    Ratio ratio = new Ratio(listing.getId(), mRandom.nextInt(Constants.MAX_RATIO));
                    listing.setRatio(ratio);
                }
                channel.setListings(listings);
            }
            Log.d(TAG, "[END] RatiosGen");
        }
    };

    public StoreHelper() {
        Log.d(TAG, "Create the new StoreHelper");
        init();
    }

    private void init() {
        Log.d(TAG, "[INIT] StoreHelper");
        mRunnerChannels = new Runner(mGenChannels, Constants.TIME_GEN_CHANNELS);
        mRunnerListings = new Runner(mGenListings, Constants.TIME_GEN_LISTINGS);
        mRunnerRatios = new Runner(mGenRatios, Constants.TIME_GEN_RATIOS);
    }

    public void start() {
        Log.d(TAG, "[START] StoreHelper");
        mRunnerChannels.start();
        mRunnerListings.start();
        mRunnerRatios.start();
    }

    public void stop() {
        Log.d(TAG, "[STOP] StoreHelper");
        mRunnerChannels.stop();
        mRunnerListings.stop();
        mRunnerRatios.stop();
    }

    public List<Channel> getChannels() {
        return mChannels;
    }

    public List<Channel> getTopChannels() {
        List<Channel> topListings = new ArrayList<Channel>();
        topListings.addAll(mChannels);
        Collections.sort(topListings, new ComparatorTopChannels());
        return topListings;
    }

    public List<Listing> getTopListings() {
        List<Listing> topListings = new ArrayList<Listing>();
        int size = mChannels.size();
        for (int i = 0; i < size; i++) {
            topListings.addAll(mChannels.get(i).getListings());
        }
        Collections.sort(topListings, new ComparatorTopListing());
        return topListings;
    }

    private class Runner {
        private final Runnable mRunnable;
        private final long mSeconds;
        private final ScheduledExecutorService mService;
        private ScheduledFuture mScheduled;

        public Runner(Runnable runnable, long seconds) {
            if (runnable == null || seconds <= 0) {
                throw new IllegalArgumentException("runnable == null || seconds <= 0");
            }
            mService = Executors.newSingleThreadScheduledExecutor();
            mRunnable = runnable;
            mSeconds = seconds;
        }

        public void start() {
            mScheduled = mService.scheduleWithFixedDelay(mRunnable, 0, mSeconds, TimeUnit.SECONDS);
        }

        public void stop() {
            mScheduled.cancel(true);
        }
    }
}
