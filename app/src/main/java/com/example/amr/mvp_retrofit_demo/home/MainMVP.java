package com.example.amr.mvp_retrofit_demo.home;

import com.example.amr.mvp_retrofit_demo.models.Movie;

import java.util.List;

public interface MainMVP {

    interface Presenter {
        void onDestroy();

        void requestDataFromAPI(String topRated);
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setData(List<Movie.ResultsBean> resultsBeans);

        void onResponseFailed(String message);

        void onResponseFailure(Throwable throwable);
    }

    interface Model {
        void getMoviesList(String theme, OnFinishedListener onFinishedListener);

        interface OnFinishedListener {
            void onFinishedSuccess(List<Movie.ResultsBean> resultsBeans);

            void onFinishedFailed(String message);

            void onFailure(Throwable throwable);
        }
    }
}
