package com.epam.realm;

import android.content.Context;

import com.epam.realm.model.Employee;
import com.epam.realm.model.Personal;
import com.epam.realm.model.Project;
import com.epam.realm.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;

/**
 * Created by sergey on 03.12.2014.
 */
public class ProviderImpl implements Provider {

    private final Context mContext;
    private Realm mRealm;

    JSONArray employees;
    JSONArray projects;
    JSONArray units;

    public ProviderImpl(Context context) {
        mContext = context;
        try {
            employees = read("employee.json");
            projects = read("project.json");
            units = read("unit.json");
        } catch (Exception e) {
            throw new RuntimeException("Cannot load files");
        }
        mRealm = Realm.getInstance(context);
    }

    private JSONArray read(String file) throws IOException, JSONException {
        InputStream inputStream = mContext.getAssets().open(file);
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return new JSONArray(total.toString());
    }

    @Override
    public void createEmployee(Employee employee) {
        mRealm.beginTransaction();
        //Employee employee = mRealm.createObject(Employee.class);

        Personal personal = mRealm.createObject(Personal.class);
        personal.setName("Sergey");
        personal.setAge(22);
        personal.setEmployee(employee);

        employee.setPersonal(personal);

        mRealm.commitTransaction();
    }

    @Override
    public void createUnit(Unit unit) {

    }

    @Override
    public void createProject(Project project) {

    }

    @Override
    public Employee findEmployeeById(long id) {
        return null;
    }

    @Override
    public Unit findUnitById(long id) {
        return null;
    }

    @Override
    public Project findProjectById(long id) {
        return null;
    }

    @Override
    public void deleteEmployeeById(long id) {

    }

    @Override
    public void deleteUnitById(long id) {

    }

    @Override
    public void deleteProjectById(long id) {

    }

    @Override
    public void updateEmployeeById(Employee employee) {

    }

    @Override
    public void updateUnitById(Unit unit) {

    }

    @Override
    public void updateProjectById(Project project) {

    }

    @Override
    public void addEmployeeToUnit(Employee employee, Unit unit) {

    }

    @Override
    public void assignEmployeeForProject(Employee employee, Project project) {

    }
}
