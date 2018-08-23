package com.jp.app.common.presenter;

import android.content.Context;

import com.jp.app.common.controller.BaseActivity;
import com.jp.app.common.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePresenter<TView extends IBaseView> implements IBasePresenter {

    @Inject
    Context mContext;

    protected TView mView;


    private CompositeDisposable mCompositeDisposable;


    public BasePresenter(TView view) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void detachView() {
        mView = null;
    }

    @Override
    public void pause() {
        if (mCompositeDisposable != null) {
            removeAllDisposables();
        }
    }

    protected void showError(String title, String description, BaseActivity.actionOnError actionOnError) {
        if (mView != null) {
            mView.showError(title, description, actionOnError);
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed())
            mCompositeDisposable = new CompositeDisposable();
        return mCompositeDisposable;
    }

    public void addDisposable(Disposable disposable) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    public void removeDisposable(Disposable disposable) {
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
            if (mCompositeDisposable != null) {
                mCompositeDisposable.remove(disposable);
            }
        }
    }

    public Context getContext() {
        return mContext;
    }

    public void removeAllDisposables() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public boolean hasDisposables() {
        if (mCompositeDisposable != null) {
            return mCompositeDisposable.size() > 0;
        } else {
            return false;
        }
    }

    public void setView (TView view) {
        mView = view;
    }

    public abstract void onViewReady();


}
