package com.example.matgw.tmdbtampere.services.impl;


import android.content.SharedPreferences;

import com.example.matgw.tmdbtampere.models.Movie;
import com.example.matgw.tmdbtampere.models.Trailer;
import com.example.matgw.tmdbtampere.services.ApiService;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.matgw.tmdbtampere.utils.Constants.API_KEY;
import static com.example.matgw.tmdbtampere.utils.Constants.BASE_URL;



public class ApiServiceImpl {

    int movID=0;

    public void setMovID (int id)
    {
        movID=id;
    }

    public static ApiService getMovieApiService() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return restAdapter.create(ApiService.class);
    }

    public void getPopularShows(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();

        //APPEL RETROFIT
        service.getPopularShows(API_KEY, language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films populaires");
            }
        });
    }


    public void getTopRatedShows(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getTopRatedShows(API_KEY, language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les meilleurs films");
            }
        });
    }


    public void getNowPlayingShows(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getNowPlayingShows(API_KEY, language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films du moment");
            }
        });
    }




    public void getPopularMovies(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();

        //APPEL RETROFIT
        service.getPopularMovies(API_KEY, language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films populaires");
            }
        });
    }

    public void getTopRatedMovies(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getTopRatedMovies(API_KEY, language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les meilleurs films");
            }
        });
    }


    public void getNowPlayingMovies(final CustomCallBack<Movie> customCallBack, String language) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();


        //APPEL RETROFIT
        service.getNowPlayingMovies(API_KEY, language).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films du moment");
            }
        });
    }


    public void getTrailers(final CustomCallBack<Trailer> customCallBack) {
        final List<Trailer> trailers = new ArrayList<>();
        ApiService service = getMovieApiService();

        //APPEL RETROFIT
        service.getTrailers(movID, API_KEY).enqueue(new Callback<Trailer.TrailerResult>() {
            @Override
            public void onResponse(Call<Trailer.TrailerResult> call, Response<Trailer.TrailerResult> response) {

                Trailer.TrailerResult trailResult = response.body();

                if (trailResult != null) {
                    for (Trailer trailer : trailResult.getResults()) {
                        if (trailer.getKey() != null && trailer.getSite() != null) {
                            trailers.add(trailer);
                        }
                    }
                }
                customCallBack.onSuccess(trailers);
            }

            @Override
            public void onFailure(Call<Trailer.TrailerResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les trailers");
            }
        });
    }

    public void getTrailersShows(final CustomCallBack<Trailer> customCallBack) {
        final List<Trailer> trailers = new ArrayList<>();
        ApiService service = getMovieApiService();

        //APPEL RETROFIT
        service.getTrailersShows(movID, API_KEY).enqueue(new Callback<Trailer.TrailerResult>() {
            @Override
            public void onResponse(Call<Trailer.TrailerResult> call, Response<Trailer.TrailerResult> response) {

                Trailer.TrailerResult trailResult = response.body();

                if (trailResult != null) {
                    for (Trailer trailer : trailResult.getResults()) {
                        if (trailer.getKey() != null && trailer.getSite() != null) {
                            trailers.add(trailer);
                        }
                    }
                }
                customCallBack.onSuccess(trailers);
            }

            @Override
            public void onFailure(Call<Trailer.TrailerResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les trailers");
            }
        });
    }


    public void getSearchMovies(final CustomCallBack<Movie> customCallBack, String language, String word) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();

        //APPEL RETROFIT
        service.getSearchMovies(API_KEY, language, word).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les films");
            }
        });
    }

    public void getSearchShows(final CustomCallBack<Movie> customCallBack, String language, String word) {
        final List<Movie> movies = new ArrayList<>();
        ApiService service = getMovieApiService();

        //APPEL RETROFIT
        service.getSearchShows(API_KEY, language, word).enqueue(new Callback<Movie.MovieResult>() {
            @Override
            public void onResponse(Call<Movie.MovieResult> call, Response<Movie.MovieResult> response) {

                Movie.MovieResult movieResult = response.body();

                if (movieResult != null) {
                    for (Movie movie : movieResult.getResults()) {
                        if (movie.getBackdrop() != null && movie.getPoster() != null) {
                            movies.add(movie);
                        }
                    }
                }
                customCallBack.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<Movie.MovieResult> call, Throwable t) {
                customCallBack.onError("Impossible de recupérer les séries");
            }
        });
    }

    public interface CustomCallBack<T> {
        void onSuccess(List<T> movies);

        void onError(String message);
    }
}
