package com.j7arsen.mvvmexampleproject.base;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.j7arsen.mvvmexampleproject.app.MVVMApp;
import com.j7arsen.mvvmexampleproject.di.components.ConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.DaggerConfigPersistentComponent;
import com.j7arsen.mvvmexampleproject.di.components.ServiceComponent;
import com.j7arsen.mvvmexampleproject.di.modules.ServiceModule;

/**
 * Created by j7ars on 25.05.2017.
 */

public abstract class BaseIntentService extends IntentService {

    private ServiceComponent mServiceComponent;

    public BaseIntentService(String serviceName){
        super(serviceName);
    }

    protected final ServiceComponent serviceComponent() {
        if (mServiceComponent == null) {
            ConfigPersistentComponent configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MVVMApp.get(this).getComponent())
                    .build();
            mServiceComponent = configPersistentComponent.serviceComponent(new ServiceModule(this));
        }

        return mServiceComponent;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        inject(serviceComponent());
    }

    protected abstract void inject(ServiceComponent serviceComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mServiceComponent = null;
    }
}
