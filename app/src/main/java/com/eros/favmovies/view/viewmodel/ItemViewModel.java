package com.eros.favmovies.view.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.content.Context;
import android.widget.Toast;

import com.eros.favmovies.view.constants.AppConstants;
import com.eros.favmovies.view.model.Movie;
import com.eros.favmovies.view.utils.ItemDataSource;
import com.eros.favmovies.view.utils.ItemDataSourceFactory;
import com.eros.favmovies.view.utils.NetworkStatus;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Movie>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, Movie>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
         PagedList.Config pagedListConfig =
                    (new PagedList.Config.Builder())
                            .setEnablePlaceholders(false)
                            .setPageSize(ItemDataSource.PAGE_SIZE).build();

            itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                    .build();

    }
}
