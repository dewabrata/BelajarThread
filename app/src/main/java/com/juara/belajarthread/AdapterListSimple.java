package com.juara.belajarthread;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterListSimple extends RecyclerView.Adapter<AdapterListSimple.ViewHolder> {




    JSONArray data;
    Context context;


    public AdapterListSimple(Context context, JSONArray data){


        this.data = data;
        this.context = context;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kiri, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TextView txtNama = holder.txtNama;
        ImageView image = holder.image;


        try {
            txtNama.setText((String)((JSONObject)data.get(position)).get("title"));
            Picasso.get().load((String)((JSONObject)data.get(position)).get("urlToImage")).into(image);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return data.length();
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtNama;
        public ImageView image;
        public LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNama = (TextView) itemView.findViewById(R.id.txtName);
            image = (ImageView)itemView.findViewById(R.id.imageView);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.parentLayout);

        }

    }


}
