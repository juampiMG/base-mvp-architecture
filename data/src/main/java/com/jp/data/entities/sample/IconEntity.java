package com.jp.data.entities.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IconEntity {

    @SerializedName("uri")
    @Expose
    public String uri;
    @SerializedName("width")
    @Expose
    public int width;
    @SerializedName("height")
    @Expose
    public int height;

}
