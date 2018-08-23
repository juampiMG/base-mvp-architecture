package com.jp.app.common.controller;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jp.app.R;
import com.jp.app.common.view.IBaseFragmentCallback;
import com.jp.app.ui.sample.SampleActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements HasFragmentInjector, IBaseFragmentCallback {

    private static final int DEFAULT_NUM_COUNT_BACK = 1;

    protected int layoutId;

    protected Fragment currentFragment;

    public enum actionOnError {
        CLOSE, NOTHING
    }

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Nullable
    @BindView(R.id.generic_loading)
    RelativeLayout loadingView;

    private Unbinder unbinder;
    private CompositeDisposable compositeDisposable;


    private Toast exitToast;
    private int defaultCountBackToExit;
    private int countBackToExit;

    private Handler handler = new Handler();
    private Runnable restartCountBackToExit = new Runnable() {
        public void run() {
            countBackToExit = defaultCountBackToExit;
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);


        super.onCreate(savedInstanceState);

        layoutId = getLayoutId();

        setContentView(layoutId);

        unbinder = ButterKnife.bind(this);

        if (defaultCountBackToExit == 0) {
            defaultCountBackToExit = DEFAULT_NUM_COUNT_BACK;
        }
        countBackToExit = defaultCountBackToExit;
    }

    // =============== HasFragmentInjector =========================================================

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return mFragmentInjector;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            removeAllDisposables();
        }
    }

    @Override
    public void onBackPressed() {
        manageBackPressed();

    }

    protected void manageBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        }
        if (this instanceof SampleActivity) {
            if (exitToast == null) {
                exitToast = Toast.makeText(this, getString(R.string.count_back_exit_message), Toast.LENGTH_SHORT);
            }
            if (countBackToExit > 0) {
                countBackToExit--;
                removeCallbacks();
                handler.postDelayed(restartCountBackToExit, 2000);
                exitToast.show();
                getFragmentManager().popBackStack();
            } else {
                exitToast.cancel();
                super.onBackPressed();
            }

        } else {
            super.onBackPressed();
        }


    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    private void removeCallbacks() {
        if (handler != null) {
            handler.removeCallbacks(restartCountBackToExit);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                manageBackPressed();
                break;
        }
        return true;
    }


    @Override
    public void showError(String title, String message, actionOnError action) {
        showErrorDialog(title, message, action);
    }

    @Override
    public void showMessage(String title, String message) {
        showMessageDialog(title, message);
    }


    @Override
    public void showLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed())
            compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }

    public void addDisposable(Disposable disposable) {
        if (disposable != null && compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }
    }

    public void removeDisposable(Disposable disposable) {
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
            if (compositeDisposable != null) {
                compositeDisposable.remove(disposable);
            }
        }
    }

    public void removeAllDisposables() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public boolean hasDisposables() {
        if (compositeDisposable != null) {
            return compositeDisposable.size() > 0;
        } else {
            return false;
        }
    }

    private void showErrorDialog(String title, String message, final actionOnError action) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title);
        builder.setPositiveButton(R.string.accept, (dialog, which) -> {
            if (action == actionOnError.CLOSE){
                finish();
            }
            dialog.dismiss();
        });

        builder.create().show();
    }

    private void showMessageDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title);
        builder.setPositiveButton(R.string.accept, (dialog, which) -> {
            dialog.dismiss();
        });

        builder.create().show();
    }

    protected abstract int getLayoutId();
}
