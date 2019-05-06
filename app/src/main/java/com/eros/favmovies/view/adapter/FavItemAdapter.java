package com.eros.favmovies.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.eros.favmovies.R;
import com.eros.favmovies.view.constants.AppConstants;
import com.eros.favmovies.view.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<Movie> movieDbList;

    public FavItemAdapter(Context context, List<Movie> movieDbList) {
        mContext = context;
        this.movieDbList = movieDbList;
    }


    @Override
    public int getCount() {
        return movieDbList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.layout_fav_item, null);
            TextView txt_title = gridViewAndroid.findViewById(R.id.txt_title);
            ImageView img_poster = gridViewAndroid.findViewById(R.id.img_movie);
            TextView txt_rating = gridViewAndroid.findViewById(R.id.txt_rating);
            final Movie result = movieDbList.get(i);
            Picasso.get()
                    .load(AppConstants.IMAGE_BASE_URL +result.getPoster_path())
                    .into(img_poster);

            txt_title.setText(result.getTitle());
            txt_rating.setText(result.getRating()+"");

        } else {
            gridViewAndroid = convertView;
        }

        return gridViewAndroid;
    }

}