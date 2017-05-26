package com.j7arsen.mvvmexampleproject.di.components;

import android.content.Context;
import android.content.res.Resources;

import com.j7arsen.mvvmexampleproject.di.modules.ApplicationModule;
import com.j7arsen.mvvmexampleproject.di.modules.NetModule;
import com.j7arsen.mvvmexampleproject.di.qualifier.ApplicationContext;
import com.j7arsen.mvvmexampleproject.di.scopes.PerApplication;
import com.j7arsen.mvvmexampleproject.managers.DataManager;
import com.j7arsen.mvvmexampleproject.managers.RequestManager;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by j7ars on 24.05.2017.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context applicationContext();
    Resources resources();

    Retrofit retrofit();

    RequestManager requestManager();
    DataManager dataManager();

}
