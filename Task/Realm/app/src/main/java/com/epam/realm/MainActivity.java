package com.epam.realm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.epam.realm.realm.RealmImpl;
import com.epam.realm.realm.model.Employee;
import com.epam.realm.realm.model.Project;
import com.epam.realm.realm.model.Unit;
import com.epam.realm.xcore.XcoreImpl;

import org.json.JSONArray;


public class MainActivity extends Activity {

    private RealmImpl mRealm;
    private XcoreImpl mXcore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = new RealmImpl(this);
        mXcore = new XcoreImpl(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mRealm.start();

                mXcore.start();
            }
        }).start();
    }

}
