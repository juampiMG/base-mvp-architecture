package com.jp.app.ui.sample.presenter;

import com.jp.app.model.SampleView;
import com.jp.app.ui.BaseTest;
import com.jp.app.ui.sample.SampleActivity;
import com.jp.app.ui.sample.view.SampleFragment;
import com.jp.app.ui.sample.view.ISampleView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class SamplePresenterTest extends BaseTest {


    SamplePresenter mPresenter;

    @Mock
    ISampleView mView;

    ActivityController<SampleActivity> mActivityController;

    SampleActivity mActivity;

    SampleFragment mFragment;

    @Before
    public void setup() {
        mActivityController = Robolectric.buildActivity(SampleActivity.class);

        mActivityController.create().start().resume();

        mActivity = mActivityController.get();

        mFragment = (SampleFragment) mActivity.getCurrentFragment();

        mPresenter = (SamplePresenter) mFragment.getPresenter();

        mPresenter.setView(mView);

    }


    @Override
    public void controlViews() {
        assertNotNull(mActivity);
        assertNotNull(mFragment);
        assertNotNull (mPresenter);
        assertNotNull (mView);
    }

    @Test
    public void checkLoadSample () {
        mPresenter.onViewReady();

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mView).drawSample(argumentCaptor.capture());
        List capturedArgument = argumentCaptor.<List<SampleView>>getValue();

        assertEquals(3, capturedArgument.size());
    }
}
