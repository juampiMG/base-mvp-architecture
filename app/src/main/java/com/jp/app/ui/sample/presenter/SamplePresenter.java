package com.jp.app.ui.sample.presenter;

import com.jp.app.common.BaseSingleObserver;
import com.jp.app.common.controller.BaseActivity;
import com.jp.app.common.presenter.BasePresenter;
import com.jp.app.model.SampleView;
import com.jp.app.model.mapper.SampleViewMapper;
import com.jp.app.ui.sample.adapter.SampleAdapter;
import com.jp.app.ui.sample.view.ISampleView;
import com.jp.domain.interactor.IGetSampleUseCase;
import com.jp.domain.model.SampleDomain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SamplePresenter extends BasePresenter<ISampleView> implements ISamplePresenter, SampleAdapter.SampleAdapterCallBack {

    @Inject
    IGetSampleUseCase mGetSampleUseCase;
    @Inject
    SampleViewMapper mSampleViewMapper;


    List<SampleView> mSampleView;
    List<SampleDomain> mSampleDomain;


    @Inject
    public SamplePresenter(ISampleView view) {
        super(view);
    }

    @Override
    public void onViewReady() {
        loadSample();
    }

    private void loadSample() {
        mView.showLoading();
        mGetSampleUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSingleObserver<List<SampleDomain>>(getContext()) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<SampleDomain> Sample) {
                        if (mView != null) mView.hideLoading();
                        if (Sample != null) {
                            mSampleDomain = Sample;
                            mSampleView = mSampleViewMapper.transform(Sample);
                            drawSampleTransformed(mSampleView);
                        }
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        if (mView != null) mView.hideLoading();
                        showError(title, description, BaseActivity.actionOnError.CLOSE);
                    }
                });
    }

    private void drawSampleTransformed(List<SampleView> list) {
        if (mView != null) {
            mView.drawSample(list);
        }
    }


    @Override
    public void sampleClicked(final int position) {
        loadSampleInfo(mSampleView.get(position));
    }

    private void loadSampleInfo(SampleView sample) {
        if (mView != null) {
            mView.loadSampleInfo(sample);
        }
    }
}
