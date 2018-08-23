package com.jp.data.repository;

import com.jp.data.entities.mapper.SampleEntityMapper;
import com.jp.data.network.retrofit.service.RestServices;
import com.jp.domain.model.SampleDomain;
import com.jp.domain.repository.ISampleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.jp.data.ServerMock.getListDomain;


public class SampleRepository implements ISampleRepository {

    @Inject
    SampleEntityMapper mSampleEntityMapper;


    RestServices mRestServices;

    @Inject
    public SampleRepository(RestServices gateway){
        mRestServices = gateway;
    }


    @Override
    public Single<List<SampleDomain>> getSamples() {
        return Single.just(getListDomain());
    }
}
