package online.earnmoney.com.makemoney_earnonline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Abhishek on 25/11/2017.
 */

public class WalletFragment extends Fragment {

    InterstitialAd mInterstitialAd;
    FloatingActionButton shareFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        mInterstitialAd = new InterstitialAd(getContext());
        shareFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.share_button);
        mInterstitialAd.setAdUnitId(getString(R.string.wallet_intetstitial));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        shareFloatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return view;
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
