package com.example.matgw.tmdbtampere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.matgw.tmdbtampere.models.Movie;
import com.example.matgw.tmdbtampere.models.Trailer;
import com.example.matgw.tmdbtampere.services.impl.ApiServiceImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieSingleActivity extends AppCompatActivity {

    public static String EXTRA_MOVIE = "EXTRA_MOVIE";
    private static final String PREFS = "PREFS";
    private static final String IDMOVIE = "IDMOVIE";

    private static final String FAV = "FAV";
    private static final String FAVTITLE = "FAVTITLE";

    private Movie mov1;
    private List<Trailer> trailers = new ArrayList<Trailer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        mov1 = (Movie) intent.getParcelableExtra("FILM");


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        setContentView(R.layout.activity_movie_single);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv_nom = (TextView) findViewById(R.id.tv_nomMS);
        TextView tv_description = (TextView) findViewById(R.id.tv_descriptionMS);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewMS);
        RatingBar rb = (RatingBar ) findViewById(R.id.ratingBar2);

        setTitle("Fiche de "+mov1.getTitle());
        tv_nom.setText(mov1.getTitle());
        Picasso.with(getApplicationContext()).load(mov1.getPoster()).into(imageView);

        String description = new String();
        float rating=0;

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        description = sharedPreferences.getString("DESC", "test");
        rating = Float.parseFloat(sharedPreferences.getString("RAT", "ok"));

        tv_description.setText(description);
        rb.setNumStars(10);
        rb.setRating(rating);

        //l'intent passe que la moitié de l'ID en paramètre/pas la description/pas le rating...
        mov1.setId(sharedPreferences.getInt("ID", 1));

        Button btn_share=(Button)findViewById(R.id.shareit);
        btn_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareIt();
            }
        });

        Button btn_trailer=(Button)findViewById(R.id.trailers);
        btn_trailer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getTrailers();
            }
        });

        FloatingActionButton fav = (FloatingActionButton) findViewById(R.id.favourite);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeLeFilm();
            }
        });


    }

    private void likeLeFilm()
    {
        TinyDB tinydb = new TinyDB(getApplicationContext());
        ArrayList<String> listeFavoris = new ArrayList<String>();
        listeFavoris = tinydb.getListString("fav");

        int dejaPresent=0;
        //On vérifie si le film est pas déjà présent
        for (String temp : listeFavoris) {
            if(temp.equals(mov1.getTitle()))
            {
                dejaPresent=1;
            }
        }
        if(dejaPresent==0)
        {
            listeFavoris.add(mov1.getTitle());
            tinydb.putListString("fav", listeFavoris);
            Toast.makeText(this, mov1.getTitle()+" a bien été ajouté aux favoris !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, mov1.getTitle()+" est déjà présent dans vos favoris.", Toast.LENGTH_SHORT).show();
        }

    }


    private void shareIt() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "A regarder");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mov1.getTitle()+", c'est vraiment trop bien !");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void getTrailers()
    {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.setMovID(mov1.getId());
        if(mov1.getTypeSerieMovie()>=0)
        {
            apiService.getTrailersShows(new ApiServiceImpl.CustomCallBack<Trailer>() {
                @Override
                public void onSuccess(List<Trailer> trail) {
                    afficheTrailers(trail);
                }

                @Override
                public void onError(String message) {
                    Log.d("film", "error");
                }
            });
        }
        else
        {
            apiService.getTrailers(new ApiServiceImpl.CustomCallBack<Trailer>() {
                @Override
                public void onSuccess(List<Trailer> trail) {
                    afficheTrailers(trail);
                }

                @Override
                public void onError(String message) {
                    Log.d("film", "error");
                }
            });
        }
    }



    private void afficheTrailers(List<Trailer> trail)
    {
        if(!trail.isEmpty())
        {
            YoutubeFragment fragment = new YoutubeFragment();
            fragment.addTrailers(trail);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.CLMovieSingle, fragment)
                    .addToBackStack(null)
                    .commit();
        }

    }


}
