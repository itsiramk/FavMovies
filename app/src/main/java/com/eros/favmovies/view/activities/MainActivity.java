package com.eros.favmovies.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.eros.favmovies.R;
import com.eros.favmovies.view.adapter.TabAdapter;
import com.eros.favmovies.view.fragments.Tab1Fragment;
import com.eros.favmovies.view.fragments.Tab2Fragment;

public class MainActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;
    private int[] tabIcons = {
            R.drawable.ic_star_unselected,
            R.drawable.ic_home_unselected,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager(), context);
        adapter.addFragment(new Tab1Fragment(), "Tab 1", tabIcons[0]);
        adapter.addFragment(new Tab2Fragment(), "Tab 2", tabIcons[1]);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        highLightCurrentTab(0); // for initial selected tab view


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment= adapter.getItem(position);
                if(fragment instanceof Tab2Fragment ){
                    ((Tab2Fragment)fragment).getTasks();
                }
                adapter.notifyDataSetChanged();

                highLightCurrentTab(position); // for tab change

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void highLightCurrentTab(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(adapter.getTabView(i));
        }

        TabLayout.Tab tab = tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(adapter.getSelectedTabView(position));
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        if (!(adapter == null)) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.clearOnPageChangeListeners();

    }*/



    public interface Updateable {
        public void update();
    }
}


