package com.eros.favmovies.view.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.eros.favmovies.view.model.Movie;

@Database(entities = {Movie.class}, version = 4,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "MyFavMovie";

    public abstract MovieDao movieDao();
}