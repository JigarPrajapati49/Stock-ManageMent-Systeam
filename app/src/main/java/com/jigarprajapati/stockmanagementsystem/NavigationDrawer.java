package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;
import com.jigarprajapati.stockmanagementsystem.Fragment.AboutApp;
import com.jigarprajapati.stockmanagementsystem.Fragment.Home;
import com.jigarprajapati.stockmanagementsystem.Fragment.RateApp;
import com.jigarprajapati.stockmanagementsystem.Fragment.ShareApp;
import com.jigarprajapati.stockmanagementsystem.Fragment.UserGuidelines;

public class NavigationDrawer extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        frameLayout = findViewById(R.id.fragment);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
      //  setSupportActionBar(toolbar);

        fragment = new Home();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
        initNavigationMenu();
    }

    private void initNavigationMenu() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        final DrawerLayout drawerLayout = findViewById(R.id.drawable_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigationOn,R.string.NavigationOF) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.AboutApp:
                    fragment = new AboutApp();
                    toolbar.setTitle("About App");
                    navigationView.setCheckedItem(R.id.AboutApp);
                    break;
                case R.id.HOME:
                    toolbar.setTitle("Home");
                    fragment = new Home();
                    navigationView.setCheckedItem(R.id.HOME);
                    break;
                case R.id.RateApp:
                    toolbar.setTitle("Rate App");
                    fragment = new RateApp();
                    navigationView.setCheckedItem(R.id.RateApp);
                    break;
                case R.id.ShareApp:
                    toolbar.setTitle("Share App");
                    fragment = new ShareApp();
                    navigationView.setCheckedItem(R.id.ShareApp);
                    break;
                case R.id.UserGuidlines:
                    toolbar.setTitle("User Guidlines");
                    fragment = new UserGuidelines();
                    navigationView.setCheckedItem(R.id.UserGuidlines);
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            drawerLayout.closeDrawers();
            return true;
        });
    }

}