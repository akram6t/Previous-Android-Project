package com.codexproject.codewithui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.codexproject.codewithui.Fragments.CodeFragment;
import com.codexproject.codewithui.Fragments.HomeFragment;
import com.codexproject.codewithui.Fragments.SavedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity{
    HomeFragment homeFragment;
    CodeFragment codeFragment;
    SavedFragment savedFragment;
    BottomNavigationView navigationView;
    ViewPager viewPager;
    MenuItem prevMenuItem;
    Toolbar toolbar;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.customtoolbar);
        btnSearch = findViewById(R.id.id_search);

        setSupportActionBar(toolbar);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuhome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menucode:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menusaved:
                        viewPager.setCurrentItem(2);
                        break;
                }

                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                navigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        codeFragment = new CodeFragment();
        savedFragment = new SavedFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(codeFragment);
        adapter.addFragment(savedFragment);
        viewPager.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}