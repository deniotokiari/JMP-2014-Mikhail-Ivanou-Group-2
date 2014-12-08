package com.epam.realm.xcore.processor;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.epam.realm.xcore.model.Project;

import by.istin.android.xcore.processor.impl.AbstractGsonProcessor;
import by.istin.android.xcore.provider.IDBContentProviderSupport;
import by.istin.android.xcore.provider.ModelContract;
import by.istin.android.xcore.source.DataSourceRequest;

/**
 * Created by sergey on 07.12.2014.
 */
public class ProjectProcessor extends AbstractGsonProcessor<Project[]> {

    public static final String KEY = "processor:project";
    private final IDBContentProviderSupport mDbContentProvider;

    public ProjectProcessor(IDBContentProviderSupport dbContentProvider) {
        super(Project[].class);
        mDbContentProvider = dbContentProvider;
    }

    @Override
    public void cache(Context context, DataSourceRequest dataSourceRequest, Project[] jsonArray) throws Exception {
        int length = jsonArray.length;
        ContentValues[] values = new ContentValues[length];
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            Project obj = jsonArray[i];
            ContentValues val = new ContentValues();
            val.put(Project.ID, obj.getId());
            val.put(Project.NAME, obj.getName());
            val.put(Project.ABOUT, obj.getAbout());
            values[i] = val;
        }
        long t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "[read] createProjects avg = " + t);

        start = System.currentTimeMillis();
        mDbContentProvider.bulkInsertOrUpdate(ModelContract.getUri(Project.class), values);
        t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "createProjects avg = " + t);
    }

    @Override
    public String getAppServiceKey() {
        return KEY;
    }

}
