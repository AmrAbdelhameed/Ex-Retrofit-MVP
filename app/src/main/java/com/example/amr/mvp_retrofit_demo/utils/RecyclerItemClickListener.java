package com.example.amr.mvp_retrofit_demo.utils;

import com.example.amr.mvp_retrofit_demo.models.Movie;

public interface RecyclerItemClickListener {
    void onItemClick(Movie.ResultsBean resultsBean);
}