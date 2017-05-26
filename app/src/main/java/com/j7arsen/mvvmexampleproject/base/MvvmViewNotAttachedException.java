package com.j7arsen.mvvmexampleproject.base;

/**
 * Created by j7ars on 24.05.2017.
 */

public class MvvmViewNotAttachedException extends RuntimeException {

    public MvvmViewNotAttachedException() {
        super("Please call ViewModel.attachView(MvvmView) before requesting data to the ViewModel");
    }
}
