package com.techdemos.empmgmtapp.vm;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;


import com.techdemos.empmgmtapp.util.AbsentLiveData;
import com.techdemos.empmgmtapp.db.Employee;
import com.techdemos.empmgmtapp.repo.EmployeeRepository;
import com.techdemos.empmgmtapp.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class EmployeeViewModel extends ViewModel {
    @VisibleForTesting
    private final MutableLiveData<String> empListApiArgs = new MutableLiveData<>();
//    private final LiveData<Resource<List<Employee>>> empList;
    private final LiveData<Resource<List<Employee>>> empList;
    @SuppressWarnings("unchecked")

    @Inject
    public EmployeeViewModel(final EmployeeRepository employeeRepository) {
        empList = Transformations.switchMap(empListApiArgs, new Function<String, LiveData<Resource<List<Employee>>>>() {
            @Override
            public LiveData<Resource<List<Employee>>> apply(String input) {
                if (input == null) {
                    return AbsentLiveData.create();
                } else {
                    return employeeRepository.getEmployees();
                }
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void setEmpListApiArgs(String user_id) {
        empListApiArgs.setValue(user_id);
    }

    //    @VisibleForTesting
    public LiveData<Resource<List<Employee>>> getEmpList() {
        return empList;
    }
}
