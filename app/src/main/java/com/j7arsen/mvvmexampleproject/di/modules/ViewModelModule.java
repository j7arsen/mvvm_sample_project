package com.j7arsen.mvvmexampleproject.di.modules;

import com.j7arsen.mvvmexampleproject.main.people.IPeopleContract;
import com.j7arsen.mvvmexampleproject.main.people.adapter.IPeopleItemContract;
import com.j7arsen.mvvmexampleproject.main.people.adapter.viewmodel.PeopleItemViewModel;
import com.j7arsen.mvvmexampleproject.main.people.viewmodel.PeopleViewModel;
import com.j7arsen.mvvmexampleproject.main.peopledetail.IPeopleDetailContract;
import com.j7arsen.mvvmexampleproject.main.peopledetail.viewmodel.PeopleDetailViewModel;
import com.j7arsen.mvvmexampleproject.view.ILogViewContract;
import com.j7arsen.mvvmexampleproject.view.LogViewViewModel;

import dagger.Binds;
import dagger.Module;

/**
 * Created by j7ars on 24.05.2017.
 */
@Module
public abstract class ViewModelModule {

    // Activities
    @Binds
    abstract IPeopleContract.ViewModel bindPeopleViewModel(PeopleViewModel peopleViewModel);

    @Binds
    abstract IPeopleDetailContract.ViewModel bindPeopleDetailViewModel(PeopleDetailViewModel peopleDetailViewModel);
    //Fragments

    //Service

    //View
    @Binds
    abstract ILogViewContract.ViewModel bindLogViewViewModel(LogViewViewModel logViewViewModel);

    //View Holder
    @Binds
    abstract IPeopleItemContract.ViewModel bindPeopleItemViewModel(PeopleItemViewModel peopleItemViewModel);

}
