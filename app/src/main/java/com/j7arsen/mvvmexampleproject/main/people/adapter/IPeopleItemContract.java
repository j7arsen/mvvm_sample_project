package com.j7arsen.mvvmexampleproject.main.people.adapter;

import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.dataclasses.People;

/**
 * Created by j7ars on 25.05.2017.
 */

public interface IPeopleItemContract {

    interface View extends IMvvmView {
        void openDetailScreen(int position);
    }

    interface ViewModel extends IMvvmViewModel<View> {
        String getFullName();
        String getCell();
        String getMail();
        String getPictureProfile();
        void updatePeople(People people, int position);
        void onItemClick(android.view.View view);
    }

}
