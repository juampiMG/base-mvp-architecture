package com.jp.app.common.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jp.app.common.controller.BaseActivity;
import com.jp.app.common.presenter.IBasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public abstract class BaseFragment<TPresenter extends IBasePresenter, TCallback extends IBaseFragmentCallback> extends Fragment implements HasFragmentInjector, IBaseView {

    @Inject
    protected Context mContext;
    @Inject
    public TPresenter mPresenter;
    @Inject
    public TCallback mCallback;

    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;


    private Unbinder mUnbinder;
    private String mFragmentId;
    protected int mLayoutId;

    public BaseFragment() {
        Class fragmentClass = ((Object) this).getClass();
        mFragmentId = fragmentClass.getName();
    }


    // =============== HasFragmentInjector =========================================================

    @Override
    public void onAttach(Activity activity) {
        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidInjection.inject(this);
        }
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return mChildFragmentInjector;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutId = getLayoutId();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(mLayoutId, container, false);
        return view;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        /*
         * Bind the views here instead of in onViewCreated so that mView state changed listeners
         * are not invoked automatically without user interaction.
         *
         * If we bind before this method (e.g. onViewCreated), then any checked changed
         * listeners bound by ButterKnife will be invoked during fragment recreation (since
         * Android itself saves and restores the views' states. Take a look at this gist for a
         * concrete example: https://gist.github.com/vestrel00/982d585144423f728342787341fa001d
         *
         * The lifecycle order is as follows (same if added via xml or java or if retain
         * instance is true):
         *
         * onAttach
         * onCreateView
         * onViewCreated
         * onActivityCreated
         * onViewRestored
         * onStart
         * onResume
         *
         * Note that the onCreate (and other lifecycle events) are omitted on purpose. The
         * caveat to this approach is that views, listeners, and resources bound by
         * Butterknife will be null until onViewStatedRestored. Just be careful not to use any
         * objects bound using Butterknife before onViewRestored.
         *
         * Fragments that do not return a non-null View in onCreateView results in onViewCreated
         * and onViewRestored not being called. This means that Butterknife.bind will not get
         * called, which is completely fine because there is no View to bind. Furthermore, there is
         * no need to check if getView() returns null here because this lifecycle method only gets
         * called with a non-null View.
         */
        mUnbinder = ButterKnife.bind(this, getView());
        onViewLoaded(savedInstanceState, getView());
        mPresenter.onViewReady();
    }

    public void onViewLoaded(Bundle savedInstanceState, View view) {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        if (mCallback != null) {
            mCallback.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mCallback != null) {
            mCallback.hideLoading();
        }
    }

    @Override
    public void showError(String title, String message, BaseActivity.actionOnError actionOnError) {
        mCallback.showError(title, message, actionOnError);
    }

    public abstract int getLayoutId();


    public String getFragmentId() {
        return mFragmentId;
    }

    public TPresenter getPresenter() {
        return mPresenter;
    }

    public void setPresenter (TPresenter presenter) {
        mPresenter = presenter;
    }
}
