package com.epam.realm.xcore.processor;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.epam.realm.xcore.model.Employee;

import by.istin.android.xcore.processor.impl.AbstractGsonProcessor;
import by.istin.android.xcore.provider.IDBContentProviderSupport;
import by.istin.android.xcore.provider.ModelContract;
import by.istin.android.xcore.source.DataSourceRequest;

/**
 * Created by sergey on 07.12.2014.
 */
public class EmployeeProcessor extends AbstractGsonProcessor<Employee[]> {

    public static final String KEY = "processor:employee";
    private final IDBContentProviderSupport mDbContentProvider;

    public EmployeeProcessor(IDBContentProviderSupport dbContentProvider) {
        super(Employee[].class);
        mDbContentProvider = dbContentProvider;
    }

    @Override
    public void cache(Context context, DataSourceRequest dataSourceRequest, Employee[] jsonArray) throws Exception {
        int length = jsonArray.length;
        ContentValues[] values = new ContentValues[length];
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            Employee obj = jsonArray[i];
            ContentValues val = new ContentValues();
            val.put(Employee.ID, obj.getId());
            val.put(Employee.NAME, obj.getName());
            val.put(Employee.EMAIL, obj.getEmail());
            val.put(Employee.AGE, obj.getAge());
            val.put(Employee.STATUS, obj.getStatus());
            values[i] = val;
        }
        long t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "[read] createEmployee avg = " + t);

        start = System.currentTimeMillis();
        mDbContentProvider.bulkInsertOrUpdate(ModelContract.getUri(Employee.class), values);
        t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "createEmployee avg = " + t);
    }

    @Override
    public String getAppServiceKey() {
        return KEY;
    }

}
