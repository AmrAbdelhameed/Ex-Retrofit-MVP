package com.example.amr.mvp_retrofit_demo.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amr.mvp_retrofit_demo.utils.AppManger;
import com.example.amr.mvp_retrofit_demo.utils.PicassoHelper;
import com.example.amr.mvp_retrofit_demo.models.Movie;
import com.example.amr.mvp_retrofit_demo.R;
import com.example.amr.mvp_retrofit_demo.utils.RecyclerItemClickListener;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context context;
    private List<Movie.ResultsBean> resultsBeans;
    private RecyclerItemClickListener recyclerItemClickListener;

    public MoviesAdapter(Context context, List<Movie.ResultsBean> resultsBeans, RecyclerItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.resultsBeans = resultsBeans;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_in_movies, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Movie.ResultsBean resultsBean = resultsBeans.get(position);

        holder.movie_title.setText(resultsBean.getTitle());

        String imageURL = AppManger.imageBaseURL + resultsBean.getPoster_path();
        new PicassoHelper().loadImage(context, imageURL, holder.movie_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(resultsBeans.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView movie_image;
        public TextView movie_title;

        MyViewHolder(View view) {
            super(view);
            movie_image = view.findViewById(R.id.movie_image);
            movie_title = view.findViewById(R.id.movie_title);
        }
    }
}