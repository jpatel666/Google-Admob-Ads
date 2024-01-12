package com.iw.googleadmobads;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerAdsActivity extends AppCompatActivity {
    private AdView adView;

    LinearLayout adContainerView;
    private boolean initialLayoutComplete = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ads);

        banner1();
        banner2();
        bannerAdaptive();
    }


    void banner1() {
        //XML Side
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    void banner2() {
        //Java Side
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.SMART_BANNER);

        adView.setAdUnitId(getResources().getString(R.string.banner_id));

        linearLayout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    void bannerAdaptive() {
        //Adaptive Banners
        adContainerView = findViewById(R.id.linearAdaptive);

        adView = new AdView(this);
        adContainerView.addView(adView);

        adContainerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (!initialLayoutComplete) {
                            initialLayoutComplete = true;
                            loadBanner();
                        }
                    }
                });
    }

    private void loadBanner() {
        adView.setAdUnitId(getString(R.string.banner_id));

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);


        AdRequest adRequest =
                new AdRequest.Builder().build();


        adView.loadAd(adRequest);
    }


    @SuppressLint({"NewApi", "LocalSuppress"})
    private AdSize getAdSize() {
        WindowMetrics windowMetrics = getWindowManager().getCurrentWindowMetrics();
        Rect bounds = windowMetrics.getBounds();

        float adWidthPixels = adContainerView.getWidth();

        if (adWidthPixels == 0f) {
            adWidthPixels = bounds.width();
        }

        float density = getResources().getDisplayMetrics().density;
        int adWidth = (int) (adWidthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
}