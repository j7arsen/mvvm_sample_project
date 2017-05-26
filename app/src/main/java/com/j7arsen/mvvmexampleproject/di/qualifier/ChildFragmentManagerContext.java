package com.j7arsen.mvvmexampleproject.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by j7ars on 24.05.2017.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildFragmentManagerContext {
}
