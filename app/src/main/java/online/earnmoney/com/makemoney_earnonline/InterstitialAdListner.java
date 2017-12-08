package online.earnmoney.com.makemoney_earnonline;


import android.content.Context;

import com.google.android.gms.ads.AdListener;

/**
 * Created by Abhishek on 04/12/2017.
 */

public class InterstitialAdListner extends AdListener {

    Context ctx;

    public InterstitialAdListner() {
        super();
    }

    public InterstitialAdListner(Context context) {
        this.ctx = context;
    }


    @Override
    public void onAdClosed() {
        super.onAdClosed();
    }

    @Override
    public void onAdFailedToLoad(int i) {
        super.onAdFailedToLoad(i);
    }

    @Override
    public void onAdLeftApplication() {
        super.onAdLeftApplication();
    }

    @Override
    public void onAdOpened() {
        super.onAdOpened();
    }

    @Override
    public void onAdLoaded() {
        super.onAdLoaded();
    }

    @Override
    public void onAdClicked() {
        super.onAdClicked();
    }

    @Override
    public void onAdImpression() {
        super.onAdImpression();
    }
}
