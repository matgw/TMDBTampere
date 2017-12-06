package com.example.matgw.tmdbtampere;

import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;



public class FavorisViewHolder extends RecyclerView.ViewHolder {

    TextView fv_nom;
    Button fv_supp;
    NavigationView nv;

    public FavorisViewHolder(View itemViewUser) {
        super(itemViewUser);

        fv_nom = (TextView) itemView.findViewById(R.id.fv_nom);
        fv_supp = (Button) itemView.findViewById(R.id.fv_supp);
        nv = (NavigationView) itemView.findViewById(R.id.nav_view);
    }


}
