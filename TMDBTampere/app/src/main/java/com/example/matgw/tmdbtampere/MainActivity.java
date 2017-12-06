package com.example.matgw.tmdbtampere;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.matgw.tmdbtampere.models.Movie;
import com.example.matgw.tmdbtampere.services.impl.ApiServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String EXTRA_MESSAGE = "";
    ApiServiceImpl apiService1 = new ApiServiceImpl();
    ApiServiceImpl apiService2 = new ApiServiceImpl();
    ApiServiceImpl apiService3 = new ApiServiceImpl();
    ApiServiceImpl apiService4 = new ApiServiceImpl();
    ApiServiceImpl apiService5 = new ApiServiceImpl();
    ApiServiceImpl apiService6 = new ApiServiceImpl();
    ApiServiceImpl apiService7 = new ApiServiceImpl();

    private int choixVue =  1;
    private int choixCategorie = 3;
    private int vueMovie = 1;


    private List<Movie> popularMovies = new ArrayList<Movie>();
    private List<Movie> topRatedMovies = new ArrayList<Movie>();
    private List<Movie> nowPlayingMovies = new ArrayList<Movie>();

    private ImageButton trm;
    private ImageButton pm;
    private ImageButton npm;

    private List<Movie> popularShows = new ArrayList<Movie>();
    private List<Movie> topRatedShows = new ArrayList<Movie>();
    private List<Movie> nowPlayingShows = new ArrayList<Movie>();

    private List<Movie> moviesSearched = new ArrayList<Movie>();
    private List<Movie> showsSearched = new ArrayList<Movie>();

    private int ite=0;

    private static final String SETTINGS = "SETTINGS";
    private static final String LANG = "LANG";
    String language="fr-FR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //définition des boutons du bas
        trm=(ImageButton) findViewById(R.id.topRatedButton);
        pm=(ImageButton) findViewById(R.id.popularButton);
        npm=(ImageButton) findViewById(R.id.nowPlayButton);


        trm.setOnClickListener(trCL);
        pm.setOnClickListener(pmCL);
        npm.setOnClickListener(npmCL);

        vueMovie=1;

        getNowPlayingMovies();
        getTopRatedMovies();
        getPopularMovies();

        getNowPlayingShows();
        getTopRatedShows();
        getPopularShows();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                if(vueMovie==1)
                {
                    getSearchMovies(query);
                }
                else
                {
                    getSearchShows(query);
                }


                return true;
            }
        });


        return true;
    }

    private void getSearchMovies(String query) {
        apiService7.getSearchMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {

                moviesSearched=movies;
                choixVue=1;
                setView1(moviesSearched);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("recherche", "error");
            }
        }, language, query);
    }

    private void getSearchShows(String query) {
        apiService7.getSearchShows(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {

                for (Movie temp : movies) {
                    temp.convertNameToTitle();
                    temp.setTypeSerieMovie(1);
                }

                showsSearched=movies;
                choixVue=1;
                setView1(showsSearched);

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("recherche", "error");
            }
        }, language, query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        List<Movie> tempMovies = new ArrayList<Movie>();

        if(vueMovie==1)
        {
            if (choixCategorie == 1) {
                tempMovies = topRatedMovies;
            }
            if (choixCategorie == 2) {
                tempMovies = popularMovies;
            }
            if (choixCategorie == 3) {
                tempMovies = nowPlayingMovies;
            }
        }
        else
        {
            if (choixCategorie == 1) {
                tempMovies = topRatedShows;
            }
            if (choixCategorie == 2) {
                tempMovies = popularShows;
            }
            if (choixCategorie == 3) {
                tempMovies = nowPlayingShows;
            }
        }

        int id = item.getItemId();
        if(!popularMovies.isEmpty()) {

            if (id == R.id.vue1) {
                setView1(tempMovies);
                choixVue=1;
                return true;
            }
            if (id == R.id.vue2) {
                setView2(tempMovies);
                choixVue=2;
                return true;
            }
            if (id == R.id.vue3) {
                setView3(tempMovies);
                choixVue=3;
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_movies)
        {
            vueMovie=1;
            choixVue=1;
            choixCategorie=3;
            setView1(nowPlayingMovies);
            Toast.makeText(this, "films", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_tvshows)
        {
            vueMovie=0;
            choixVue=1;
            choixCategorie=3;
            setView1(nowPlayingShows);
            Toast.makeText(this, "séries", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_favorites)
        {
            Toast.makeText(this, "favoris", Toast.LENGTH_SHORT).show();

            RecyclerView mRvUser = (RecyclerView) findViewById(R.id.rv_numbers);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRvUser.setLayoutManager(layoutManager);

            FavorisAdapter userAdapter = new FavorisAdapter(getApplicationContext());

            mRvUser.setAdapter(userAdapter);
            userAdapter.notifyDataSetChanged();

        }
        else if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);

            intent.putExtra(EXTRA_MESSAGE, "test");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getPopularShows() {
        apiService4.getPopularShows(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                for (Movie temp : movies) {
                    temp.convertNameToTitle();
                    temp.setTypeSerieMovie(1);
                }
                popularShows=movies;

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("film", "error");
            }
        }, language);
    }

    private void getTopRatedShows() {
        apiService5.getTopRatedShows(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                for (Movie temp : movies) {
                    temp.convertNameToTitle();
                    temp.setTypeSerieMovie(1);
                }
                topRatedShows=movies;

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("film", "error");
            }
        }, language);
    }

    private void getNowPlayingShows() {
        apiService6.getNowPlayingShows(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {
                for (Movie temp : movies) {
                    temp.convertNameToTitle();
                    temp.setTypeSerieMovie(1);
                }
                nowPlayingShows=movies;

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("film", "error");
            }
        }, language);
    }


    private void getPopularMovies() {
        apiService1.getPopularMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
            @Override
            public void onSuccess(List<Movie> movies) {

                popularMovies=movies;

            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("film", "error");
            }
        }, language);
    }

    private void getTopRatedMovies() {
                apiService2.getTopRatedMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
                    @Override
                    public void onSuccess(List<Movie> movies) {

                        topRatedMovies=movies;

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        Log.d("film", "error");
                    }
                }, language);
            }

            private void getNowPlayingMovies() {
                apiService3.getNowPlayingMovies(new ApiServiceImpl.CustomCallBack<Movie>() {
                    @Override
                    public void onSuccess(List<Movie> movies) {

                        nowPlayingMovies=movies;

                        //vue par défaut
                        if (ite==0)
                        {
                            choixVue=1;
                            setView1(nowPlayingMovies);
                            ite=1;
                        }
                    }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Log.d("film", "error");
            }
        }, language);
    }

    private void setView1(List<Movie> movies) {

        RecyclerView mRvUser = (RecyclerView) findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvUser.setLayoutManager(layoutManager);

        MovieAdapter1 userAdapter = new MovieAdapter1(getApplicationContext(), movies);

        mRvUser.setAdapter(userAdapter);

    }

    private void setView2(List<Movie> movies) {

        RecyclerView mRvUser = (RecyclerView) findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvUser.setLayoutManager(layoutManager);

        MovieAdapter2 userAdapter = new MovieAdapter2(getApplicationContext(), movies);

        mRvUser.setAdapter(userAdapter);

    }
    private void setView3(List<Movie> movies) {

        RecyclerView mRvUser = (RecyclerView) findViewById(R.id.rv_numbers);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRvUser.setLayoutManager(layoutManager);

        MovieAdapter3 userAdapter = new MovieAdapter3(getApplicationContext(), movies);

        mRvUser.setAdapter(userAdapter);

    }




    View.OnClickListener trCL = new View.OnClickListener()
    {
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Top Rated", Toast.LENGTH_SHORT).show();

            if(vueMovie==1)
            {
                if (choixVue == 1) {
                    setView1(topRatedMovies);
                }
                if (choixVue == 2) {
                    setView2(topRatedMovies);
                }
                if (choixVue == 3) {
                    setView3(topRatedMovies);
                }
            }
            else
            {
                if (choixVue == 1) {
                    setView1(topRatedShows);
                }
                if (choixVue == 2) {
                    setView2(topRatedShows);
                }
                if (choixVue == 3) {
                    setView3(topRatedShows);
                }
            }

            choixCategorie=1;
        }
    };

    View.OnClickListener pmCL = new View.OnClickListener()
    {
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Popular", Toast.LENGTH_SHORT).show();
            if(vueMovie==1)
            {
                if (choixVue == 1) {
                    setView1(popularMovies);
                }
                if (choixVue == 2) {
                    setView2(popularMovies);
                }
                if (choixVue == 3) {
                    setView3(popularMovies);
                }
            }
            else
            {
                if (choixVue == 1) {
                    setView1(popularShows);
                }
                if (choixVue == 2) {
                    setView2(popularShows);
                }
                if (choixVue == 3) {
                    setView3(popularShows);
                }
            }

            choixCategorie=2;
        }
    };

    View.OnClickListener npmCL = new View.OnClickListener()
    {
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Now Playing", Toast.LENGTH_SHORT).show();
            if(vueMovie==1)
            {
                if (choixVue == 1) {
                    setView1(nowPlayingMovies);
                }
                if (choixVue == 2) {
                    setView2(nowPlayingMovies);
                }
                if (choixVue == 3) {
                    setView3(nowPlayingMovies);
                }
            }
            else
            {
                if (choixVue == 1) {
                    setView1(nowPlayingShows);
                }
                if (choixVue == 2) {
                    setView2(nowPlayingShows);
                }
                if (choixVue == 3) {
                    setView3(nowPlayingShows);
                }
            }


            choixCategorie=3;
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences(SETTINGS, MODE_PRIVATE);
        String newLanguage = sharedPreferences.getString("LANG", "test");
        String languageTemp = language;
        if(newLanguage.equals(languageTemp))
        {
            int i=0;
        }
        else
        {
            language=newLanguage;
            nowPlayingMovies.clear();
            popularMovies.clear();
            topRatedMovies.clear();
            vueMovie=1;
            ite=0;
            choixCategorie=3;
            getNowPlayingMovies();
            getTopRatedMovies();
            getPopularMovies();

            getNowPlayingShows();
            getTopRatedShows();
            getPopularShows();


          //  setContentView(R.layout.activity_main);
        }

    }


}
