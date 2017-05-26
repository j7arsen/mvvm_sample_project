package com.j7arsen.mvvmexampleproject.di.modules;

import android.support.v7.widget.RecyclerView;

import com.j7arsen.mvvmexampleproject.di.scopes.PerViewHolder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by j7ars on 24.05.2017.
 */
@Module
public class ViewHolderModule {

    private RecyclerView.ViewHolder mViewHolder;

    public ViewHolderModule(RecyclerView.ViewHolder viewHolder){
        this.mViewHolder = viewHolder;
    }

    @Provides
    @PerViewHolder
    RecyclerView.ViewHolder provideViewHolder(){
        return mViewHolder;
    }

}
