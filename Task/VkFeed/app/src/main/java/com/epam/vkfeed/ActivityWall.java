package com.epam.vkfeed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.epam.vkfeed.adapter.WallPostAdapter;
import com.epam.vkfeed.model.WallPost;
import com.epam.vkfeed.task.HttpRequestTask;


public class ActivityWall extends ActionBarActivity {

    public static final String ID = "com.epam.vkfeed.extra.ActivityWall.ID";

    public static final String TAG = "ActivityWall";

    private View progress;
    private WallPostAdapter adapter;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);

        final String idPage = getIntent().getStringExtra(ID);

        alertDialogBuilder = new AlertDialog.Builder(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter = new WallPostAdapter(getApplicationContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WallPost tag = (WallPost) view.getTag();

                alertDialogBuilder
                        .setMessage(tag.getText())
                        .setCancelable(false)
                        .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                alertDialog.dismiss();
                            }
                        });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        progress = findViewById(R.id.progressBar);

        new HttpRequestTask<WallPost>() {

            @Override
            protected String getUrl() {
                return "https://api.vk.com/method/wall.get?owner_id=-" + idPage + "&v=5.27";
            }

            @Override
            protected Class getModelClass() {
                return WallPost.class;
            }

            @Override
            protected void showLoadingProgressDialog() {
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            protected void dismissProgressDialog() {
                progress.setVisibility(View.GONE);
            }

            @Override
            protected void refreshData(WallPost result) {
                if (result == null) {
                    finish();
                    return;
                }
                WallPost response = result.getResponse();
                if (response == null) {
                    finish();
                    return;
                }
                WallPost[] items = response.getItems();
                if (items.length == 0) {
                    finish();
                    return;
                }
                adapter.setData(items);
            }
        }.execute();
    }

}
