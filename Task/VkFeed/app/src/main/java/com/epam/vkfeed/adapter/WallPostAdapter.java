package com.epam.vkfeed.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.epam.vkfeed.R;
import com.epam.vkfeed.model.WallPost;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sergey on 11.01.2015.
 */
public class WallPostAdapter extends ListAdapter<WallPost> {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy в HH:mm");

    public WallPostAdapter(Context context) {
        super(context, R.layout.item_wall_post);
    }

    @Override
    protected void bindView(View v, WallPost item) {
        setText(v, R.id.text, item.getText());

        Date date = new Date();
        date.setTime(item.getDate() * 1000);
        setText(v, R.id.date, dateFormat.format(date));

        v.setTag(item);
    }

    private void setText(View v, int id, String value) {
        TextView tv = (TextView) v.findViewById(id);
        tv.setText(value);
    }
}
