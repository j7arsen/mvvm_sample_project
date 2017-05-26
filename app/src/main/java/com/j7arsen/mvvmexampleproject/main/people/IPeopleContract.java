package com.j7arsen.mvvmexampleproject.main.people;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.viewmodel.BaseAdapterMvvmViewModel;

/**
 * Created by j7ars on 25.05.2017.
 */

public interface IPeopleContract {

    interface View extends IMvvmView {

    }

    interface ViewModel extends BaseAdapterMvvmViewModel<View> {
        void onClickFab(android.view.View view);
        ObservableInt getPeopleProgress();
        ObservableInt getPeopleRecycler();
        ObservableInt getPeopleLabel();
        ObservableField<String> getMessageLabel();
    }

}
