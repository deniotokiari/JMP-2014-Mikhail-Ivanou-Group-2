package com.epam.realm.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class RealmEmployee extends RealmObject {
    private int id;
    private String name;
    private String email;
    private int age;
    private String status;
    private RealmPersonal personal;
    private RealmAddress address;
    private RealmList<RealmProject> projects;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RealmPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(RealmPersonal personal) {
        this.personal = personal;
    }

    public RealmAddress getAddress() {
        return address;
    }

    public void setAddress(RealmAddress address) {
        this.address = address;
    }

    public RealmList<RealmProject> getProjects() {
        return projects;
    }

    public void setProjects(RealmList<RealmProject> projects) {
        this.projects = projects;
    }
}
