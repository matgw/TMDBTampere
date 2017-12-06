package com.example.matgw.tmdbtampere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailer implements Parcelable {


    @SerializedName("key")
    private String key;
    @SerializedName("site")
    private String site;

    @SerializedName("id")
    private String id;


    public Trailer() {
    }

    protected Trailer(Parcel in) {
        id = in.readString();
        key = in.readString();
        site = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(site);
        parcel.writeString(key);
        parcel.writeString(id);
    }

    public static class TrailerResult {
        private List<Trailer> results;

        public List<Trailer> getResults() {
            return results;
        }
    }
}
