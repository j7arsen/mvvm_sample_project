package com.j7arsen.mvvmexampleproject.di.components;

import com.j7arsen.mvvmexampleproject.di.modules.FragmentModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModelModule;
import com.j7arsen.mvvmexampleproject.di.scopes.PerFragment;

import dagger.Subcomponent;

/**
 * Created by j7ars on 24.05.2017.
 */
@PerFragment
@Subcomponent(modules = {FragmentModule.class, ViewModelModule.class})
public interface FragmentComponent {

    //inject fragment

}
