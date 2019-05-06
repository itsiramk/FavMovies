package com.eros.favmovies.view.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eros.favmovies.R;
import com.eros.favmovies.view.activities.MovieDetailActivity;
import com.eros.favmovies.view.constants.AppConstants;
import com.eros.favmovies.view.db.DatabaseClient;
import com.eros.favmovies.view.model.Movie;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ItemAdapter extends PagedListAdapter<Movie, ItemAdapter.ItemViewHolder> {

    String title,posterpath,releasedate,overview,original_lang;
    float rating,popularity;
    int id;
    private static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.image_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        final Movie movie = getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, MovieDetailActivity.class);
                intent.putExtra(AppConstants.TITLE,movie.getTitle());
                intent.putExtra(AppConstants.RATING,movie.getRating());
                intent.putExtra(AppConstants.RELEASE_DATE,movie.getRelease_date());
                intent.putExtra(AppConstants.POSTER_PATH,movie.getPoster_path());
                intent.putExtra(AppConstants.OVERVIEW,movie.getOverview());
                mCtx.startActivity(intent);

            }
        });
        holder.txt_title.setText(movie.getTitle().toString());
        holder.txt_rel_date.setText(movie.getRelease_date().toString());
        holder.like.setText(movie.getRating()+"");
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=movie.getId();
                title=movie.getTitle();
                posterpath=movie.getPoster_path();
                releasedate=movie.getRelease_date();
                rating=movie.getRating();
                overview=movie.getOverview();
                SaveTask st = new SaveTask();
                st.execute();
            }
        });

        Picasso.get()
                .load(AppConstants.IMAGE_BASE_URL + movie.getPoster_path())
                .placeholder(R.drawable.shimmer_background)
                .into(holder.image);

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(1)
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        if (!movie.getPoster_path().isEmpty()) {
            Picasso.get()
                    .load(AppConstants.IMAGE_BASE_URL+movie.getPoster_path())
                    .fit()
                    .transform(transformation)
                    .into(holder.img_movie);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txt_title, like, txt_rel_date;
        ImageView image, img_movie;

        public ItemViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            img_movie = view.findViewById(R.id.img_movie);
            txt_title = view.findViewById(R.id.user);
            like = view.findViewById(R.id.txt_vote_avg);
            txt_rel_date = view.findViewById(R.id.txt_rel_date);
        }
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Movie movie = new Movie();
            movie.setId(id);
            movie.setTitle(title);
            movie.setPoster_path(posterpath);
            movie.setRelease_date(releasedate);
            movie.setRating(rating);
            movie.setOverview(overview);
            Log.d("Data>>>>",id+"/"+title+"/"+posterpath+"/"+releasedate);
            //adding to database
            DatabaseClient.getInstance(mCtx).getAppDatabase()
                    .movieDao()
                    .insert(movie);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mCtx, "Data Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
