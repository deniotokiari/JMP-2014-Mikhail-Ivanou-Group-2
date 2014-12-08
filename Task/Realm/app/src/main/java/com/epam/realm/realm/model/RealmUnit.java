package com.epam.realm.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class RealmUnit extends RealmObject {
    private int id;
    private String title;
    private RealmList<RealmEmployee> employees;

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

    public RealmList<RealmEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(RealmList<RealmEmployee> employees) {
        this.employees = employees;
    }
}
