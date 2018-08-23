package com.jp.domain.interactor;

import com.jp.domain.model.SampleDomain;

import java.util.List;

import io.reactivex.Single;

public interface IGetSampleUseCase {

    Single<List<SampleDomain>> execute();
}
