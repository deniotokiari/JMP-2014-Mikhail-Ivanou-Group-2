package com.epam.vkfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sergey on 11.01.2015.
 */
public abstract class ListAdapter<T> extends ArrayAdapter<T> {

    private int mResource;
    private List<T> items;

    public ListAdapter(Context context, int resource) {
        super(context, resource, new ArrayList<T>());
        mResource = resource;
        items = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(mResource, null);
        }

        T item = getItem(position);

        if (item != null) {
            bindView(v, item);
        }

        return v;

    }

    @Override
    public T getItem(int position) {
        return items != null ? items.get(position) : super.getItem(position);
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : super.getCount();
    }

    protected abstract void bindView(View v, T item);

    public void setData(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setData(T[] items) {
        setData(Arrays.asList(items));
    }
}
