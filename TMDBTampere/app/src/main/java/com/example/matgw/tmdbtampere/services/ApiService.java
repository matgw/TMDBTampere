package com.example.matgw.tmdbtampere.services;



import com.example.matgw.tmdbtampere.models.Movie;
import com.example.matgw.tmdbtampere.models.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("tv/popular")
    Call<Movie.MovieResult> getPopularShows(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/top_rated")
    Call<Movie.MovieResult> getTopRatedShows(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/airing_today")
    Call<Movie.MovieResult> getNowPlayingShows(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/popular")
    Call<Movie.MovieResult> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/top_rated")
    Call<Movie.MovieResult> getTopRatedMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/now_playing")
    Call<Movie.MovieResult> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{id}/videos")
    Call<Trailer.TrailerResult> getTrailers(@Path("id") int movieID, @Query("api_key") String apiKey);

    @GET("tv/{id}/videos")
    Call<Trailer.TrailerResult> getTrailersShows(@Path("id") int movieID, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<Movie.MovieResult> getSearchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String word);

    @GET("search/tv")
    Call<Movie.MovieResult> getSearchShows(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String word);
}
