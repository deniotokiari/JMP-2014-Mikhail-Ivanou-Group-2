package com.epam.realm.realm;

import android.content.Context;
import android.util.Log;

import com.epam.realm.DataProvider;
import com.epam.realm.realm.model.Address;
import com.epam.realm.realm.model.Employee;
import com.epam.realm.realm.model.Personal;
import com.epam.realm.realm.model.Project;
import com.epam.realm.realm.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        testFindEmployeeById(mProvider.getFeedEmployees().length());
        testFindProjectById(mProvider.getFeedProjects().length());
        testFindUnitById(mProvider.getFeedUnits().length());
    }

    private void testCreateUnits(JSONArray feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length();
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                createUnit(feed.getJSONObject(i));
            }
            long t = (System.currentTimeMillis() - start) / size;
            mRealm.commitTransaction();
            Log.e("RESULT REALM", "createUnits avg = " + t);
        } catch (Exception e) {
        }
    }

    private void createUnit(JSONObject obj) {
        try {
            Unit realmUnit = mRealm.createObject(Unit.class);
            realmUnit.setId(obj.getInt("id"));
            realmUnit.setTitle(obj.getString("title"));
        } catch (JSONException e) {
            Log.e(TAG, "error " + e);
        }
        Log.d(TAG, "createUnit " + obj);
    }

    private void testCreateProjects(JSONArray feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length();
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                createProject(feed.getJSONObject(i));
            }
            long t = (System.currentTimeMillis() - start) / size;
            mRealm.commitTransaction();
            Log.e("RESULT REALM", "createProjects avg = " + t);
        } catch (Exception e) {
        }
    }

    private void createProject(JSONObject obj) {
        try {
            Project realmProject = mRealm.createObject(Project.class);
            realmProject.setId(obj.getInt("id"));
            realmProject.setName(obj.getString("name"));
            realmProject.setAbout(obj.getString("about"));
        } catch (JSONException e) {
            Log.e(TAG, "error " + e);
        }
        Log.d(TAG, "createProject " + obj);
    }

    private void testCreateEmployees(JSONArray feed) {
        try {
            mRealm.beginTransaction();
            int size = feed.length();
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                createEmployee(feed.getJSONObject(i));
            }
            long t = (System.currentTimeMillis() - start) / size;
            mRealm.commitTransaction();
            Log.e("RESULT REALM", "createEmployees avg = " + t);
        } catch (Exception e) {
        }
    }

    private void createEmployee(JSONObject obj) {
        try {
            Employee realmEmployee = mRealm.createObject(Employee.class);
            realmEmployee.setId(obj.getInt("id"));
            realmEmployee.setName(obj.getString("name"));
            realmEmployee.setEmail(obj.getString("email"));
            realmEmployee.setAge(obj.getInt("age"));
            realmEmployee.setStatus(obj.getString("status"));

            Personal realmPersonal = mRealm.createObject(Personal.class);
            JSONObject jsonObjectPersonal = obj.getJSONObject("personal");
            realmPersonal.setCurProjectId(jsonObjectPersonal.getInt("curProject"));
            realmPersonal.setUnitId(jsonObjectPersonal.getInt("unit"));
            realmPersonal.setEmployee(realmEmployee);

            Address realmAddress = mRealm.createObject(Address.class);
            JSONObject jsonObjectAddress = obj.getJSONObject("address");
            realmAddress.setCountry(jsonObjectAddress.getString("country"));
            realmAddress.setCity(jsonObjectAddress.getString("city"));
            realmAddress.setStreet(jsonObjectAddress.getString("street"));
            realmAddress.setPostalCode(jsonObjectAddress.getInt("postalCode"));

            realmEmployee.setPersonal(realmPersonal);
            realmEmployee.setAddress(realmAddress);
        } catch (JSONException e) {
            Log.e(TAG, "error " + e);
        }
        Log.d(TAG, "createEmployee " + obj);
    }

    private void testFindEmployeeById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.where(Employee.class).equalTo("id", i).findFirst();
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findEmployeeById avg = " + t);
    }

    private void testFindProjectById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.where(Project.class).equalTo("id", i).findFirst();
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findProjectById avg = " + t);
    }

    private void testFindUnitById(int max) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            mRealm.where(Unit.class).equalTo("id", i).findFirst();
        }
        long t = (System.currentTimeMillis() - start) / max;
        Log.e("RESULT REALM", "findUnitById avg = " + t);
    }

}
