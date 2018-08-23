package com.jp.data.network.retrofit.service;

import com.jp.data.entities.sample.ResultSampleEntity;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RestServices {

    @GET("games")
    Single<ResultSampleEntity> getSamples();

}
