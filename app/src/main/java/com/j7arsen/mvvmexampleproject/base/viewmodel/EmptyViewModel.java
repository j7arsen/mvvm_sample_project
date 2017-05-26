package com.j7arsen.mvvmexampleproject.base.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;

import javax.inject.Inject;

/**
 * Created by j7ars on 24.05.2017.
 */

public class EmptyViewModel extends BaseObservable implements IMvvmViewModel<IMvvmView>{

    @Inject
    public EmptyViewModel() {
    }

    @Override
    public void attachView(IMvvmView view, Bundle savedInstanceState) {
    }

    @Override
    public void detachView() {
    }

    @Override
    public void saveInstanceState(@NonNull Bundle outState) {
    }

}