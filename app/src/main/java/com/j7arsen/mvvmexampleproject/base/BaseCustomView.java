package com.j7arsen.mvvmexampleproject.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.j7arsen.mvvmexampleproject.BR;
import com.j7arsen.mvvmexampleproject.app.MVVMApp;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.base.viewmodel.EmptyViewModel;
import com.j7arsen.mvvmexampleproject.di.components.ConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.DaggerConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.ViewComponent;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModule;

import javax.inject.Inject;

/**
 * Created by j7ars on 24.05.2017.
 */

public abstract class BaseCustomView<B extends ViewDataBinding, V extends IMvvmViewModel> extends FrameLayout{

    private ViewComponent mViewComponent;

    protected B mBinding;
    @Inject
    protected V mViewModel;

    public BaseCustomView(Context context) {
        super(context);
        init(context);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected final ViewComponent viewComponent() {
        if (mViewComponent == null) {
            ConfigPersistentComponent configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MVVMApp.get(
                           this.getContext()).getComponent())
                    .build();
            mViewComponent = configPersistentComponent.viewComponent(new ViewModule(this));
        }

        return mViewComponent;
    }

    protected abstract void init(Context context);

    protected final View bindContentView(@NonNull Context context, @LayoutRes int layoutResID) {
        if (mViewModel == null) {
            throw new IllegalStateException("viewModel must already be set via injection");
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, layoutResID, this, false);
        mBinding.setVariable(BR.vm, mViewModel);

        try {
            mViewModel.attachView((IMvvmView) this, null);
        } catch (ClassCastException e) {
            if (!(mViewModel instanceof EmptyViewModel)) {
                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + mViewModel.getClass().getSimpleName());
            }
        }

        return mBinding.getRoot();
    }

    public final V viewModel() {
        return mViewModel;
    }

    public final void executePendingBindings() {
        if (mBinding != null) {
            mBinding.executePendingBindings();
        }
    }
}
