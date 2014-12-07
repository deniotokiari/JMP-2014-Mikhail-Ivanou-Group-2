package com.epam.realm.xcore;

import com.epam.realm.xcore.model.Address;
import com.epam.realm.xcore.model.Employee;
import com.epam.realm.xcore.model.Personal;
import com.epam.realm.xcore.model.Project;
import com.epam.realm.xcore.model.Unit;

import by.istin.android.xcore.provider.DBContentProvider;

/**
 * Created by sergey on 07.12.2014.
 */
public class ContentProvider extends DBContentProvider {

    public static final Class<?>[] DB_ENTITIES = new Class<?>[]{
            Address.class,
            Employee.class,
            Personal.class,
            Project.class,
            Unit.class
    };

    @Override
    public Class<?>[] getEntities() {
        return DB_ENTITIES;
    }

}

