package com.j7arsen.mvvmexampleproject.di.modules;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import com.j7arsen.mvvmexampleproject.di.qualifier.ChildFragmentManagerContext;
import com.j7arsen.mvvmexampleproject.di.qualifier.FragmentContext;
import com.j7arsen.mvvmexampleproject.di.qualifier.FragmentManagerContext;
import com.j7arsen.mvvmexampleproject.di.scopes.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by j7ars on 24.05.2017.
 */
@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @PerFragment
    Activity provideActivity(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @FragmentContext
    Context provideContext(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @FragmentManagerContext
    FragmentManager provideFragmentManager(){
        return mFragment.getFragmentManager();
    }

    @PerFragment
    @ChildFragmentManagerContext
    FragmentManager provideChildFragmentManager(){
        return mFragment.getChildFragmentManager();
    }


}
