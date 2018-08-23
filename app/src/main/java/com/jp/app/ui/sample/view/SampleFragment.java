package com.jp.app.ui.sample.view;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jp.app.R;
import com.jp.app.common.view.BaseFragment;
import com.jp.app.common.view.IBaseFragmentCallback;
import com.jp.app.model.SampleView;
import com.jp.app.ui.sample.adapter.SampleAdapter;
import com.jp.app.ui.sample.presenter.SamplePresenter;
import com.jp.app.ui.sample.presenter.ISamplePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SampleFragment extends BaseFragment<ISamplePresenter, SampleFragment.FragmentCallback> implements ISampleView {

    public static final int LAYOUT_ID = R.layout.sample_fragment;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mGridLayoutManager;
    private SampleAdapter mAdapter;

    public static SampleFragment newInstance(Bundle bundle) {
        SampleFragment fragment = new SampleFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface FragmentCallback extends IBaseFragmentCallback {
        void loadSampleInfo(SampleView Sample);
    }


    @Override
    public int getLayoutId() {
        return LAYOUT_ID;
    }


    @Override
    public void onViewLoaded(Bundle savedInstanceState, View view) {
        super.onViewLoaded(savedInstanceState, view);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new SampleAdapter(new ArrayList<>(), (SamplePresenter) getPresenter());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void drawSample(List<SampleView> Sample) {
        mAdapter.addSamples(Sample);
    }

    @Override
    public void loadSampleInfo(SampleView Sample) {
        if (mCallback != null) {
            mCallback.loadSampleInfo(Sample);
        }
    }

    public SampleAdapter getAdapter() {
        return mAdapter;
    }
}

