package com.j7arsen.mvvmexampleproject.di.modules;

import android.app.Service;
import android.content.Context;

import com.j7arsen.mvvmexampleproject.di.qualifier.ServiceContext;
import com.j7arsen.mvvmexampleproject.di.scopes.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by j7ars on 24.05.2017.
 */
@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service){
        this.mService = service;
    }

    @Provides
    @PerService
    Service provideService() {
        return mService;
    }

    @Provides
    @PerService
    @ServiceContext
    Context provideContext(){
        return mService.getApplication();
    }

}
