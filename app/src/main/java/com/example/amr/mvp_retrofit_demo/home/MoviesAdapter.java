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

import com.example.amr.mvp_retrofit_demo.R;
import com.example.amr.mvp_retrofit_demo.models.Movie;
import com.example.amr.mvp_retrofit_demo.utils.AppManger;
import com.example.amr.mvp_retrofit_demo.utils.PicassoHelper;
import com.example.amr.mvp_retrofit_demo.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context context;
    private List<Movie.ResultsBean> resultsBeans;
    private RecyclerItemClickListener recyclerItemClickListener;

    MoviesAdapter(Context context, RecyclerItemClickListener recyclerItemClickListener) {
        this.context = context;
        this.resultsBeans = new ArrayList<>();
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public void setResultsBeans(List<Movie.ResultsBean> resultsBeans) {
        this.resultsBeans = resultsBeans;
        notifyDataSetChanged();
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
        holder.bind(resultsBean);
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView movie_image;
        private TextView movie_title;

        MyViewHolder(View view) {
            super(view);
            movie_image = view.findViewById(R.id.movie_image);
            movie_title = view.findViewById(R.id.movie_title);
        }

        void bind(final Movie.ResultsBean resultsBean) {
            movie_title.setText(resultsBean.getTitle());

            String imageURL = AppManger.imageBaseURL + resultsBean.getPoster_path();
            new PicassoHelper().loadImage(context, imageURL, movie_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerItemClickListener.onItemClick(resultsBean);
                }
            });
        }
    }
}