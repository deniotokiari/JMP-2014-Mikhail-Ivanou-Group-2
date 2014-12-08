package com.epam.realm.xcore.processor;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.epam.realm.xcore.model.Unit;

import by.istin.android.xcore.processor.impl.AbstractGsonProcessor;
import by.istin.android.xcore.provider.IDBContentProviderSupport;
import by.istin.android.xcore.provider.ModelContract;
import by.istin.android.xcore.source.DataSourceRequest;

/**
 * Created by sergey on 07.12.2014.
 */
public class UnitProcessor extends AbstractGsonProcessor<Unit[]> {

    public static final String KEY = "processor:unit";
    private final IDBContentProviderSupport mDbContentProvider;

    public UnitProcessor(IDBContentProviderSupport dbContentProvider) {
        super(Unit[].class);
        mDbContentProvider = dbContentProvider;
    }

    @Override
    public void cache(Context context, DataSourceRequest dataSourceRequest, Unit[] units) throws Exception {
        int length = units.length;
        ContentValues[] values = new ContentValues[length];
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            Unit unit = units[i];
            ContentValues val = new ContentValues();
            val.put(Unit.ID, unit.getId());
            val.put(Unit.TITLE, unit.getTitle());
            values[i] = val;
        }
        long t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "[read] createUnits avg = " + t);

        start = System.currentTimeMillis();
        mDbContentProvider.bulkInsertOrUpdate(ModelContract.getUri(Unit.class), values);
        t = (System.currentTimeMillis() - start) / length;
        Log.e("RESULT XCORE", "createUnits avg = " + t);
    }

    @Override
    public String getAppServiceKey() {
        return KEY;
    }
}
