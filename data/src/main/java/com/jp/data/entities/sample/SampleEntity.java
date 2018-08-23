package com.jp.data.entities.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SampleEntity {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("names")
    @Expose
    public NamesEntity names;
    @SerializedName("assets")
    @Expose
    public AssetsEntity assets;

}
