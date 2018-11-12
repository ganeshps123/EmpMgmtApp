package com.techdemos.empmgmtapp.ui;

import android.support.v7.app.AppCompatActivity;

import com.techdemos.empmgmtapp.App;
import com.techdemos.empmgmtapp.di.controller.ControllerComponent;
import com.techdemos.empmgmtapp.di.controller.ControllerModule;

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private boolean mIsControllerComponentUsed = false;

    public ControllerComponent getControllerComponent(){
        if (mIsControllerComponentUsed) {
            throw new IllegalStateException("must not use ControllerComponent more than once");
        }
        mIsControllerComponentUsed = true;

        return ((App)getApplication()).getAppComponent().controllerComponent(new ControllerModule(this));
    }
}
