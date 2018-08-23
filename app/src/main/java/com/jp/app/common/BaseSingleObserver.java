package com.jp.app.common;

import android.content.Context;

import com.jp.app.R;
import com.jp.app.common.presenter.BasePresenter;
import com.jp.data.exception.AppException;
import com.jp.data.network.retrofit.exception.RetrofitException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import retrofit2.Response;

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {

    private Context mContext;

    public BaseSingleObserver(Context context) {
        this.mContext = context;
    }

    public BaseSingleObserver(BasePresenter presenter) {
        this(presenter.getContext());
    }

    @Override
    public void onError(@NonNull Throwable e) {
        handleError(e);
    }

    protected abstract void onError(int code, String title, String description);

    protected void handleError(Throwable throwable) {
        int code = 0;
        String title = mContext.getString(R.string.oh_hell);
        String message = mContext.getString(R.string.default_error);

        if (throwable instanceof RetrofitException) {
            handleRetrofitException((RetrofitException) throwable);
        } else if (throwable instanceof AppException) {
            handleAppException((AppException) throwable);
        } else {
            onError(code, title, message);
        }
    }

    protected void handleRetrofitException(RetrofitException exception) {
        final String url = exception.getUrl();
        final Response response = exception.getResponse();
        final Throwable cause = exception.getCause();
        int code = response != null ? response.code() : 0;
        String title =  mContext.getString(R.string.oh_hell);
        String message = mContext.getString(R.string.default_error);
        if (cause instanceof ConnectException) {
            message = mContext.getString(R.string.default_error);
        } else if (cause instanceof SocketTimeoutException) {
            message = mContext.getString(R.string.default_error);
        } else {
            try {
                JSONObject jsonError = exception.getErrorBodyAs(JSONObject.class);
                if (jsonError != null) {
                    try {
                        if (jsonError.has("code") && jsonError.has("description")) {
                            code = jsonError.getInt("code");
                            message = jsonError.getString("description");
                        }
                    } catch (JSONException e) {
                    }
                }
            } catch (IOException e) {
            }
        }
        onError(code, title, message);
    }

    protected void handleAppException(AppException exception) {
        int code = exception.getCode();
        String title = mContext.getString(R.string.oh_hell);
        String message = exception.getMessage();
        onError(code, title, message);
    }
}
