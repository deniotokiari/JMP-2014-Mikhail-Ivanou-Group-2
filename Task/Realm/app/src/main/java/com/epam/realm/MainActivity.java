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
    private DataProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProvider = new DataProvider(this);

        mRealm = new RealmImpl(this);
        mXcore = new XcoreImpl(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                createUnits(mProvider.getFeedUnits());
                createProjects(mProvider.getFeedProjects());
                createEmployees(mProvider.getFeedEmployees());

                findEmployeeById(mProvider.getFeedEmployees().length());
                findProjectById(mProvider.getFeedProjects().length());
                findUnitById(mProvider.getFeedUnits().length());

                deleteEmployeeById(mProvider.getFeedEmployees().length());
                deleteProjectById(mProvider.getFeedProjects().length());
                deleteUnitById(mProvider.getFeedUnits().length());

                mXcore.start();
            }
        }).start();
    }

    private void deleteEmployeeById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.deleteEmployeeById(i);
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "deleteEmployeeById avg = " + t);
    }

    private void deleteProjectById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.deleteProjectById(i);
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "deleteProjectById avg = " + t);
    }

    private void deleteUnitById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.deleteUnitById(i);
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "deleteUnitById avg = " + t);
    }

    private void findEmployeeById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.findEmployeeById(i);
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findEmployeeById avg = " + t);
    }

    private void findProjectById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.findProjectById(i);
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findProjectById avg = " + t);
    }

    private void findUnitById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.findUnitById(i);
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findUnitById avg = " + t);
    }

    private void createEmployees(JSONArray feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length();
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                mRealm.createEmployee(feed.getJSONObject(i));
            }
            long t = (System.currentTimeMillis() - start) / size;
            mRealm.commitTransaction();
            Log.e("RESULT REALM", "createEmployees avg = " + t);
        } catch (Exception e) {
        }
    }

    private void createProjects(JSONArray feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length();
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                mRealm.createProject(feed.getJSONObject(i));
            }
            long t = (System.currentTimeMillis() - start) / size;
            mRealm.commitTransaction();
            Log.e("RESULT REALM", "createProjects avg = " + t);
        } catch (Exception e) {
        }
    }

    private void createUnits(JSONArray feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length();
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                mRealm.createUnit(feed.getJSONObject(i));
            }
            long t = (System.currentTimeMillis() - start) / size;
            mRealm.commitTransaction();
            Log.e("RESULT REALM", "createUnits avg = " + t);
        } catch (Exception e) {
        }
    }

}
