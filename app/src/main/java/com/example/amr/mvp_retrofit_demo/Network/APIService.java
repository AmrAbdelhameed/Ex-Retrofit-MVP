package com.example.amr.mvp_retrofit_demo.Network;

import com.example.amr.mvp_retrofit_demo.Models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("movie/{theme}?api_key=0378f9d1be05009430ec0b03b4f3b3e8")
    Call<Movie> getMovies(@Path("theme") String theme);
}