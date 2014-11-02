package com.epam.jmp.concurrency.data;

/**
 * Created by sergey on 27.10.2014.
 */
public class Listing {
    private final int mId;
    private final int mChannelId;
    private final String mTitle;
    private Channel mChannel;
    private Ratio mRatio;

    public Listing(int id, int channelId, String title) {
        mId = id;
        mChannelId = channelId;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public int getChannelId() {
        return mChannelId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Channel getChannel() {
        return mChannel;
    }

    public void setChannel(Channel channel) {
        mChannel = channel;
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
