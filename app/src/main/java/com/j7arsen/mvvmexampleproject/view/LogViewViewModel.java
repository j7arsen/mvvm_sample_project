package com.j7arsen.mvvmexampleproject.view;

import android.util.Log;
import android.view.View;

import com.j7arsen.mvvmexampleproject.base.viewmodel.CustomViewViewModel;

import javax.inject.Inject;

/**
 * Created by j7ars on 25.05.2017.
 */

public class LogViewViewModel extends CustomViewViewModel<ILogViewContract.View> implements ILogViewContract.ViewModel {

    @Inject
    public LogViewViewModel(View view){
        super(view);
    }

    @Override
    public void onTestClick(View view) {
        Log.i("Test Click", "Test Click Event");
        getView();
    }

    @Override
    public void onTest2Click(View view) {
        Log.i("Test2 Click", "Test2 Click Event");
    }

    @Override
    public void onAttached() {
        Log.i("Attached", "Attached");
    }

    @Override
    public void onDetached() {
        Log.i("Detach", "Detach");
    }
}
