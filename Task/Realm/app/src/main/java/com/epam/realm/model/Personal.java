package com.epam.realm.model;

import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Personal extends RealmObject {
    long id;
    boolean isActive;
    int age;
    String name;
    String email;
    Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
