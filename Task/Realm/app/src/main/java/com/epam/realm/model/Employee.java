package com.epam.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Employee extends RealmObject {
    public class Address {
        String country;
        String city;
        String street;
        String postalCode;
    }

    Address address;
    Personal personal;
    RealmList<Project> projectList;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public RealmList<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(RealmList<Project> projectList) {
        this.projectList = projectList;
    }
}
