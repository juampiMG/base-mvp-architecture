package com.jp.app.ui.sample;

import android.os.Bundle;

import com.jp.app.R;
import com.jp.app.common.controller.BaseActivity;
import com.jp.app.model.SampleView;
import com.jp.app.ui.sample.view.SampleFragment;
import com.jp.app.utils.NavigationUtils;

public class SampleActivity extends BaseActivity implements SampleFragment.FragmentCallback {

    public static final int LAYOUT_ID = R.layout.sample_activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            currentFragment = SampleFragment.newInstance(new Bundle());
            NavigationUtils.navigateToFragment(this, currentFragment, R.id.content, false);
        } else {
            currentFragment = getFragmentManager().findFragmentById(R.id.content);
        }
    }

    @Override
    protected int getLayoutId() {
        return LAYOUT_ID;
    }

    @Override
    public void loadSampleInfo(SampleView sample) {
        NavigationUtils.navigationToSampleInfoActivity(this, sample);
        showMessage(getString(R.string.information), String.format(getString(R.string.message_on_click), sample.getTitle()));
    }
}
