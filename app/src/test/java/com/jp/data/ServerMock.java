package com.jp.data;

import com.jp.app.model.SampleView;
import com.jp.domain.model.SampleDomain;

import java.util.ArrayList;
import java.util.List;

public class  ServerMock {

    public static SampleView getSampleView () {
        SampleView sampleView = new SampleView();
        sampleView.setId("12345");
        sampleView.setTitle("Sample test");
        sampleView.setUrlLogo("http//logo");
        return sampleView;
    }

    public static List<SampleDomain> getListDomain () {
        SampleDomain g1 = new SampleDomain();
        g1.setId("1234");
        g1.setTitle("SampleDomain1");
        g1.setUrlLogo("http//logo1");

        SampleDomain g2 = new SampleDomain();
        g2.setId("1234");
        g2.setTitle("SampleDomain2");
        g2.setUrlLogo("http//logo2");

        SampleDomain g3 = new SampleDomain();
        g3.setId("1234");
        g3.setTitle("SampleDomain3");
        g3.setUrlLogo("http//logo3");

        List<SampleDomain> list = new ArrayList();
        list.add(g1);
        list.add(g2);
        list.add(g3);

        return list;
    }

}
