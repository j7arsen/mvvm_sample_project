package com.j7arsen.mvvmexampleproject.managers;

import com.j7arsen.mvvmexampleproject.di.scopes.PerApplication;
import com.j7arsen.mvvmexampleproject.network.Urls;
import com.j7arsen.mvvmexampleproject.network.response.PeopleResponse;
import com.j7arsen.mvvmexampleproject.network.service.PeopleService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by j7ars on 25.05.2017.
 */
@PerApplication
public class RequestManager {

    private Retrofit mRetrofit;

    @Inject
    public RequestManager(Retrofit retrofit){
        this.mRetrofit = retrofit;
    }

    private <S> S createService(Class<S> serviceClass){
        return mRetrofit.create(serviceClass);
    }

    public Observable<PeopleResponse> fetchPeople(){
        return createService(PeopleService.class).fetchPeople(Urls.GET_PEOPLE_URL).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
