package com.jp.domain.repository;

import com.jp.domain.model.SampleDomain;

import java.util.List;

import io.reactivex.Single;

public interface ISampleRepository {
    Single<List<SampleDomain>> getSamples();
}
