package com.epam.realm.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Project extends RealmObject {
    RealmList<Employee> employeeList;
    String name;
    Date start;
    String about;
}
