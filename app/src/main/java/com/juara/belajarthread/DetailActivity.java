package com.juara.belajarthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    ImageView gambar;
    TextView txtTitle, txtAuthor, txtDetail;
    JSONObject mJsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        gambar = findViewById(R.id.gambar);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtTitle = findViewById(R.id.txtTitle);
        txtDetail = findViewById(R.id.txtDetail);
        if(getIntent().hasExtra("json")) {
            try {
                mJsonObject = new JSONObject(getIntent().getStringExtra("json"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            txtDetail.setText((String)mJsonObject.get("description"));
            txtTitle.setText((String)mJsonObject.get("title"));
            txtAuthor.setText((String)mJsonObject.get("author"));
            Picasso.get().load((String)mJsonObject.get("urlToImage")).into(gambar);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
