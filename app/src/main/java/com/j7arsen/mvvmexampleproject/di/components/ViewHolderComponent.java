package com.j7arsen.mvvmexampleproject.di.components;

import com.j7arsen.mvvmexampleproject.di.modules.ViewHolderModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModelModule;
import com.j7arsen.mvvmexampleproject.di.scopes.PerViewHolder;
import com.j7arsen.mvvmexampleproject.main.people.adapter.adapter.PeopleItemAdapter;

import dagger.Subcomponent;

/**
 * Created by j7ars on 24.05.2017.
 */
@PerViewHolder
@Subcomponent(modules = {ViewHolderModule.class, ViewModelModule.class})
public interface ViewHolderComponent {

    //inject view holder
    void inject(PeopleItemAdapter.PeopleItemViewHolder peopleItemViewHolder);

}
