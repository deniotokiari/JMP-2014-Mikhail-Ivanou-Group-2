package com.epam.jmp.concurrency.data;

/**
 * Created by sergey on 27.10.2014.
 */
public class Ratio implements Comparable<Ratio> {
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

    @Override
    public int compareTo(Ratio another) {
        int rhsValue = another.getValue();
        if (mValue == rhsValue) {
            return 0;
        }
        if (mValue < rhsValue) {
            return 1;
        }
        return -1;
    }
}
