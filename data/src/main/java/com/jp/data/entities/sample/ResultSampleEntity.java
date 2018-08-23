package com.jp.data.entities.sample;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultSampleEntity {

    @SerializedName("data")
    @Expose
    public List<SampleEntity> data = null;

}
