package com.epam.realm;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
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

    private JSONArray mEmployees;
    private JSONArray mProjects;
    private JSONArray mUnits;

    public DataProvider(Context context) {
        mContext = context;
    }

    public JSONArray getFeedEmployees() {
        try {
            if (mEmployees == null) {
                mEmployees = readAssets(FILE_EMPLOYEE);
            }
            return mEmployees;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file");
        }
    }

    public JSONArray getFeedProjects() {
        try {
            if (mProjects == null) {
                mProjects = readAssets(FILE_PROJECT);
            }
            return mProjects;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file");
        }
    }

    public JSONArray getFeedUnits() {
        try {
            if (mUnits == null) {
                mUnits = readAssets(FILE_UNIT);
            }
            return mUnits;
        } catch (Exception e) {
            throw new RuntimeException("Cannot load file");
        }
    }

    public JSONArray readAssets(String file) throws IOException, JSONException {
        InputStream inputStream = getAssetInputScream(file);
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return new JSONArray(total.toString());
    }

    public InputStream getAssetInputScream(String file) throws IOException {
        return mContext.getAssets().open(file);
    }


}
