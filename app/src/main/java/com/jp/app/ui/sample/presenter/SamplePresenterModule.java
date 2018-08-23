package com.jp.app.ui.sample.presenter;


import com.jp.app.common.presenter.BasePresenterModule;
import com.jp.app.injector.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

@Module(includes = BasePresenterModule.class)
public abstract class SamplePresenterModule {

    @Binds
    @PerFragment
    abstract ISamplePresenter presenter(SamplePresenter presenter);
}
