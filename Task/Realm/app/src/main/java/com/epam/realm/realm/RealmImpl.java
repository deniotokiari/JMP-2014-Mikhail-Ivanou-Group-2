package com.epam.realm.realm;

import android.content.Context;
import android.util.Log;

import com.epam.realm.DataProvider;
import com.epam.realm.realm.model.Employee;
import com.epam.realm.realm.model.Project;
import com.epam.realm.realm.model.RealmEmployee;
import com.epam.realm.realm.model.RealmProject;
import com.epam.realm.realm.model.RealmUnit;
import com.epam.realm.realm.model.Unit;

import java.io.File;

import io.realm.Realm;

/**
 * Created by sergey on 03.12.2014.
 */
public class RealmImpl {

    private static final String TAG = "RealmImpl";
    private final Context mContext;
    private Realm mRealm;
    private DataProvider mProvider;

    public RealmImpl(Context context) {
        mContext = context;
        mProvider = new DataProvider(mContext);

        File file = mContext.getFileStreamPath(Realm.DEFAULT_REALM_NAME);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "delete db");
        }
        mRealm = Realm.getInstance(mContext);
        Log.d(TAG, "create realm instance");
    }

    public void start() {
        testCreateUnits(mProvider.getFeedUnits());
        testCreateProjects(mProvider.getFeedProjects());
        testCreateEmployees(mProvider.getFeedEmployees());

        testFindEmployeeById(mProvider.getFeedEmployees().length);
        testFindProjectById(mProvider.getFeedProjects().length);
        testFindUnitById(mProvider.getFeedUnits().length);
    }

    private void testCreateUnits(Unit[] feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length;
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                createUnit(feed[i]);
            }
            long t = (System.currentTimeMillis() - start) / size;
            Log.e("RESULT REALM", "[read] createUnits avg = " + t);
            start = System.currentTimeMillis();
            mRealm.commitTransaction();
            t = (System.currentTimeMillis() - start) / size;
            Log.e("RESULT REALM", "createUnits avg = " + t);
        } catch (Exception e) {
        }
    }

    private RealmUnit createUnit(Unit obj) {
        RealmUnit realmUnit = mRealm.createObject(RealmUnit.class);
        realmUnit.setId(obj.getId());
        realmUnit.setTitle(obj.getTitle());
        return realmUnit;
    }

    private void testCreateProjects(Project[] feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length;
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                createProject(feed[i]);
            }
            long t = (System.currentTimeMillis() - start) / size;
            Log.e("RESULT REALM", "[read] createProjects avg = " + t);
            start = System.currentTimeMillis();
            mRealm.commitTransaction();
            t = (System.currentTimeMillis() - start) / size;
            Log.e("RESULT REALM", "createProjects avg = " + t);
        } catch (Exception e) {
        }
    }

    private RealmProject createProject(Project obj) {
        RealmProject realmProject = mRealm.createObject(RealmProject.class);
        realmProject.setId(obj.getId());
        realmProject.setName(obj.getName());
        realmProject.setAbout(obj.getAbout());
        return realmProject;
    }

    private void testCreateEmployees(Employee[] feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length;
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                createEmployee(feed[i]);
            }
            long t = (System.currentTimeMillis() - start) / size;
            Log.e("RESULT REALM", "[read] createEmployees avg = " + t);
            start = System.currentTimeMillis();
            mRealm.commitTransaction();
            t = (System.currentTimeMillis() - start) / size;
            Log.e("RESULT REALM", "createEmployees avg = " + t);
        } catch (Exception e) {
        }
    }

    private RealmEmployee createEmployee(Employee obj) {
        RealmEmployee realmEmployee = mRealm.createObject(RealmEmployee.class);
        realmEmployee.setId(obj.getId());
        realmEmployee.setName(obj.getName());
        realmEmployee.setEmail(obj.getEmail());
        realmEmployee.setAge(obj.getAge());
        realmEmployee.setStatus(obj.getStatus());

        return realmEmployee;
    }

    private void testFindEmployeeById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.where(RealmEmployee.class).equalTo("id", i).findFirst();
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findEmployeeById avg = " + t);
    }

    private void testFindProjectById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.where(RealmProject.class).equalTo("id", i).findFirst();
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findProjectById avg = " + t);
    }

    private void testFindUnitById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.where(RealmUnit.class).equalTo("id", i).findFirst();
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findUnitById avg = " + t);
    }

}
