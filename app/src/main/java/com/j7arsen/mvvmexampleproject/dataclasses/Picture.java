package com.j7arsen.mvvmexampleproject.dataclasses;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by j7ars on 25.05.2017.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Picture {

    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
