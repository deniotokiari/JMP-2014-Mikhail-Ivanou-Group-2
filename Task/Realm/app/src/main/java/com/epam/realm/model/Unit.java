package com.epam.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Unit extends RealmObject {
    RealmList<Employee> employees;
    String name;
    int number;
}
