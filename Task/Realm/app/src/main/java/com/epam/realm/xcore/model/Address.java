package com.epam.realm.xcore.model;

import android.provider.BaseColumns;

import by.istin.android.xcore.annotations.dbInteger;
import by.istin.android.xcore.annotations.dbString;

/**
 * Created by sergey on 06.12.2014.
 */
public class Address implements BaseColumns {

    @dbString
    public static final String COUNTRY = "country";

    @dbString
    public static final String CITY = "city";

    @dbString
    public static final String STREET = "street";

    @dbInteger
    public static final String POSTAL_CODE = "postalCode";

}
