package com.example.matgw.tmdbtampere.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie implements Parcelable {
    
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("vote_average")
    private String rating;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("name")
    private String name;

    private int typeSerieMovie;

    @SerializedName("id")
    private int id;


    public Movie() {
    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        id = in.readInt();
        description = in.readString();
        rating = in.readString();
        backdrop = in.readString();
        releaseDate = in.readString();
        name = in.readString();
        typeSerieMovie = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeSerieMovie() {
        return typeSerieMovie;
    }

    public void setTypeSerieMovie(int typeSerieMovie) {
        this.typeSerieMovie = typeSerieMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        if (poster != null) {
            return "http://image.tmdb.org/t/p/w500" + poster;
        }
        return null;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBackdrop() {
        if (backdrop != null) {
            return "http://image.tmdb.org/t/p/w500" + backdrop;
        }
        return null;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeInt(typeSerieMovie);
        parcel.writeString(description);
        parcel.writeString(rating);
        parcel.writeInt(id);
        parcel.writeString(backdrop);
        parcel.writeString(releaseDate);
        parcel.writeString(name);

    }

    public void convertNameToTitle()
    {
        title=name;
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }
}
