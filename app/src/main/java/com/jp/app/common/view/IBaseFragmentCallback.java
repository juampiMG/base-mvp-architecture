package com.jp.app.common.view;

import com.jp.app.common.controller.BaseActivity;

public interface IBaseFragmentCallback {

    void showError(String title, String message, BaseActivity.actionOnError action);

    void showMessage(String title, String message);

    void showLoading();

    void hideLoading();

}
