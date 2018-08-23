package com.jp.app.injector.module;

import com.jp.app.injector.scope.PerActivity;
import com.jp.app.ui.sample.SampleActivity;
import com.jp.app.ui.sample.SampleActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectorModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {SampleActivityModule.class})
    abstract SampleActivity sampleActivity();

}
