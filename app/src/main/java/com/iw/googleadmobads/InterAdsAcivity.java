package com.iw.googleadmobads;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class InterAdsAcivity extends AppCompatActivity {

    Button btnInterads, btnTwoInterads;

    private InterstitialAd mInterstitialAd;
    int choice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_ads_acivity);

        btnInterads = findViewById(R.id.btnInterads);
        btnTwoInterads = findViewById(R.id.btnTwoInterads);

        loadInter();
        btnInterads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 1;
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(InterAdsAcivity.this);
                } else {
                    //Button Code Here Like Intent etc...
                }
            }
        });

        btnTwoInterads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 2;
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(InterAdsAcivity.this);
                } else {
                    //Button Code Here Like Intent etc...
                }

            }
        });


    }

    void loadInter() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, getResources().getString(R.string.inter_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {

                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                mInterstitialAd = null;

                                if (choice == 1) {
                                    //Button Code Here Like Intent etc...

                                    //start & finish()
                                } else if (choice == 2) {
                                    //Button Code Here Like Intent etc...

                                    //start

                                    loadInter();
                                }

                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {

                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }
}