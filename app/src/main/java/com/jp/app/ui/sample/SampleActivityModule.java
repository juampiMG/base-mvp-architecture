package com.jp.app.ui.sample;

import android.app.Activity;

import com.jp.app.common.controller.BaseActivityModule;
import com.jp.app.injector.scope.PerActivity;
import com.jp.app.injector.scope.PerFragment;
import com.jp.app.ui.sample.view.SampleFragment;
import com.jp.app.ui.sample.view.SampleFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {
        BaseActivityModule.class
})
public abstract class SampleActivityModule {

    @Binds
    @PerActivity
    abstract Activity activity(SampleActivity activity);

    @Binds
    @PerActivity
    abstract SampleFragment.FragmentCallback fragmentCallback(SampleActivity activity);

    @PerFragment
    @ContributesAndroidInjector(modules = SampleFragmentModule.class)
    abstract SampleFragment fragmentInjector();

}
