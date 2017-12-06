package com.example.matgw.tmdbtampere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.matgw.tmdbtampere.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


class FavorisAdapter extends RecyclerView.Adapter<FavorisViewHolder> {

    private Context mContext;
    private ArrayList<String> favoris;
    private int positionFavoris;
    private TinyDB tinydb;

    public FavorisAdapter(Context context) {
        mContext = context;
        tinydb = new TinyDB(context);
        favoris = tinydb.getListString("fav");

    }

    @Override
    public FavorisViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favoris_view, viewGroup, false);

        final FavorisViewHolder u1 = new FavorisViewHolder(view);

        return u1;
    }

    @Override
    public void onBindViewHolder(FavorisViewHolder holder, final int position) {

        final String titre = favoris.get(position);

        holder.fv_nom.setText(titre);

        holder.fv_supp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                favoris.remove(position);
                tinydb.putListString("fav", favoris);
                Toast.makeText(mContext, titre+" a été supprimé de vos favoris !", Toast.LENGTH_SHORT).show();
                notifyItemRemoved(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return favoris.size();
    }
}
