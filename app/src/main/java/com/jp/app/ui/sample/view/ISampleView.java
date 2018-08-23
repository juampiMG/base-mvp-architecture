package com.jp.app.ui.sample.view;


import com.jp.app.common.view.IBaseView;
import com.jp.app.model.SampleView;

import java.util.List;

public interface ISampleView extends IBaseView {

    void drawSample(List<SampleView> list);

    void loadSampleInfo(SampleView sample);
}
