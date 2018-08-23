package com.jp.app.injector.module;


import com.jp.domain.interactor.IGetSampleUseCase;
import com.jp.domain.interactor.impl.GetSampleUseCase;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract IGetSampleUseCase getSampleUseCase(GetSampleUseCase useCase);
}
