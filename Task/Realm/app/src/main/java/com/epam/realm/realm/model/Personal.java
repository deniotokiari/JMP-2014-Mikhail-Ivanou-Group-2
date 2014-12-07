package com.epam.realm.realm.model;

import io.realm.RealmObject;

/**
 * Created by sergey on 03.12.2014.
 */
public class Personal extends RealmObject {
    private Employee employee;
    private int unitId;
    private int curProjectId;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getCurProjectId() {
        return curProjectId;
    }

    public void setCurProjectId(int curProjectId) {
        this.curProjectId = curProjectId;
    }
}
