package com.epam.realm.xcore.model;

import android.content.ContentValues;
import android.provider.BaseColumns;

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
public class Unit implements BaseColumns, IGenerateID {

    @dbInteger
    public static final String ID = "id";
    int id;

    public int getId() {
        return id;
    }


    @dbString
    public static final String TITLE = "title";
    String title;

    public String getTitle() {
        return title;
    }

    @Override
    public long generateId(DBHelper dbHelper, IDBConnection db, DataSourceRequest dataSourceRequest, ContentValues contentValues) {
        return HashUtils.generateId(
                contentValues,
                ID
        );
    }
}
