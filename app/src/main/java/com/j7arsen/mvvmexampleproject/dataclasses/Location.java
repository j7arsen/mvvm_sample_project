package com.j7arsen.mvvmexampleproject.dataclasses;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by j7ars on 25.05.2017.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Location {

    @SerializedName("street")
    private String street;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("postcode")
    private Integer postcode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

}
