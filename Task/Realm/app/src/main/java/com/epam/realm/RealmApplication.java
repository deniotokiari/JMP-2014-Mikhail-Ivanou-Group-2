package com.epam.realm;

import com.epam.realm.xcore.AssetsDataSource;
import com.epam.realm.xcore.ContentProvider;
import com.epam.realm.xcore.processor.EmployeeProcessor;
import com.epam.realm.xcore.processor.ProjectProcessor;
import com.epam.realm.xcore.processor.UnitProcessor;

import by.istin.android.xcore.CoreApplication;
import by.istin.android.xcore.provider.IDBContentProviderSupport;
import by.istin.android.xcore.provider.impl.DBContentProviderFactory;

/**
 * Created by sergey on 07.12.2014.
 */
public class RealmApplication extends CoreApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        IDBContentProviderSupport dbContentProvider = DBContentProviderFactory.getDefaultDBContentProvider(this, ContentProvider.DB_ENTITIES);
        registerAppService(new AssetsDataSource());
        registerAppService(new UnitProcessor(dbContentProvider));
        registerAppService(new ProjectProcessor(dbContentProvider));
        registerAppService(new EmployeeProcessor(dbContentProvider));
    }
}
