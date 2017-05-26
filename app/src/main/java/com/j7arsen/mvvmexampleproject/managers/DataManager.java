package com.j7arsen.mvvmexampleproject.managers;

import com.j7arsen.mvvmexampleproject.di.scopes.PerApplication;
import com.j7arsen.mvvmexampleproject.network.response.PeopleResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by j7ars on 25.05.2017.
 */
@PerApplication
public class DataManager {

    private RequestManager mRequestManager;

    @Inject
    public DataManager(RequestManager requestManager){
        this.mRequestManager = requestManager;
    }

    public Observable<PeopleResponse> fetchPeople(){
        return mRequestManager.fetchPeople();
    }

}
