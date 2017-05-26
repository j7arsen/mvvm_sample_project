package com.j7arsen.mvvmexampleproject.dataclasses;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by j7ars on 25.05.2017.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Name {

    @SerializedName("title")
    private String title;
    @SerializedName("first")
    private String first;
    @SerializedName("last")
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

}
