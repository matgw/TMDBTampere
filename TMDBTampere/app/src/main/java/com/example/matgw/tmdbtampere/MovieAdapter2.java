package com.example.matgw.tmdbtampere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matgw.tmdbtampere.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

class MovieAdapter2 extends RecyclerView.Adapter<MovieViewHolder2> {


    private Context mContext;
    private List<Movie> m;
    private static final String PREFS = "PREFS";
    private static final String DESC = "DESC";
    private static final String RAT = "RAT";
    private static final String ID = "ID";

    public MovieAdapter2(Context context, List<Movie> movies) {
        mContext = context;
        m = movies;
    }

    @Override
    public MovieViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_view2, viewGroup, false);



        return new MovieViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder2 holder, int position) {

        final Movie movie = m.get(position);

        View view = holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieSingleActivity.class);
                intent.putExtra("FILM", movie);
                view.getContext().startActivity(intent);

                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                sharedPreferences
                        .edit()
                        .putString(DESC, movie.getDescription())
                        .putString(RAT, movie.getRating())
                        .putInt(ID, movie.getId())
                        .apply();


            }
        });
        holder.tv_nom2.setText(String.valueOf(movie.getTitle()));
        holder.tv_description2.setText(String.valueOf(movie.getDescription()));
        Picasso.with(mContext).load(movie.getPoster()).into(holder.movieImage);


    }

    @Override
    public int getItemCount() {
        return m.size();
    }
}