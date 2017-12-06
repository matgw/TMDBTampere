package com.example.matgw.tmdbtampere;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;



public class MovieViewHolder1 extends RecyclerView.ViewHolder {

    TextView tv_nom;
    TextView tv_description;
    ImageView imageView;

    public MovieViewHolder1(View itemViewUser) {
        super(itemViewUser);

        tv_nom = (TextView) itemView.findViewById(R.id.tv_nom);
        tv_description = (TextView) itemView.findViewById(R.id.tv_description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }


}
