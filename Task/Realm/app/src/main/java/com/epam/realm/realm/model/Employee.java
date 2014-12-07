package com.epam.realm.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Employee extends RealmObject {
    private int id;
    private String name;
    private String email;
    private int age;
    private String status;
    private Personal personal;
    private Address address;
    private RealmList<Project> projects;

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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RealmList<Project> getProjects() {
        return projects;
    }

    public void setProjects(RealmList<Project> projects) {
        this.projects = projects;
    }
}
