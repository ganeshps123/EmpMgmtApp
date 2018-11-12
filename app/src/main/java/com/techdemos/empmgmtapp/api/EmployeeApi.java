package com.techdemos.empmgmtapp.api;

import android.arch.lifecycle.LiveData;

import com.techdemos.empmgmtapp.db.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {
//    "http://restdemo-env.cmczw7ckyf.ap-south-1.elasticbeanstalk.com/webapi/"
    @GET("employees")
    LiveData<ApiResponse<List<Employee>>> getEmployees();
//    LiveData<ApiResponse<List<Employee>>> getEmployees(@Query("start") String start, @Query("size") String size);

    @GET("employees")
    Call<List<Employee>> getEmployeesSync();
}
