package com.jp.app.injector.module;

import com.jp.app.BuildConfig;
import com.jp.data.Constants;
import com.jp.data.network.interceptor.RequestInterceptor;
import com.jp.data.network.retrofit.callAdapter.RxErrorHandlingCallAdapterFactory;
import com.jp.data.network.retrofit.service.RestServices;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {


    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return logging;
    }

    @Singleton
    @Provides
    public RequestInterceptor provideRequestInterceptor() {
        return new RequestInterceptor ();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, RequestInterceptor requestInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(requestInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);

        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    @Singleton
    @Provides
    public RestServices provideRestServices (OkHttpClient okHttpClient) {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
        return restAdapter.create(RestServices.class);
    }
}
