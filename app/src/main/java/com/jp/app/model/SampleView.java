package com.jp.app.model;

import org.parceler.Parcel;

import lombok.Data;


@Parcel
@Data
public class SampleView {

    public SampleView() {}

    String id;
    String title;
    String urlLogo;

}
