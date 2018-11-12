package com.techdemos.empmgmtapp;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;


import com.techdemos.empmgmtapp.di.application.AppComponent;
import com.techdemos.empmgmtapp.di.application.AppModule;
import com.techdemos.empmgmtapp.di.application.DaggerAppComponent;

import java.util.Locale;


/**
 * Created by nestingbits on 7/12/2017.
 */

public class App extends Application {
    private static final String TAG=App.class.getSimpleName();
    private AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
//                .cardiotrackModule(new CardiotrackModule())
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}