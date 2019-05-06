package com.eros.favmovies.view.interfaces;

import com.eros.favmovies.view.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices{

   @GET("top_rated")
   Call<MovieResponse> getMovieFavourites(@Query("api_key") String apikey, @Query("language") String lang, @Query("page") int page);

}
