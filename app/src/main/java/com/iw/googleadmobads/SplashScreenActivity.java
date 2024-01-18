package com.iw.googleadmobads;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Google Admob Ads
        //1.Inter... Ads:- Explicit Intent, Back
        //2.Banner Ads:- One Ads In One Page
        //3.Native Ads:- 60% Content And 40% Ads, In Scroll If Possible
        //4.AppOpen Ads:- onResume

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Threads [Splash Screen]
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, BannerAdsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
}