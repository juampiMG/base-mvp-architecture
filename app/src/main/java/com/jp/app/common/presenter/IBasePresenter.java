package com.jp.app.common.presenter;

import com.jp.app.common.view.IBaseView;

public interface IBasePresenter<V extends IBaseView> {

    void detachView();

    void pause();

    void onViewReady();

}
