package com.epam.jmp.concurrency.data;

/**
 * Created by sergey on 27.10.2014.
 */
public class Ratio {
    private int mListingId;
    private int mValue;

    public Ratio(int listingId, int value) {
        mListingId = listingId;
        mValue = value;
    }

    public int getListingId() {
        return mListingId;
    }

    public int getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
