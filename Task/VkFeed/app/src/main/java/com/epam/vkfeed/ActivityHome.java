package com.epam.vkfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;


public class ActivityHome extends ActionBarActivity implements View.OnClickListener {

    public static final String ID_TUT = "15591739";
    public static final String ID_ONLINER = "17406378";
    public static final String ID_GRODNO = "62644071";

    private static final String TAG = "ActivityHome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.bTut).setOnClickListener(this);
        findViewById(R.id.bGrodno).setOnClickListener(this);
        findViewById(R.id.bOnliner).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String idPage = ID_TUT;
        switch (id) {
            case R.id.bGrodno:
                idPage = ID_GRODNO;
                break;
            case R.id.bOnliner:
                idPage = ID_ONLINER;
                break;
            case R.id.bTut:
                idPage = ID_TUT;
                break;
        }
        Intent intent = new Intent(getApplicationContext(), ActivityWall.class);
        intent.putExtra(ActivityWall.ID, idPage);
        startActivity(intent);
    }
}
