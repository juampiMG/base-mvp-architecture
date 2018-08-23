package com.jp.app;

import android.app.Activity;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.jp.app.injector.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class SampleApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        initCompatVector();
        initDaggerApplicationComponent();
    }

    // =============== Injectors ===================================================================

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    // =============== Support methods =============================================================

    private void initCompatVector() {
        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }
    }

    protected void initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this);
    }

}

