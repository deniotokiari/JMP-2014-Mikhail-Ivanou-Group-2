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
import java.util.concurrent.ConcurrentHashMap;
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

    private ConcurrentHashMap<Integer, Channel> mChannels = new ConcurrentHashMap<Integer, Channel>();

    private Runner mRunnerChannels;
    private Runner mRunnerListings;
    private Runner mRunnerRatios;

    private Random mRandom = new Random();

    private final Object lock = new Object();

    private final Runnable mGenChannels = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                Log.d(TAG, "[START] ChannelsGen");
                mChannels.clear();
                int id;
                String title, desc, image;
                Channel channel;
                int countChannels = Constants.COUNT_CHANNELS - mRandom.nextInt(10 + 1);
                for (int i = 0; i < countChannels; i++) {
                    id = mRandom.nextInt();
                    title = DummyData.CHANNEL_TITLES[mRandom.nextInt(DummyData.CHANNEL_TITLES.length)];
                    desc = DummyData.CHANNEL_DESC[mRandom.nextInt(DummyData.CHANNEL_DESC.length)];
                    image = DummyData.IMAGES[mRandom.nextInt(DummyData.IMAGES.length)];
                    channel = new Channel(id, title, desc, image);
                    mChannels.put(i, channel);
                }
                mGenListings.run();
                Log.d(TAG, "[END] ChannelsGen");
            }
        }
    };

    private final Runnable mGenListings = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                Log.d(TAG, "[START] ListingsGen");
                int id;
                String title;
                Listing listing;
                int sizeChannels = mChannels.size();
                for (int i = 0; i < sizeChannels; i++) {
                    Channel channel = mChannels.get(i);
                    List<Listing> listings = new CopyOnWriteArrayList<Listing>();
                    int countListingsPerChannels = Constants.COUNT_LISTINGS_PER_CHANNELS - mRandom.nextInt(10 + 1);
                    for (int j = 0; j < countListingsPerChannels; j++) {
                        id = mRandom.nextInt(Integer.MAX_VALUE);
                        title = DummyData.LISTING_TITLES[mRandom.nextInt(DummyData.LISTING_TITLES.length)];
                        listing = new Listing(id, channel, title);
                        listings.add(listing);
                    }
                    channel.setListings(listings);
                }
                mGenRatios.run();
                Log.d(TAG, "[END] ListingsGen");
            }
        }
    };

    private final Runnable mGenRatios = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
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
        synchronized (lock) {
            return Collections.list(mChannels.elements());
        }
    }

    private static ComparatorTopChannels comparatorTopChannels = new ComparatorTopChannels();
    private static ComparatorTopListing comparatorTopListings = new ComparatorTopListing();

    public List<Channel> getTopChannels() {
        synchronized (lock) {
            List<Channel> topChannels = Collections.list(mChannels.elements());
            Collections.sort(topChannels, comparatorTopChannels);
            return topChannels;
        }
    }

    public List<Listing> getTopListings() {
        synchronized (lock) {
            List<Listing> topListings = new ArrayList<Listing>();
            int size = mChannels.size();
            for (int i = 0; i < size; i++) {
                topListings.addAll(mChannels.get(i).getListings());
            }
            Collections.sort(topListings, comparatorTopListings);
            return topListings;
        }
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
