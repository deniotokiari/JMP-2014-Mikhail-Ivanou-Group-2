package com.epam.jmp.concurrency.data;

import com.epam.jmp.concurrency.Constants;

/**
 * Created by sergey on 27.10.2014.
 */
public class Listing {
    private final int mId;
    private final Channel mChannel;
    private final String mTitle;
    private Ratio mRatio;

    public Listing(int id, Channel channel, String title) {
        mId = id;
        mChannel = channel;
        mTitle = title;
        mRatio = new Ratio(mId, Constants.DEF_RATIO);
    }

    public int getId() {
        return mId;
    }

    public Channel getChannel() {
        return mChannel;
    }

    public String getTitle() {
        return mTitle;
    }

    public Ratio getRatio() {
        return mRatio;
    }

    public void setRatio(Ratio ratio) {
        mRatio = ratio;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
