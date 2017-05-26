package com.j7arsen.mvvmexampleproject.base.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;

/**
 * Created by j7ars on 25.05.2017.
 */

public abstract class CustomViewViewModel<V extends IMvvmView> extends BaseViewModel<V> {

    private final View view;

    public CustomViewViewModel(View view) {
        this.view = view;
        this.view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                onAttached();
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                onDetached();
            }
        });
    }

    @NonNull
    public Context getContext() {
        if (view != null) {
            return view.getContext();
        }
        throw new IllegalStateException("No view attached");
    }

    public abstract void onDetached();
    public abstract void onAttached();
}
