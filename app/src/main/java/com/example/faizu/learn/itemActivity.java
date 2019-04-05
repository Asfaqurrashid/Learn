package com.example.faizu.learn;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;
import android.content.Intent;

public class itemActivity extends AppCompatActivity {
    private BottomNavigationView bottomnav;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        bottomnav = (BottomNavigationView)findViewById(R.id.navigation);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("MENU");
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        bottomnav.setItemBackgroundResource(R.color.colorPrimaryDark);
        toolbarBackgroundColor();
        loadFragment(new HomeFragment());
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Fragment fragment = null;
                if(id == R.id.nav_account)
                {
                    fragment = new AccountFragment();
                    toolbar.setTitle("ACCOUNT");
                    loadFragment(fragment);
                    return true;
                }
                else if(id == R.id.nav_home)
                {
                    fragment = new HomeFragment();
                    toolbar.setTitle("MENU");
                    loadFragment(fragment);
                    return true;
                }
                else if(id == R.id.nav_chart)
                {
                    fragment = new ChartFragment();
                    toolbar.setTitle("MY CART");
                    loadFragment(fragment);
                    return true;
                }
                else if(id==R.id.nav_search)
                {
                    fragment = new SearchFragment();
                    toolbar.setTitle("SEARCH");
                    loadFragment(fragment);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
         if(id == R.id.setting)
        {
            Intent intent = new Intent(getApplicationContext(),SettingAcitivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }

    public  void toolbarBackgroundColor()
    {
        Intent intent = getIntent();
        int number  = intent.getIntExtra("EXTRA_NUMBER",0);
        if(number == 0)
        {
            ActionBar bar = getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
            bottomnav.setItemBackgroundResource(R.color.colorPrimaryDark);
        }
        if(number == 1)
        {
            ActionBar bar = getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
            bottomnav.setItemBackgroundResource(R.color.colorPrimaryDark);

        }
        if(number == 2)
        {
            ActionBar bar = getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
            bottomnav.setItemBackgroundResource(R.color.blue);

        }
        if(number == 3)
        {
            ActionBar bar = getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
            bottomnav.setItemBackgroundResource(R.color.colorAccent);

        }
        if(number == 4)
        {
            ActionBar bar = getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
            bottomnav.setItemBackgroundResource(R.color.gray);

        }
    }




}

