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

class MovieAdapter3 extends RecyclerView.Adapter<MovieViewHolder3> {


    private Context mContext;
    private List<Movie> m;
    private static final String PREFS = "PREFS";
    private static final String DESC = "DESC";
    private static final String RAT = "RAT";
    private static final String ID = "ID";

    public MovieAdapter3(Context context, List<Movie> movies) {
        mContext = context;
        m = movies;
    }

    @Override
    public MovieViewHolder3 onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_view3, viewGroup, false);



        return new MovieViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder3 holder, int position) {

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

        Picasso.with(mContext).load(movie.getPoster()).into(holder.imageView31);


    }


    @Override
    public int getItemCount() {
        return m.size();
    }
}