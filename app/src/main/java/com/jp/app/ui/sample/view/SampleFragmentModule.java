package com.jp.app.ui.sample.view;


import android.app.Fragment;

import com.jp.app.common.view.BaseFragmentModule;
import com.jp.app.injector.scope.PerFragment;
import com.jp.app.ui.sample.presenter.SamplePresenterModule;

import dagger.Binds;
import dagger.Module;


@Module(includes = {
        BaseFragmentModule.class,
        SamplePresenterModule.class
})
public abstract class SampleFragmentModule {

    @Binds
    @PerFragment
    abstract Fragment fragment(SampleFragment fragment);

    @Binds
    @PerFragment
    abstract ISampleView view(SampleFragment fragment);
}
