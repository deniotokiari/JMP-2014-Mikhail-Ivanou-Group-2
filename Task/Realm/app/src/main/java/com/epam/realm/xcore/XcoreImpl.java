package com.epam.realm.xcore;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

import com.epam.realm.DataProvider;
import com.epam.realm.xcore.model.Project;
import com.epam.realm.xcore.model.Unit;
import com.epam.realm.xcore.processor.EmployeeProcessor;
import com.epam.realm.xcore.processor.ProjectProcessor;
import com.epam.realm.xcore.processor.UnitProcessor;

import java.util.List;

import by.istin.android.xcore.ContextHolder;
import by.istin.android.xcore.Core;
import by.istin.android.xcore.db.impl.DBHelper;
import by.istin.android.xcore.source.DataSourceRequest;
import by.istin.android.xcore.utils.ContentUtils;

/**
 * Created by sergey on 03.12.2014.
 */
public class XcoreImpl {

    private static final String SQL = "";
    private final Activity mContext;

    public XcoreImpl(Activity context) {
        mContext = context;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                createUnit();
                createProject();
                createEmployee();
            }
        }
        ).start();
    }


    public void createEmployee() {
        DataSourceRequest request = new DataSourceRequest(DataProvider.FILE_EMPLOYEE);
        request.setCacheable(true);
        request.setForceUpdateData(true);

        final Core.IExecuteOperation operation = new Core.ExecuteOperationBuilder()
                .setActivity(mContext)
                .setDataSourceKey(AssetsDataSource.KEY)
                .setProcessorKey(EmployeeProcessor.KEY)
                .setDataSourceRequest(request)
                .build();

        Core.get(mContext).execute(operation);
    }

    public void createUnit() {
        DataSourceRequest request = new DataSourceRequest(DataProvider.FILE_UNIT);
        request.setCacheable(true);
        request.setForceUpdateData(true);

        final Core.IExecuteOperation operation = new Core.ExecuteOperationBuilder()
                .setActivity(mContext)
                .setDataSourceKey(AssetsDataSource.KEY)
                .setProcessorKey(UnitProcessor.KEY)
                .setDataSourceRequest(request)
                .build();

        Core.get(mContext).execute(operation);
    }

    public void createProject() {
        DataSourceRequest request = new DataSourceRequest(DataProvider.FILE_PROJECT);
        request.setCacheable(true);
        request.setForceUpdateData(true);

        final Core.IExecuteOperation operation = new Core.ExecuteOperationBuilder()
                .setActivity(mContext)
                .setDataSourceKey(AssetsDataSource.KEY)
                .setProcessorKey(ProjectProcessor.KEY)
                .setDataSourceRequest(request)
                .build();

        Core.get(mContext).execute(operation);
    }

}