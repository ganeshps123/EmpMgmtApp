package com.techdemos.empmgmtapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "employee")
public final class Employee {
    @PrimaryKey
    @NonNull
    public String employeeId;

    public String emplyeeName;
    public String employeeSalary;
    public String department;
    public double latitude;
    public double longitude;

    public String getId() {
        return employeeId;
    }
    public void setId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return emplyeeName;
    }
    public void setName(String emplyeeName) {
        this.emplyeeName = emplyeeName;
    }
    public String getSalary() {
        return employeeSalary;
    }
    public void setSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
