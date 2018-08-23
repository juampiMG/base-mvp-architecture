package com.jp.app.injector.component;

import com.jp.app.SampleApplication;
import com.jp.app.common.BaseApplicationModule;
import com.jp.app.injector.module.InjectorModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                BaseApplicationModule.class,
                InjectorModule.class
        }
)
public interface ApplicationComponent extends AndroidInjector<SampleApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<SampleApplication> {
    }

}
