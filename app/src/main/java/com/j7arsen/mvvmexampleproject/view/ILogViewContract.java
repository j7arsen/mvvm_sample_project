package com.j7arsen.mvvmexampleproject.view;

import com.j7arsen.mvvmexampleproject.base.contract.IMvvmView;
import com.j7arsen.mvvmexampleproject.base.contract.IMvvmViewModel;

/**
 * Created by j7ars on 25.05.2017.
 */

public interface ILogViewContract {

    interface View extends IMvvmView {

    }

    interface ViewModel extends IMvvmViewModel<ILogViewContract.View> {
        void onTestClick(android.view.View view);
        void onTest2Click(android.view.View view);
    }

}
