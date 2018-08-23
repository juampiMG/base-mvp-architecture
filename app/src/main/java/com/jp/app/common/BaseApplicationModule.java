package com.jp.app.common;

import android.app.Application;
import android.content.Context;

import com.jp.app.SampleApplication;
import com.jp.app.injector.module.NetworkModule;
import com.jp.app.injector.module.RepositoryModule;
import com.jp.app.injector.module.UseCaseModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(
        includes = {
                RepositoryModule.class,
                UseCaseModule.class,
                NetworkModule.class
        }
)
public abstract class BaseApplicationModule {

    @Binds
    @Singleton
    abstract Application application(SampleApplication application);

    @Binds
    @Singleton
    abstract Context applicationContext(Application application);

}
