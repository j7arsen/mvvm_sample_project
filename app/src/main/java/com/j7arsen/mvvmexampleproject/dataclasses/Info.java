package com.j7arsen.mvvmexampleproject.dataclasses;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by j7ars on 25.05.2017.
 */
@Parcel(Parcel.Serialization.BEAN)
public class Info {

    @SerializedName("seed")
    private String seed;
    @SerializedName("results")
    private Integer results;
    @SerializedName("page")
    private Integer page;
    @SerializedName("version")
    private String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
