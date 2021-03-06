package com.epam.jmp.concurrency;

/**
 * Created by sergey on 27.10.2014.
 */
public class Constants {
    public static final long TIME_SCREEN_UPDATE = 1000L;

    public static final int TIME_GEN_CHANNELS = 1;
    public static final int TIME_GEN_LISTINGS = 1;
    public static final int TIME_GEN_RATIOS = 1;

    public static final int COUNT_CHANNELS = 150;
    public static final int COUNT_LISTINGS_PER_CHANNELS = 10;
    public static final int COUNT_LISTINGS = COUNT_CHANNELS * COUNT_LISTINGS_PER_CHANNELS;

    public static final int COUNT_TOP_LISTINGS = 10;
    public static final int COUNT_TOP_CHANNELS = 3;

    public static final int MAX_RATIO = 100;

    public static final int MAX = COUNT_LISTINGS;
    public static final int MAX_KOEF = 1;
}
