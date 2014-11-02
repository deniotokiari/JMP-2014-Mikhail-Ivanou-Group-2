package com.epam.jmp.concurrency.data;

/**
 * Created by sergey on 27.10.2014.
 */
public class Channel {
    private final int mId;
    private final String mTitle;
    private final String mDesc;
    private final String mImage;

    public Channel(int id, String title, String desc, String image) {
        mId = id;
        mTitle = title;
        mDesc = desc;
        mImage = image;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDesc() {
        return mDesc;
    }

    public String getImage() {
        return mImage;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
