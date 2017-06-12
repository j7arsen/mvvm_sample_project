package com.j7arsen.mvvmexampleproject.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.j7arsen.mvvmexampleproject.base.BaseIntentService;
import com.j7arsen.mvvmexampleproject.di.components.ServiceComponent;

/**
 * Created by j7ars on 25.05.2017.
 */

public class LogService extends BaseIntentService {

    public static void startService(Context context) {
        Intent intent = new Intent(context, LogService.class);
        context.startService(intent);
    }

    public LogService() {
        super("LogService");
    }

    @Override
    protected void inject(ServiceComponent serviceComponent) {
        serviceComponent.inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        super.onHandleIntent(intent);
        Log.i("LogService", "LogService RUN");
    }



}
