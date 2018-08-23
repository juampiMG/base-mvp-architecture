package com.jp.domain.interactor.impl;


import com.jp.domain.interactor.IGetSampleUseCase;
import com.jp.domain.model.SampleDomain;
import com.jp.domain.repository.ISampleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetSampleUseCase implements IGetSampleUseCase {

    @Inject
    ISampleRepository mSampleRepository;

    @Inject
    public GetSampleUseCase() {
    }

    @Override
    public Single<List<SampleDomain>> execute() {
        return mSampleRepository.getSamples();
    }
}
