package com.example.amr.mvp_retrofit_demo.MainActivityMVP;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.amr.mvp_retrofit_demo.Adapters.MoviesAdapter;
import com.example.amr.mvp_retrofit_demo.Models.Movie;
import com.example.amr.mvp_retrofit_demo.R;
import com.example.amr.mvp_retrofit_demo.Utils.RecyclerItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MainMVP.Presenter Presenter;

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Movie.ResultsBean resultsBean) {

            Toast.makeText(MainActivity.this,
                    resultsBean.getTitle(),
                    Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Presenter = new MainPresenter(MainActivity.this, new MainModel());
        Presenter.requestDataFromAPI();
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_view);
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

        MoviesAdapter adapter = new MoviesAdapter(MainActivity.this, resultsBeans, recyclerItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResponseFailed(String message) {
        Toast.makeText(MainActivity.this,
                message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.onDestroy();
    }
}
