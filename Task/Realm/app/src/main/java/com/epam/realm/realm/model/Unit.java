package com.epam.realm.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Unit extends RealmObject {
    private int id;
    private String title;
    private RealmList<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(RealmList<Employee> employees) {
        this.employees = employees;
    }
}
