package com.j7arsen.mvvmexampleproject.main.peopledetail;

import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;
import com.j7arsen.mvvmexampleproject.dataclasses.People;

/**
 * Created by j7ars on 25.05.2017.
 */

public interface IPeopleDetailContract {

    interface View extends IMvvmView {

    }

    interface ViewModel extends IMvvmViewModel<View> {
        void setPeople(People people);
        String getFullUserName();
        public String getUserName();
        String getEmail();
        int getEmailVisibility();
        String getCell();
        String getPictureProfile();
        String getAddress();
        String getGender();
        void onClickFab(android.view.View view);
    }

}
