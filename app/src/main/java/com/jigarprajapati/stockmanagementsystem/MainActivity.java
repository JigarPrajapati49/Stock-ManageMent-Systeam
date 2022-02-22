package com.jigarprajapati.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.jigarprajapati.stockmanagementsystem.Utility.NetworkChangeListner;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    LottieAnimationView lottieAnimationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.splshlogo);
        lottieAnimationView = findViewById(R.id.lottie);

        logo.animate().translationY(1600).setDuration(5000).setStartDelay(10000);
        lottieAnimationView.animate().translationY(1600).setDuration(5000).setStartDelay(10000);

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);

    }


}