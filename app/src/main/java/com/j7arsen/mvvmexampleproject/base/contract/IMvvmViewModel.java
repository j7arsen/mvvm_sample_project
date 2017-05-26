package com.j7arsen.mvvmexampleproject.base.contract;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by j7ars on 24.05.2017.
 */

public interface IMvvmViewModel<V extends IMvvmView> extends Observable {

    void attachView(V view, Bundle savedInstanceState);
    void detachView();

    void saveInstanceState(@NonNull Bundle outState);

}
