package com.example.matgw.tmdbtampere;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MovieViewHolder2 extends RecyclerView.ViewHolder {
    TextView tv_nom2;
    TextView tv_description2;
    ImageView movieImage;


    public MovieViewHolder2(View itemViewMovie) {
        super(itemViewMovie);
        tv_nom2 =  itemView.findViewById(R.id.tv_nom2);
        tv_description2 = itemView.findViewById(R.id.tv_description2);
        movieImage = itemView.findViewById(R.id.movie_image);

    }

}

