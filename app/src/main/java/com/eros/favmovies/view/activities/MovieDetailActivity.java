package com.eros.favmovies.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.eros.favmovies.R;
import com.eros.favmovies.view.constants.AppConstants;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Context context;
    String title,releasedate,posterpath,overview;
    float rating;
    ImageView img_movie;
    TextView txt_title,txt_rating,txt_releasedate,txt_overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_detail);
        img_movie = findViewById(R.id.img_movie);
        txt_rating = findViewById(R.id.txt_rating);
        txt_title = findViewById(R.id.txt_title);
        txt_releasedate = findViewById(R.id.txt_releasedate);
        txt_overview = findViewById(R.id.txt_overview);
        title = getIntent().getStringExtra(AppConstants.TITLE);
        rating = getIntent().getFloatExtra(AppConstants.RATING,0);
        releasedate = getIntent().getStringExtra(AppConstants.RELEASE_DATE);
        posterpath = getIntent().getStringExtra(AppConstants.POSTER_PATH);
        overview = getIntent().getStringExtra(AppConstants.OVERVIEW);
        Picasso.get()
                .load(AppConstants.IMAGE_BASE_URL + posterpath)
                .into(img_movie);
        txt_title.setText(title);
        txt_rating.setText(rating+"");
        txt_releasedate.setText(releasedate);
        txt_overview.setText(overview);

    }
}
