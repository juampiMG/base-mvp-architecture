package com.jp.data.network.interceptor;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    public static final String USER_AGENT = "User-Agent";
    public static final String ACCEPT = "Accept";
    public static final String CONTENT_TYPE = "Content-Type";

    public RequestInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String url = original.url().toString();

        // Customize the request
        Request.Builder request = original.newBuilder()
                .header(USER_AGENT, "Apache-HttpClient/4.3.1")
                .header(ACCEPT, "application/json")
                .header(CONTENT_TYPE, "application/json")
                .url(url)
                .method(original.method(), original.body());


        return chain.proceed(request.build());

    }

}
