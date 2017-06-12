package com.j7arsen.mvvmexampleproject.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.j7arsen.mvvmexampleproject.BR;
import com.j7arsen.mvvmexampleproject.app.MVVMApp;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.base.viewmodel.EmptyViewModel;
import com.j7arsen.mvvmexampleproject.di.components.ConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.DaggerConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.ServiceComponent;
import com.j7arsen.mvvmexampleproject.di.components.ViewHolderComponent;
import com.j7arsen.mvvmexampleproject.di.modules.ViewHolderModule;

import javax.inject.Inject;

/**
 * Created by j7ars on 24.05.2017.
 */

public abstract class BaseViewHolder<B extends ViewDataBinding, V extends IMvvmViewModel> extends RecyclerView.ViewHolder {

    private ViewHolderComponent mViewHolderComponent;

    protected B mBinding;
    @Inject
    protected V mViewModel;

    protected final View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    protected final ViewHolderComponent viewHolderComponent() {
        if (mViewHolderComponent == null) {
            ConfigPersistentComponent configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MVVMApp.get(
                            itemView.getContext()).getComponent())
                    .build();
            mViewHolderComponent = configPersistentComponent.viewHolderComponent(new ViewHolderModule(this));
        }

        return mViewHolderComponent;
    }

    protected final void bindContentView(@NonNull View view) {
        if (mViewModel == null) {
            throw new IllegalStateException("viewModel must not be null and should be injected via viewHolderComponent().inject(this)");
        }
        mBinding = DataBindingUtil.bind(view);
        mBinding.setVariable(BR.vm, mViewModel);

        try {
            mViewModel.attachView((IMvvmView) this, null);
        } catch (ClassCastException e) {
            if (!(mViewModel instanceof EmptyViewModel)) {
                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + mViewModel.getClass().getSimpleName());
            }
        }
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
