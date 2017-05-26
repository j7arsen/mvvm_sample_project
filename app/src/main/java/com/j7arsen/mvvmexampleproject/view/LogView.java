package com.j7arsen.mvvmexampleproject.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.j7arsen.mvvmexampleproject.R;
import com.j7arsen.mvvmexampleproject.base.BaseCustomView;
import com.j7arsen.mvvmexampleproject.databinding.DebugViewBinding;

/**
 * Created by j7ars on 25.05.2017.
 */

public class LogView extends BaseCustomView<DebugViewBinding, ILogViewContract.ViewModel> implements ILogViewContract.View{

    public LogView(Context context) {
        super(context);
    }

    public LogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        viewComponent().inject(this);
        addView(bindContentView(context, R.layout.debug_view));
    }

}
