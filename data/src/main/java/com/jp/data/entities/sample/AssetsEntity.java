package com.jp.data.entities.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsEntity {

    @SerializedName("logo")
    @Expose
    public LogoEntity logo;
    @SerializedName("cover-tiny")
    @Expose
    public CoverTinyEntity coverTiny;
    @SerializedName("cover-small")
    @Expose
    public CoverSmallEntity coverSmall;
    @SerializedName("cover-medium")
    @Expose
    public CoverMediumEntity coverMedium;
    @SerializedName("cover-large")
    @Expose
    public CoverLargeEntity coverLarge;
    @SerializedName("icon")
    @Expose
    public IconEntity icon;
    @SerializedName("background")
    @Expose
    public BackgroundEntity background;
    @SerializedName("foreground")
    @Expose
    public Object foreground;
}
