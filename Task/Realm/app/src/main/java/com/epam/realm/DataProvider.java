package com.epam.realm;

import android.content.Context;

import com.epam.realm.realm.model.Employee;
import com.epam.realm.realm.model.Project;
import com.epam.realm.realm.model.Unit;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sergey on 07.12.2014.
 */
public class DataProvider {

    public static final String FILE_EMPLOYEE = "employee.json";
    public static final String FILE_PROJECT = "project.json";
    public static final String FILE_UNIT = "unit.json";

    private final Context mContext;

    private Employee[] mEmployees;
    private Project[] mProjects;
    private Unit[] mUnits;

    public DataProvider(Context context) {
        mContext = context;
    }

    public Employee[] getFeedEmployees() {
        try {
            if (mEmployees == null) {
                mEmployees = readAssets(FILE_EMPLOYEE, Employee[].class);
            }
            return mEmployees;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file");
        }
    }

    public Project[] getFeedProjects() {
        try {
            if (mProjects == null) {
                mProjects = readAssets(FILE_PROJECT, Project[].class);
            }
            return mProjects;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file");
        }
    }

    public Unit[] getFeedUnits() {
        try {
            if (mUnits == null) {
                mUnits = readAssets(FILE_UNIT, Unit[].class);
            }
            return mUnits;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file");
        }
    }

    public <T> T readAssets(String file, Class<T> clazz) throws IOException, JSONException {
        InputStream inputStream = getAssetInputScream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    public InputStream getAssetInputScream(String file) throws IOException {
        return mContext.getAssets().open(file);
    }


}
