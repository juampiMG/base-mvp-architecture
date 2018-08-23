package com.jp.app.injector.module;


import com.jp.data.repository.SampleRepository;
import com.jp.domain.repository.ISampleRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract ISampleRepository sampleRepository(SampleRepository repository);
}
