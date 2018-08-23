package com.jp.data.entities.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NamesEntity {

    @SerializedName("international")
    @Expose
    public String international;
    @SerializedName("japanese")
    @Expose
    public Object japanese;
    @SerializedName("twitch")
    @Expose
    public String twitch;

}
