package com.epam.jmp.concurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epam.jmp.concurrency.Constants;
import com.epam.jmp.concurrency.R;
import com.epam.jmp.concurrency.comparator.ComparatorTopListing;
import com.epam.jmp.concurrency.data.Channel;

import java.util.List;

/**
 * Created by sergey on 27.10.2014.
 */
public class AdapterTopChannels extends ArrayAdapter<Channel> {

    public AdapterTopChannels(Context context, List<Channel> list) {
        super(context, R.layout.adapter_top_channels, list);
    }

    @Override
    public int getCount() {
        return Constants.COUNT_TOP_CHANNELS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_top_channels, parent, false);
        }
        Channel item = getItem(position);
        ((TextView) convertView.findViewById(R.id.ChannelTitle)).setText(item.getTitle());
        int stars = ComparatorTopListing.getAvg(item.getListings());
        ((TextView) convertView.findViewById(R.id.Rating)).setText("ratio: " + stars);
        return convertView;
    }
}
