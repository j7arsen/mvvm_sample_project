package com.j7arsen.mvvmexampleproject.di.components;

import com.j7arsen.mvvmexampleproject.di.modules.ServiceModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModelModule;
import com.j7arsen.mvvmexampleproject.di.scopes.PerService;
import com.j7arsen.mvvmexampleproject.service.LogService;

import dagger.Subcomponent;

/**
 * Created by j7ars on 24.05.2017.
 */
@PerService
@Subcomponent(modules ={ServiceModule.class, ViewModelModule.class})
public interface ServiceComponent {

    //inject service
    void inject(LogService logService);

}
