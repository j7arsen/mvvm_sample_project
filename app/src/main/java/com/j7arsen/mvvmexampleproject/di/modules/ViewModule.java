package com.j7arsen.mvvmexampleproject.di.modules;

import android.view.View;

import com.j7arsen.mvvmexampleproject.di.scopes.PerView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by j7ars on 24.05.2017.
 */
@Module
public class ViewModule {

    private final View mView;

    public ViewModule(View view){
        this.mView = view;
    }

    @Provides
    @PerView
    View provideView(){
        return mView;
    }

}
