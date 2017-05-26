package com.j7arsen.mvvmexampleproject.di.components;

import com.j7arsen.mvvmexampleproject.di.modules.ActivityModule;
import com.j7arsen.mvvmexampleproject.di.modules.FragmentModule;
import com.j7arsen.mvvmexampleproject.di.modules.ServiceModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewHolderModule;
import com.j7arsen.mvvmexampleproject.di.modules.ViewModule;
import com.j7arsen.mvvmexampleproject.di.scopes.ConfigPersistent;

import dagger.Component;

/**
 * Created by j7ars on 24.05.2017.
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

    ServiceComponent serviceComponent(ServiceModule serviceModule);

    ViewHolderComponent viewHolderComponent(ViewHolderModule viewHolderModule);

    ViewComponent viewComponent(ViewModule viewModule);

}
