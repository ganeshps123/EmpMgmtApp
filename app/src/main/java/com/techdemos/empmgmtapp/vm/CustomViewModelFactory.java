package com.techdemos.empmgmtapp.vm;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.techdemos.empmgmtapp.repo.EmployeeRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final EmployeeRepository employeeRepository;
    private final Application application;

    @Inject
    public CustomViewModelFactory(Application application, EmployeeRepository employeeRepository) {
        this.application=application;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EmployeeViewModel.class)) {
            return (T) new EmployeeViewModel(employeeRepository);
        }
        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
