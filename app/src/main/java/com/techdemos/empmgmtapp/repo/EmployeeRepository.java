package com.techdemos.empmgmtapp.repo;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import com.techdemos.empmgmtapp.api.ApiResponse;
import com.techdemos.empmgmtapp.db.Employee;
import com.techdemos.empmgmtapp.api.EmployeeApi;
import com.techdemos.empmgmtapp.db.EmployeeDao;
import com.techdemos.empmgmtapp.util.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Repository that handles User objects.
 */
@Singleton
public class EmployeeRepository {
    private static final String TAG=EmployeeRepository.class.getSimpleName();
    private final EmployeeDao employeeDao;
    private final EmployeeApi employeeApi;

    @Inject
//    EmployeeRepository(AppExecutors appExecutors, UserDao userDao, CardiotrackService employeeApi) {
    public EmployeeRepository(EmployeeApi employeeApi, EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        this.employeeApi = employeeApi;
    }

    public LiveData<Resource<List<Employee>>> getEmployees() {
//        FetchEmployeesTask fetchEmployeesTask = new FetchEmployeesTask(employeeApi);
//        appExecutors.networkIO().execute(fetchEmployeesTask);
//        return fetchEmployeesTask.getLiveData();
        return new NetworkBoundResource<List<Employee>, List<Employee>>() {

            @Override
            protected void saveCallResult(@NonNull List<Employee> employees) {
                employeeDao.insertEmployees(employees);
//                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Employee> employees) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Employee>> loadFromDb() {
                return employeeDao.getEmployees();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Employee>>> createCall() {
                return employeeApi.getEmployees();
            }
        }.asLiveData();

        
//        return new FetchNetworkResource<List<Employee>>() {
//            @NonNull
//            @Override
//            protected LiveData<ApiResponse<List<Employee>>> createCall() {
//                return employeeApi.getEmployees();
//            }
//
//            @Override
//            protected void saveCallResult(List<Employee> item){
////                for (Employee e:item) {
////                    Log.d(TAG, "id: "+e.getId());
////                    Log.d(TAG, "name: "+e.getName());
////                }
//            }
//        }.asLiveData();
    }
}
