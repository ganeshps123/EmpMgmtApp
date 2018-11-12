package com.techdemos.empmgmtapp.di.application;


import com.techdemos.empmgmtapp.di.controller.ControllerComponent;
import com.techdemos.empmgmtapp.di.controller.ControllerModule;
import com.techdemos.empmgmtapp.di.service.ServiceComponent;
import com.techdemos.empmgmtapp.di.service.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    ControllerComponent controllerComponent(ControllerModule controllerModule);
    ServiceComponent serviceComponent(ServiceModule serviceModule);
}
