package com.epam.realm.realm;

import android.content.Context;
import android.util.Log;

import com.epam.realm.realm.model.Address;
import com.epam.realm.realm.model.Employee;
import com.epam.realm.realm.model.Personal;
import com.epam.realm.realm.model.Project;
import com.epam.realm.realm.model.Unit;

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

    public RealmImpl(Context context) {
        mContext = context;
        File file = mContext.getFileStreamPath(Realm.DEFAULT_REALM_NAME);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "delete db");
        }
        mRealm = Realm.getInstance(mContext);
        Log.d(TAG, "create realm instance");
    }

    public void beginTransaction() {
        mRealm.beginTransaction();
    }

    public void commitTransaction() {
        mRealm.commitTransaction();
    }

    public void createEmployee(JSONObject obj) {
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

    public void createUnit(JSONObject obj) {
        try {
            Unit realmUnit = mRealm.createObject(Unit.class);
            realmUnit.setId(obj.getInt("id"));
            realmUnit.setTitle(obj.getString("title"));
        } catch (JSONException e) {
            Log.e(TAG, "error " + e);
        }
        Log.d(TAG, "createUnit " + obj);
    }

    public void createProject(JSONObject obj) {
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

    public JSONObject findEmployeeById(int id) {
        mRealm.where(Employee.class).equalTo("id", id).findFirst();
        return null;
    }

    public JSONObject findUnitById(int id) {
        mRealm.where(Unit.class).equalTo("id", id).findFirst();
        return null;
    }

    public JSONObject findProjectById(int id) {
        mRealm.where(Project.class).equalTo("id", id).findFirst();
        return null;
    }

    /*public void deleteEmployeeById(int id) {
        mRealm.beginTransaction();
        mRealm.where(Employee.class).equalTo("id", id).findAll().clear();
        mRealm.commitTransaction();
    }

    public void deleteUnitById(int id) {
        mRealm.beginTransaction();
        mRealm.where(Unit.class).equalTo("id", id).findAll().clear();
        mRealm.commitTransaction();
    }

    public void deleteProjectById(int id) {
        mRealm.beginTransaction();
        mRealm.where(Project.class).equalTo("id", id).findAll().clear();
        mRealm.commitTransaction();
    }*/

}
