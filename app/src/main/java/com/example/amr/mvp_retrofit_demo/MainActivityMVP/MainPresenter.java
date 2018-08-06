package com.example.amr.mvp_retrofit_demo.MainActivityMVP;

import com.example.amr.mvp_retrofit_demo.Models.Movie;

import java.util.List;

public class MainPresenter implements MainMVP.Presenter, MainMVP.Model.OnFinishedListener {

    private MainMVP.View view;
    private MainMVP.Model model;

    MainPresenter(MainMVP.View view, MainMVP.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void requestDataFromAPI() {
        if (view != null) {
            view.showProgress();
            String TOP_RATED = "top_rated";
            model.getMoviesList(TOP_RATED, this);
        }
    }

    @Override
    public void onFinishedSuccess(List<Movie.ResultsBean> resultsBeans) {
        if (view != null) {
            view.setData(resultsBeans);
            view.hideProgress();
        }
    }

    @Override
    public void onFinishedFailed(String message) {
        if (view != null) {
            view.onResponseFailed(message);
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null) {
            view.onResponseFailure(t);
            view.hideProgress();
        }
    }
}
