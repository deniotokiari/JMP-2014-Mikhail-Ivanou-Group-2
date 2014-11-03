package com.epam.jmp.concurrency;

import android.util.Log;

import com.epam.jmp.concurrency.data.Channel;
import com.epam.jmp.concurrency.data.DummyData;
import com.epam.jmp.concurrency.data.Listing;
import com.epam.jmp.concurrency.data.Ratio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by sergey on 26.10.2014.
 */
public class StoreHelper {

    public static final String TAG = "Helper";

    //<ChannelId, Channel>
    private ConcurrentHashMap<Integer, Channel> mChannels =
            new ConcurrentHashMap<Integer, Channel>(Constants.COUNT_CHANNELS);

    //<ListingId, Listing>
    private ConcurrentHashMap<Integer, Listing> mListings =
            new ConcurrentHashMap<Integer, Listing>(Constants.COUNT_LISTINGS);

    //<ListingId, Ratio>
    private ConcurrentHashMap<Integer, Ratio> mRatios =
            new ConcurrentHashMap<Integer, Ratio>(Constants.COUNT_LISTINGS);

    private Runner mRunnerChannels;
    private Runner mRunnerListings;
    private Runner mRunnerRatios;

    private static Random mRandom = new Random();

    private static int nextInt(int n) {
        return mRandom.nextInt(n);
    }

    private static int nextInt() {
        return nextInt(Constants.MAX);
    }

    private static int genRandom(int val) {
        return val - nextInt(Constants.MAX_KOEF);
    }

    private final Runnable mGenChannels = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "[START] ChannelsGen");
            mChannels.clear();
            Integer id;
            String title, desc, image;
            int countChannels = genRandom(Constants.COUNT_CHANNELS);
            for (int i = 0; i < countChannels; i++) {
                id = Integer.valueOf(nextInt());
                title = "Channel title " + i;
                desc = "Channel description " + i;
                image = DummyData.IMAGES[nextInt(DummyData.IMAGES.length)];
                mChannels.put(id, new Channel(id, title, desc, image));
            }
            Log.d(TAG, "[END] ChannelsGen " + countChannels);
        }
    };

    private final Runnable mGenListings = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "[START] ListingsGen");
            mListings.clear();
            Integer listingId, channelId;
            String title;
            int countListings = genRandom(Constants.COUNT_LISTINGS);
            for (int i = 0; i < countListings; i++) {
                listingId = Integer.valueOf(nextInt());
                channelId = Integer.valueOf(nextInt());
                title = "Listing title " + i;
                mListings.put(listingId, new Listing(listingId, channelId, title));
            }
            Log.d(TAG, "[END] ListingsGen " + countListings);
        }
    };

    private final Runnable mGenRatios = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "[START] RatiosGen");
            mRatios.clear();
            Integer listingId;
            int countRatios = genRandom(Constants.COUNT_LISTINGS);
            for (int i = 0; i < countRatios; i++) {
                listingId = Integer.valueOf(nextInt());
                Ratio ratio = new Ratio(listingId, nextInt(Constants.MAX_RATIO));
                mRatios.put(listingId, ratio);
            }
            Log.d(TAG, "[END] RatiosGen " + countRatios);
        }
    };


    public void setChannels(ConcurrentHashMap<Integer, Channel> channels) {
        mChannels = channels;
    }

    public void setListings(ConcurrentHashMap<Integer, Listing> listings) {
        mListings = listings;
    }

    public void setRatios(ConcurrentHashMap<Integer, Ratio> ratios) {
        mRatios = ratios;
    }

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

    public List<Channel> getAllChannels() {
        return Collections.list(mChannels.elements());
    }

    public List<Channel> getTopChannels() {
        List<Ratio> ratios = Collections.list(mRatios.elements());
        Object[] array = ratios.toArray();
        Arrays.sort(array);

        List<Channel> topChannels = new ArrayList<Channel>(Constants.COUNT_TOP_CHANNELS);
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            Ratio ratio = (Ratio) array[i];
            if (ratio == null) {
                continue;
            }
            int listingId = ratio.getListingId();
            Listing listing = mListings.get(listingId);
            if (listing == null) {
                continue;
            }
            int channelId = listing.getChannelId();
            Channel channel = mChannels.get(channelId);
            if (channel == null) {
                continue;
            }
            if (j == 0) {
                topChannels.add(channel);
                j++;
            } else if (j == Constants.COUNT_TOP_CHANNELS) {
                break;
            } else if (topChannels.get(j - 1).getId() != channelId) {
                topChannels.add(channel);
                j++;
            }
        }
        Log.d(TAG, "topChannels " + topChannels.size());
        return topChannels;
    }

    public List<Listing> getTopListings() {
        List<Ratio> ratios = Collections.list(mRatios.elements());
        Object[] array = ratios.toArray();
        Arrays.sort(array);

        List<Listing> topListings = new ArrayList<Listing>(Constants.COUNT_TOP_LISTINGS);
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            Ratio ratio = (Ratio) array[i];
            if (ratio == null) {
                continue;
            }
            int listingId = ratio.getListingId();
            Listing listing = mListings.get(listingId);
            if (listing == null) {
                continue;
            }
            Channel channel = mChannels.get(listing.getChannelId());
            if (channel == null) {
                continue;
            }
            if (j == Constants.COUNT_TOP_LISTINGS) {
                break;
            } else {
                listing.setChannel(channel);
                listing.setRatio(ratio);
                topListings.add(listing);
                j++;
            }
        }
        Log.d(TAG, "topListings " + topListings.size());
        return topListings;
    }

    public ConcurrentHashMap<Integer, Channel> getChannels() {
        return mChannels;
    }

    public ConcurrentHashMap<Integer, Listing> getListings() {
        return mListings;
    }

    public ConcurrentHashMap<Integer, Ratio> getRatios() {
        return mRatios;
    }

    private static class Runner {
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
