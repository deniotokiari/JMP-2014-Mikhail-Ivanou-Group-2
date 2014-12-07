package com.epam.realm.xcore.processor;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.epam.realm.xcore.model.Project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import by.istin.android.xcore.ContextHolder;
import by.istin.android.xcore.db.impl.DBHelper;
import by.istin.android.xcore.processor.impl.AbstractStringProcessor;
import by.istin.android.xcore.provider.IDBContentProviderSupport;
import by.istin.android.xcore.provider.ModelContract;
import by.istin.android.xcore.source.DataSourceRequest;
import by.istin.android.xcore.utils.ContentUtils;

/**
 * Created by sergey on 07.12.2014.
 */
public class ProjectProcessor extends AbstractStringProcessor<JSONArray> {

    public static final String KEY = "processor:project";
    private final IDBContentProviderSupport mDbContentProvider;

    public ProjectProcessor(IDBContentProviderSupport dbContentProvider) {
        mDbContentProvider = dbContentProvider;
    }

    @Override
    public void cache(Context context, DataSourceRequest dataSourceRequest, JSONArray jsonArray) throws Exception {
        int length = jsonArray.length();
        ContentValues[] values = new ContentValues[length];
        for (int i = 0; i < length; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            ContentValues val = new ContentValues();
            val.put(Project.ID, obj.getInt(Project.ID));
            val.put(Project.NAME, obj.getString(Project.NAME));
            val.put(Project.ABOUT, obj.getString(Project.ABOUT));
            values[i] = val;
        }
        long start = System.currentTimeMillis();
        mDbContentProvider.bulkInsertOrUpdate(ModelContract.getUri(Project.class), values);
        long t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "createProjects avg = " + t);
    }

    @Override
    public String getAppServiceKey() {
        return KEY;
    }

    @Override
    protected JSONArray convert(String string) throws Exception {
        return new JSONArray(string);
    }
}
