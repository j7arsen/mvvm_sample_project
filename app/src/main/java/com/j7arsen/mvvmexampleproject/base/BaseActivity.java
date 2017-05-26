package com.j7arsen.mvvmexampleproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.LongSparseArray;

import com.j7arsen.mvvmexampleproject.BR;
import com.j7arsen.mvvmexampleproject.app.MVVMApp;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.base.viewmodel.EmptyViewModel;
import com.j7arsen.mvvmexampleproject.di.components.ActivityComponent;
import com.j7arsen.mvvmexampleproject.di.components.ConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.DaggerConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.modules.ActivityModule;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

/**
 * Created by j7ars on 24.05.2017.
 */

public abstract class BaseActivity<B extends ViewDataBinding, V extends IMvvmViewModel> extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";

    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent> sComponentsArray =
            new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;

    protected B mBinding;
    @Inject
    protected V mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (sComponentsArray.get(mActivityId) == null) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MVVMApp.get(this).getComponent())
                    .build();
            sComponentsArray.put(mActivityId, configPersistentComponent);
        } else {
            configPersistentComponent = sComponentsArray.get(mActivityId);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
        setupComponent();
    }

    protected abstract void setupComponent();

    /* Sets the content view, creates the binding and attaches the view to the view model */
    protected final void setAndBindContentView(@Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
        if(mViewModel == null) { throw new IllegalStateException("viewModel must already be set via injection"); }
        mBinding = DataBindingUtil.setContentView(this, layoutResID);
        mBinding.setVariable(BR.vm, mViewModel);

        try {
            mViewModel.attachView((IMvvmView) this, savedInstanceState);
        } catch(ClassCastException e) {
            if (!(mViewModel instanceof EmptyViewModel)) {
                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + mViewModel.getClass().getSimpleName());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isChangingConfigurations()) {
            sComponentsArray.remove(mActivityId);
        }
        if(mViewModel != null) {
            mViewModel.detachView();
        }
        mBinding = null;
        mViewModel = null;
        mActivityComponent = null;
    }

}
