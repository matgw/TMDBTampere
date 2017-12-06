package com.example.matgw.tmdbtampere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.matgw.tmdbtampere.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.matgw.tmdbtampere.R.id.imageView;


class MovieAdapter1 extends RecyclerView.Adapter<MovieViewHolder1> {

    private Context mContext;
    private List<Movie> movies;
    private int positionMovie;
    private static final String PREFS = "PREFS";
    private static final String DESC = "DESC";
    private static final String RAT = "RAT";
    private static final String ID = "ID";


    public MovieAdapter1(Context context, List<Movie> mov) {
        mContext = context;
        movies = mov;

    }

    @Override
    public MovieViewHolder1 onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_view1, viewGroup, false);

        final MovieViewHolder1 u1 = new MovieViewHolder1(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                positionMovie=u1.getLayoutPosition();
                final Intent intent;

                intent =  new Intent(view.getContext(), MovieSingleActivity.class);

                intent.putExtra("FILM", movies.get(positionMovie));

                view.getContext().startActivity(intent);

                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                sharedPreferences
                        .edit()
                        .putString(DESC, movies.get(positionMovie).getDescription())
                        .putString(RAT, movies.get(positionMovie).getRating())
                        .putInt(ID, movies.get(positionMovie).getId())
                        .apply();

            }
        });

        return u1;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder1 holder, int position) {

        final Movie movie = movies.get(position);

      //  View view = holder.itemView;


        holder.tv_nom.setText(movie.getTitle());
        holder.tv_description.setText(movie.getDescription());

        Picasso.with(mContext).load(movie.getBackdrop()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
