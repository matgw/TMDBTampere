package com.example.matgw.tmdbtampere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.matgw.tmdbtampere.models.Trailer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;


public class YoutubeFragment extends Fragment {

    private static final String API_KEY = "AIzaSyAcL2hpNckKWy6FA2xf7gwb4LLT3Lt5aYo";

   // private static String VIDEO_ID = "EGy39OMyHzw";

    List<Trailer> trailersList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.youtube_api, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(API_KEY, new OnInitializedListener() {

            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored & !trailersList.isEmpty()) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    player.loadVideo(trailersList.get(0).getKey());
                    player.play();

                }
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {

                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage + " - Veuillez installer l'application Youtube", Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });


        return rootView;
    }



    public void addTrailers(List<Trailer> trail) {
        trailersList=trail;
    }
}