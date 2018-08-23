package com.jp.data.entities.mapper;


import com.jp.data.entities.sample.SampleEntity;
import com.jp.domain.model.SampleDomain;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SampleEntityMapper extends BaseModelDataMapper<SampleEntity, SampleDomain> {

    @Inject
    public SampleEntityMapper() {}

    @Override
    public SampleDomain transform(SampleEntity source) {
        SampleDomain sampleDomain = new SampleDomain();
        try {
            sampleDomain.setId(source.id);
            if (source.assets.coverLarge != null) sampleDomain.setUrlLogo(source.assets.coverLarge.uri);
            if (source.names != null)sampleDomain.setTitle(source.names.international);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return sampleDomain;
    }

    @Override
    public SampleEntity inverseTransform(SampleDomain source) {
        SampleEntity sampleEntity = new SampleEntity();
        try {
        } catch (Exception e) {
            e.getStackTrace();
        }
        return sampleEntity;
    }
}
