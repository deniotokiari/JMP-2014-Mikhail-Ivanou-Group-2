package com.epam.realm.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class RealmProject extends RealmObject {
    private int id;
    private String name;
    private String about;
    private RealmList<RealmEmployee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public RealmList<RealmEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(RealmList<RealmEmployee> employees) {
        this.employees = employees;
    }
}
