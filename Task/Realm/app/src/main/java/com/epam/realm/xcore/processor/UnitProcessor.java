package com.epam.realm.xcore.processor;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.epam.realm.xcore.model.Unit;

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
public class UnitProcessor extends AbstractStringProcessor<JSONArray> {

    public static final String KEY = "processor:unit";
    private final IDBContentProviderSupport mDbContentProvider;

    public UnitProcessor(IDBContentProviderSupport dbContentProvider) {
        mDbContentProvider = dbContentProvider;
    }

    @Override
    public void cache(Context context, DataSourceRequest dataSourceRequest, JSONArray jsonArray) throws Exception {
        int length = jsonArray.length();
        ContentValues[] values = new ContentValues[length];
        for (int i = 0; i < length; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            ContentValues val = new ContentValues();
            val.put(Unit.ID, obj.getInt(Unit.ID));
            val.put(Unit.TITLE, obj.getString(Unit.TITLE));
            values[i] = val;
        }
        long start = System.currentTimeMillis();
        mDbContentProvider.bulkInsertOrUpdate(ModelContract.getUri(Unit.class), values);
        long t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "createUnits avg = " + t);

        findUnitById();
    }

    private void findUnitById() {
        String sql = "SELECT * FROM " + DBHelper.getTableName(Unit.class) + " WHERE " + Unit.ID + " = 1";
        long start = System.currentTimeMillis();
        List<ContentValues> unit = ContentUtils.getEntitiesFromSQL(ContextHolder.get(), sql);
        long t = (System.currentTimeMillis() - start);
        Log.e("RESULT XCORE", "findUnitById = " + t);
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
