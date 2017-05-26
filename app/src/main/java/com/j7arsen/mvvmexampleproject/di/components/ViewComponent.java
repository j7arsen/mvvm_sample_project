package com.j7arsen.mvvmexampleproject.di.components;

import com.j7arsen.mvvmexampleproject.di.modules.ViewModelModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModule;
import com.j7arsen.mvvmexampleproject.di.scopes.PerView;
import com.j7arsen.mvvmexampleproject.view.LogView;

import dagger.Subcomponent;

/**
 * Created by j7ars on 24.05.2017.
 */
@PerView
@Subcomponent(modules = {ViewModule.class, ViewModelModule.class})
public interface ViewComponent {

    //inject view
    void inject(LogView logView);
}
