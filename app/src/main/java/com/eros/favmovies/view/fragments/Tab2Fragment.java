package com.eros.favmovies.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.eros.favmovies.R;
import com.eros.favmovies.view.activities.MainActivity;
import com.eros.favmovies.view.adapter.FavItemAdapter;
import com.eros.favmovies.view.db.DatabaseClient;
import com.eros.favmovies.view.model.Movie;

import java.util.List;

public class Tab2Fragment extends Fragment implements MainActivity.Updateable {


    private Context context;
    List<Movie> taskList;
    GridView gridView;
    FavItemAdapter favItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        context = getContext();
        gridView = view.findViewById(R.id.grid);

        getTasks();

        return view;
    }

    public void getTasks() {

        GetTasks gt = new GetTasks();
        gt.execute();

    }

    @Override
    public void update() {
        if (favItemAdapter != null) {
            favItemAdapter.notifyDataSetChanged();
        } else {
            Log.d(">>>>", "null");
        }
    }

    class GetTasks extends AsyncTask<Void, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            taskList = DatabaseClient
                    .getInstance(context)
                    .getAppDatabase()
                    .movieDao()
                    .getAll();
            return taskList;
        }

        @Override
        protected void onPostExecute(List<Movie> tasks) {
            super.onPostExecute(tasks);
            Log.d("MovieSavedSize>>>>", taskList.size() + "");
            favItemAdapter = new FavItemAdapter(context, taskList);
            favItemAdapter.notifyDataSetChanged();
            gridView.setAdapter(favItemAdapter);

        }
    }
}
