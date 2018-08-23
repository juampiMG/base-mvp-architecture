package com.jp.domain.model;

import org.parceler.Parcel;

import lombok.Data;

@Parcel
@Data
public class SampleDomain {
    public SampleDomain(){}

    String id;
    String urlLogo;
    String title;
}
