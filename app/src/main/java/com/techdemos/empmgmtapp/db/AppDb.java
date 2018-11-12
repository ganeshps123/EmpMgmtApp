package com.techdemos.empmgmtapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 2, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract EmployeeDao employeeModel();
}