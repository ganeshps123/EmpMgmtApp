/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techdemos.empmgmtapp.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.techdemos.empmgmtapp.api.ApiResponse;
import com.techdemos.empmgmtapp.api.EmployeeApi;
import com.techdemos.empmgmtapp.db.Employee;
import com.techdemos.empmgmtapp.util.Resource;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * A task that reads the search result in the database and fetches the next page, if it has one.
 */
public class FetchEmployeesTask implements Runnable {
    private final MutableLiveData<Resource<List<Employee>>> liveData = new MutableLiveData<>();
    private final EmployeeApi employeeApi;

    FetchEmployeesTask(EmployeeApi employeeApi) {
        this.employeeApi = employeeApi;
    }

    @Override
    public void run() {
        try {
            Response<List<Employee>> response = employeeApi.getEmployeesSync().execute();
            ApiResponse<List<Employee>> apiResponse = new ApiResponse<>(response);

            if (apiResponse.isSuccessful()) {
                liveData.postValue(Resource.success(apiResponse.body));
            } else {
//                liveData.postValue(Resource.error(apiResponse.errorMessage, null));
                liveData.postValue(Resource.success((List<Employee>) null));
            }
        } catch (IOException e) {
            liveData.postValue(Resource.success((List<Employee>) null));
        }
    }

    LiveData<Resource<List<Employee>>> getLiveData() {
        return liveData;
    }
}
