package com.epam.jmp.concurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epam.jmp.concurrency.R;
import com.epam.jmp.concurrency.data.Channel;

import java.util.List;

/**
 * Created by sergey on 27.10.2014.
 */
public class AdapterChannels extends ArrayAdapter<Channel> {

    private List<Channel> mData;

    public AdapterChannels(Context context, List<Channel> data) {
        super(context, R.layout.adapter_channels);
        mData = data;
    }

    public void setData(List<Channel> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Channel getItem(int position) {
        return mData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_channels, parent, false);
        }
        Channel item = getItem(position);
        //ImageLoader.getInstance().displayImage(item.getImage(), (android.widget.ImageView) convertView.findViewById(R.id.Logo));
        ((TextView) convertView.findViewById(R.id.ChannelTitle)).setText(item.getTitle());
        ((TextView) convertView.findViewById(R.id.ChannelDesc)).setText(item.getDesc());
        //((TextView) convertView.findViewById(R.id.ChannelListings)).setText("size: "+item.getListings().size());
        return convertView;
    }
}
