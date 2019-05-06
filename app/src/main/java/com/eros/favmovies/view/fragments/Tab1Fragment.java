package com.eros.favmovies.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eros.favmovies.R;
import com.eros.favmovies.view.adapter.ItemAdapter;
import com.eros.favmovies.view.constants.AppConstants;
import com.eros.favmovies.view.model.Movie;
import com.eros.favmovies.view.utils.NetworkStatus;
import com.eros.favmovies.view.viewmodel.ItemViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class Tab1Fragment extends Fragment {

    private RecyclerView rcView;
    private Context context;
    private ShimmerFrameLayout shimmerViewContainer;
    private ItemAdapter adapter;
    private NetworkStatus networkStatus;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        rcView = v.findViewById(R.id.image_list);
        shimmerViewContainer = v.findViewById(R.id.shimmer_view_container);

        rcView.setLayoutManager(new LinearLayoutManager(context));
        rcView.setHasFixedSize(true);
        if(!NetworkStatus.isOnline(context))
        {
            getActivity().finish();
        }

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        adapter = new ItemAdapter(context);
        showLoadingIndicator(true);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable final PagedList<Movie> items) {

                if (shimmerViewContainer.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                rcView.setVisibility(View.VISIBLE);
                adapter.submitList(items);

            }
        });

        rcView.setAdapter(adapter);
        return v;
    }


    public void showLoadingIndicator(boolean active) {
        if (active) {
            shimmerViewContainer.setVisibility(View.VISIBLE);
            shimmerViewContainer.startShimmerAnimation();
        } else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    shimmerViewContainer.stopShimmerAnimation();
                    shimmerViewContainer.setVisibility(View.GONE);
                }
            }, 2000);

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        shimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        shimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

}
