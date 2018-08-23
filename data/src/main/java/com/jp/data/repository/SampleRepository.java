package com.jp.data.repository;

import com.jp.data.entities.mapper.SampleEntityMapper;
import com.jp.data.entities.sample.ResultSampleEntity;
import com.jp.data.network.retrofit.service.RestServices;
import com.jp.domain.model.SampleDomain;
import com.jp.domain.repository.ISampleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class SampleRepository implements ISampleRepository {

    @Inject
    SampleEntityMapper mSampleEntityMapper;


    RestServices mRestServices;

    @Inject
    public SampleRepository(RestServices restServices){
        mRestServices = restServices;
    }


    @Override
    public Single<List<SampleDomain>> getSamples() {
        return mRestServices.getSamples().flatMap(new Function<ResultSampleEntity, SingleSource<? extends List<SampleDomain>>>() {
            @Override
            public SingleSource<? extends List<SampleDomain>> apply(ResultSampleEntity resultEntities) throws Exception {
                return Single.just(mSampleEntityMapper.transform(resultEntities.data));
            }
        });
    }

}
