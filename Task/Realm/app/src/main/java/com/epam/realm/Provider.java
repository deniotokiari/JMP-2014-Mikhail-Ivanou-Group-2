package com.epam.realm;

import com.epam.realm.model.Employee;
import com.epam.realm.model.Project;
import com.epam.realm.model.Unit;

/**
 * Created by sergey on 03.12.2014.
 */
public interface Provider {

    void createEmployee(Employee employee);

    void createUnit(Unit unit);

    void createProject(Project project);

    Employee findEmployeeById(long id);

    Unit findUnitById(long id);

    Project findProjectById(long id);

    void deleteEmployeeById(long id);

    void deleteUnitById(long id);

    void deleteProjectById(long id);

    void updateEmployeeById(Employee employee);

    void updateUnitById(Unit unit);

    void updateProjectById(Project project);

    void addEmployeeToUnit(Employee employee, Unit unit);

    void assignEmployeeForProject(Employee employee, Project project);
}


