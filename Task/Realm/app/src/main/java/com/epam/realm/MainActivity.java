package com.epam.realm;

import android.app.Activity;
import android.os.Bundle;

import com.epam.realm.realm.RealmImpl;
import com.epam.realm.xcore.XcoreImpl;


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
