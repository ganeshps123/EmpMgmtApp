package com.techdemos.empmgmtapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface EmployeeDao {
    String EMPLOYEE_TABLE_NAME="employee";

    @Insert(onConflict = REPLACE)
    long[] insertEmployees(List<Employee> employees);

    @Query("select * from "+EMPLOYEE_TABLE_NAME)
    LiveData<List<Employee>> getEmployees();
}