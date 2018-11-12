package com.techdemos.empmgmtapp.di.application;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.GsonBuilder;
import com.techdemos.empmgmtapp.api.EmployeeApi;
import com.techdemos.empmgmtapp.db.AppDb;
import com.techdemos.empmgmtapp.db.EmployeeDao;
import com.techdemos.empmgmtapp.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {
    private static final String TAG="AppModule";
    private final Application mApp;
    private final AppDb mDb;

    public AppModule(final Application application) {
        this.mApp = application;
        mDb = Room.databaseBuilder(application.getApplicationContext(), AppDb.class,"EmpDB.db")
                .fallbackToDestructiveMigration()
                .build();
//                allowMainThreadQueries().build();
    }

    @Provides @Singleton
    Application provideApplication(){
        return mApp;
    }

    @Provides @Singleton
    AppDb provideDb() {
        return mDb;
    }

    @Provides @Singleton
    EmployeeDao provideEmployeeDao(AppDb database){
        return database.employeeModel();
    }

    @Provides @Singleton
    EmployeeApi provideEmployeeService() {
        return new Retrofit.Builder()
//                .baseUrl("http://192.168.1.6:8080/DemoRestService/webapi/")
                .baseUrl("http://restdemo-env.cmczw7ckyf.ap-south-1.elasticbeanstalk.com/webapi/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(EmployeeApi.class);
    }
}
