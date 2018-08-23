package com.jp.app.common.controller;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.jp.app.injector.scope.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule {

    @Provides
    @PerActivity
    static Resources resources(Activity activity) {
        return activity.getResources();
    }

    @Provides
    @PerActivity
    static Bundle activityExtras(Activity activity) {
        return activity.getIntent() != null && activity.getIntent().getExtras() != null ? activity.getIntent().getExtras() : new Bundle();
    }

    @Provides
    @PerActivity
    static BaseActivity baseActivity(Activity activity) {
        return (BaseActivity) activity;
    }

    @Provides
    @PerActivity
    static FragmentManager activityFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }

}
