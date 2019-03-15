package com.subratsss.volleyexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends
        RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    @NonNull

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(@NonNull Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {

        Movie movie = movieList.get(i);
        viewHolder.textTitle.setText(movie.title);
        viewHolder.textRating.setText(String.valueOf(movie.rating));
        viewHolder.textYear.setText(String.valueOf(movie.year));

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle,textRating,textYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.title);
            textRating = itemView.findViewById(R.id.rating);
            textYear = itemView.findViewById(R.id.year);
        }
    }
}
