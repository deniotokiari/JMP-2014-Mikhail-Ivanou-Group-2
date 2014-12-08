package com.epam.realm.xcore.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

import by.istin.android.xcore.annotations.dbEntities;
import by.istin.android.xcore.annotations.dbInteger;
import by.istin.android.xcore.annotations.dbString;
import by.istin.android.xcore.db.IDBConnection;
import by.istin.android.xcore.db.entity.IGenerateID;
import by.istin.android.xcore.db.impl.DBHelper;
import by.istin.android.xcore.source.DataSourceRequest;
import by.istin.android.xcore.utils.HashUtils;

/**
 * Created by sergey on 03.12.2014.
 */
public class Employee implements BaseColumns, IGenerateID {
    @dbInteger
    public static final String ID = "id";
    int id;

    public int getId() {
        return id;
    }

    @dbString
    public static final String NAME = "name";

    String name;

    public String getName() {
        return name;
    }

    @dbString
    public static final String EMAIL = "email";
    String email;

    public String getEmail() {
        return email;
    }

    @dbInteger
    public static final String AGE = "age";
    int age;

    public int getAge() {
        return age;
    }

    @dbString
    public static final String STATUS = "status";
    String status;

    public String getStatus() {
        return status;
    }

    @dbEntities(clazz = Personal.class)
    public static final String PERSONAL = "personal";

    @dbEntities(clazz = Address.class)
    public static final String ADDRESS = "address";

    @dbEntities(clazz = Project.class)
    public static final String PROJECTS = "projects";

    @Override
    public long generateId(DBHelper dbHelper, IDBConnection db, DataSourceRequest dataSourceRequest, ContentValues contentValues) {
        return HashUtils.generateId(
                contentValues,
                ID
        );
    }
}
