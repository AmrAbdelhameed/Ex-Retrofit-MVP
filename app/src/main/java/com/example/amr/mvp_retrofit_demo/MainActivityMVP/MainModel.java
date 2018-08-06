package com.example.amr.mvp_retrofit_demo.MainActivityMVP;

import android.support.annotation.NonNull;

import com.example.amr.mvp_retrofit_demo.Models.Movie;
import com.example.amr.mvp_retrofit_demo.Network.APIService;
import com.example.amr.mvp_retrofit_demo.Network.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainMVP.Model {

    private APIService mAPIService = ApiUtils.getAPIService();

    @Override
    public void getMoviesList(String theme, final OnFinishedListener onFinishedListener) {

        mAPIService.getMovies(theme).enqueue(new Callback<Movie>() {

            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {

                if (response.isSuccessful())
                    onFinishedListener.onFinishedSuccess(response.body().getResults());
                else {
                    onFinishedListener.onFinishedFailed(response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}
