package com.jp.app.model.mapper;


import com.jp.data.entities.mapper.BaseModelDataMapper;
import com.jp.domain.model.SampleDomain;
import com.jp.app.model.SampleView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SampleViewMapper extends BaseModelDataMapper<SampleDomain, SampleView> {

    @Inject
    public SampleViewMapper() {}

    @Override
    public SampleView transform(SampleDomain source) {
        SampleView sampleView = new SampleView();
        try {
            sampleView.setId(source.getId());
            sampleView.setTitle(source.getTitle());
            sampleView.setUrlLogo(source.getUrlLogo());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return sampleView;
    }

    @Override
    public SampleDomain inverseTransform(SampleView source) {
        SampleDomain sampleDomain = new SampleDomain();
        try {
        } catch (Exception e) {
            e.getStackTrace();
        }
        return sampleDomain;
    }
}
