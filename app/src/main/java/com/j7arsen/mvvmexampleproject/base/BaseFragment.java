package com.j7arsen.mvvmexampleproject.base;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j7arsen.mvvmexampleproject.BR;
import com.j7arsen.mvvmexampleproject.app.MVVMApp;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.base.viewmodel.EmptyViewModel;
import com.j7arsen.mvvmexampleproject.di.components.ConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.DaggerConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.FragmentComponent;
import com.j7arsen.mvvmexampleproject.di.modules.FragmentModule;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

/**
 * Created by j7ars on 24.05.2017.
 */

public abstract class BaseFragment<B extends ViewDataBinding, V extends IMvvmViewModel> extends Fragment {

    private static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    private static final LongSparseArray<ConfigPersistentComponent> sComponentsArray =
            new LongSparseArray<>();
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private FragmentComponent mFragmentComponent;
    private long mFragmentId;

    protected B mBinding;
    @Inject
    protected V mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_FRAGMENT_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (sComponentsArray.get(mFragmentId) == null) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MVVMApp.get(
                            getActivity()).getComponent())
                    .build();
            sComponentsArray.put(mFragmentId, configPersistentComponent);
        } else {
            configPersistentComponent = sComponentsArray.get(mFragmentId);
        }
        mFragmentComponent = configPersistentComponent.fragmentComponent(new FragmentModule(this));
        inject(mFragmentComponent);
    }

    protected abstract void inject(FragmentComponent fragmentComponent);

    /* Sets the content view, creates the binding and attaches the view to the view model */
    protected final View setAndBindContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
        if (mViewModel == null) {
            throw new IllegalStateException("viewModel must already be set via injection");
        }
        mBinding = DataBindingUtil.inflate(inflater, layoutResID, container, false);
        mBinding.setVariable(BR.vm, mViewModel);

        try {
            mViewModel.attachView((IMvvmView) this, savedInstanceState);
        } catch (ClassCastException e) {
            if (!(mViewModel instanceof EmptyViewModel)) {
                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + mViewModel.getClass().getSimpleName());
            }
        }

        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId);
        if (mViewModel != null) {
            mViewModel.saveInstanceState(outState);
        }
    }

    public FragmentComponent fragmentComponent() {
        return mFragmentComponent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mViewModel != null) {
            mViewModel.detachView();
        }
        mBinding = null;
        mViewModel = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!getActivity().isChangingConfigurations()) {
            sComponentsArray.remove(mFragmentId);
        }
        mFragmentComponent = null;
        super.onDestroy();
    }

}
