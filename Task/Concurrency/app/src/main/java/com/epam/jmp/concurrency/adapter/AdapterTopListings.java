package com.epam.jmp.concurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epam.jmp.concurrency.Constants;
import com.epam.jmp.concurrency.R;
import com.epam.jmp.concurrency.data.Listing;

import java.util.List;

/**
 * Created by sergey on 27.10.2014.
 */
public class AdapterTopListings extends ArrayAdapter<Listing> {

    public AdapterTopListings(Context context, List<Listing> list) {
        super(context, R.layout.adapter_top_listings, list);
    }

    @Override
    public int getCount() {
        return Constants.COUNT_TOP_LISTINGS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_top_listings, parent, false);
        }
        Listing item = getItem(position);
        ((TextView) convertView.findViewById(R.id.ChannelTitle)).setText(item.getChannel().getTitle());
        ((TextView) convertView.findViewById(R.id.ListingTitle)).setText(item.getTitle());
        return convertView;
    }
}
