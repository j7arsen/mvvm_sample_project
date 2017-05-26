package com.j7arsen.mvvmexampleproject.di.components;

import com.j7arsen.mvvmexampleproject.di.modules.ActivityModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModelModule;
import com.j7arsen.mvvmexampleproject.di.scopes.PerActivity;
import com.j7arsen.mvvmexampleproject.main.people.view.PeopleActivity;
import com.j7arsen.mvvmexampleproject.main.peopledetail.view.PeopleDetailActivity;

import dagger.Subcomponent;

/**
 * Created by j7ars on 24.05.2017.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModelModule.class})
public interface ActivityComponent {

    //inject activity
    void inject(PeopleActivity peopleActivity);
    void inject(PeopleDetailActivity peopleDetailActivity);
}
