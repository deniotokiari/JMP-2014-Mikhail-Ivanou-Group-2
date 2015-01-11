package com.epam.vkfeed.model;

/**
 * Created by sergey on 11.01.2015.
 */
public class WallPost {
    WallPost response;
    WallPost[] items;
    int id;
    long date;
    String text;

    public WallPost(WallPost response, WallPost[] items, int id, long date, String text) {
        this.response = response;
        this.items = items;
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public WallPost getResponse() {
        return response;
    }

    public WallPost[] getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
