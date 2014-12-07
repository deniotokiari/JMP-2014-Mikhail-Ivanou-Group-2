package com.epam.realm.xcore;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

import by.istin.android.xcore.ContextHolder;
import by.istin.android.xcore.source.DataSourceRequest;
import by.istin.android.xcore.source.IDataSource;

/**
 * Created by sergey on 07.12.2014.
 */
public class AssetsDataSource implements IDataSource<InputStream> {

    public static final String KEY = "datasource:assets";

    @Override
    public String getAppServiceKey() {
        return KEY;
    }

    @Override
    public InputStream getSource(DataSourceRequest dataSourceRequest) throws IOException {
        Context context = ContextHolder.get();
        return context.getAssets().open(dataSourceRequest.getUri());
    }
}
