package com.techdemos.empmgmtapp.di.controller;

import com.techdemos.empmgmtapp.EmpListActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ControllerModule.class})
public interface ControllerComponent {
    void inject(EmpListActivity empListActivity);
//    void inject(DisplayAdapter displayAdapter);
}
