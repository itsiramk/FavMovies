package com.eros.favmovies.view.utils;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.eros.favmovies.view.constants.AppConstants;
import com.eros.favmovies.view.model.Movie;
import com.eros.favmovies.view.model.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDataSource extends PageKeyedDataSource<Integer, Movie> {

    public int FIRST_PAGE = 1;
    public  final static int PAGE_SIZE = 359;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {

        RetrofitClient.getInstance()
                .getApi().getMovieFavourites(AppConstants.API_KEY, AppConstants.LANGUAGE,FIRST_PAGE)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getMovies(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        RetrofitClient.getInstance()
                .getApi().getMovieFavourites(AppConstants.API_KEY, AppConstants.LANGUAGE, FIRST_PAGE)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getMovies(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {

        FIRST_PAGE=FIRST_PAGE+1;
        RetrofitClient.getInstance()
                .getApi()
                .getMovieFavourites(AppConstants.API_KEY, AppConstants.LANGUAGE, FIRST_PAGE)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.body() != null && true) {
                            callback.onResult(response.body().getMovies(), params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }
}
