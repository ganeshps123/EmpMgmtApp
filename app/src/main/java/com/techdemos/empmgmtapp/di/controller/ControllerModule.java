package com.techdemos.empmgmtapp.di.controller;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Handler;


import com.techdemos.empmgmtapp.repo.EmployeeRepository;
import com.techdemos.empmgmtapp.vm.CustomViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
    private final Activity mActivity;

    public ControllerModule(Activity activity){
        mActivity = activity;
    }

    @Provides
    Context context(){return mActivity;}

    @Provides
    Activity activity(){return mActivity;}

    @Provides
    Handler handler(){return new Handler();}

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(Application application, EmployeeRepository employeeRepository){
        return new CustomViewModelFactory(application, employeeRepository);
    }
}
