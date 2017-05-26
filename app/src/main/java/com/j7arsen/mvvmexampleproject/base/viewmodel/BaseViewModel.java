package com.j7arsen.mvvmexampleproject.base.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;

import com.j7arsen.mvvmexampleproject.base.MvvmViewNotAttachedException;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.managers.DataManager;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by j7ars on 25.05.2017.
 */

public abstract class BaseViewModel<V extends IMvvmView> extends BaseObservable implements IMvvmViewModel<V> {

    private V mMvvmView;

    //list of disposable
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Inject
    protected DataManager mDataManager;

    @Override
    public void attachView(V mvvmView, @Nullable Bundle savedInstanceState) {
        this.mMvvmView = mvvmView;
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void detachView() {
        mMvvmView = null;
        undisposableAll();
    }

    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) {
    }

    public void saveInstanceState(Bundle outState) {
    }

    public final boolean isViewAttached() {
        return mMvvmView != null;
    }

    public final V getView() {
        checkViewAttached();
        return mMvvmView;
    }

    public final void checkViewAttached() {
        if (!isViewAttached()) throw new MvvmViewNotAttachedException();
    }

    public void addDisposable(Disposable disposable){
        mCompositeDisposable.add(disposable);
    }

    public void undisposable(Disposable disposable){
        if(mCompositeDisposable.size() != 0){
            if(!disposable.isDisposed()) {
                mCompositeDisposable.remove(disposable);
            }
        }
    }

    public void undisposableAll(){
        if(!mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }

}
