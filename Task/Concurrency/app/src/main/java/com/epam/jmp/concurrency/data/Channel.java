package com.epam.jmp.concurrency.data;

import java.util.Collections;
import java.util.List;

/**
 * Created by sergey on 27.10.2014.
 */
public class Channel {
    private final int mId;
    private final String mTitle;
    private final String mDesc;
    private final String mImage;
    private List<Listing> mListings = Collections.EMPTY_LIST;

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

    public List<Listing> getListings() {
        return mListings;
    }

    public void setListings(List<Listing> list) {
        mListings = list;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
