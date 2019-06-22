package com.example.amr.mvp_retrofit_demo.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.amr.mvp_retrofit_demo.R;
import com.example.amr.mvp_retrofit_demo.models.Movie;
import com.example.amr.mvp_retrofit_demo.utils.RecyclerItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMVP.View, RecyclerItemClickListener {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private MainMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        presenter = new MainPresenter(MainActivity.this, new MainModel());
        String topRated = "top_rated";
        presenter.requestDataFromAPI(topRated);
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_view);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        moviesAdapter = new MoviesAdapter(this, this);
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setData(List<Movie.ResultsBean> resultsBeans) {
        moviesAdapter.setResultsBeans(resultsBeans);
    }

    @Override
    public void onResponseFailed(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onItemClick(Movie.ResultsBean resultsBean) {
        Toast.makeText(MainActivity.this, resultsBean.getTitle(), Toast.LENGTH_LONG).show();
    }
}
