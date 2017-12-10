package online.earnmoney.com.makemoney_earnonline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import online.earnmoney.com.makemoney_earnonline.Adapters.RecyclerViewAdapter;
import online.earnmoney.com.makemoney_earnonline.Adapters.RecyclerViewAdapter.ItemOnClickListner;

/**
 * Created by Abhishek on 25/11/2017.
 */

public class HomeFragment extends Fragment implements ItemOnClickListner {

    private WebView webView;
    private AdView mAdView;
    private Button btnFullscreenAd;
    InterstitialAd mInterstitialAd;
    private Context mCtx;
    private DatabaseReference firebaseDatabase;
    private ArrayList<String> imageUrl;
    private ArrayList<String> newsHeading;
    private ArrayList<NewsClass> newsBundle;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLocale();
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        imageUrl = new ArrayList<>();
        newsHeading = new ArrayList<>();
        newsBundle = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        mInterstitialAd = new InterstitialAd(getContext());
       /* webView = (WebView) v.findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("http://www.google.com");*/
        // set the ad unit ID


        mCtx = getContext();
        mInterstitialAd.setAdUnitId(getString(R.string.offer_interstitial));

        AdRequest adRequest = new Builder()
                .build();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("random-content");

        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newsBundle.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        try {
                            NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                            newsBundle.add(newsClass);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    recyclerViewAdapter = new RecyclerViewAdapter(getContext(), newsBundle, HomeFragment.this);

                    recyclerView.setAdapter(recyclerViewAdapter);

                } else {
                    Toast.makeText(mCtx, "Empty Datasnapshot Object", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        return v;
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onClick(int position) {


    }

    public static class BitcoinPriceReceiver extends BroadcastReceiver {

        public BitcoinPriceReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Bundle bundle = intent.getExtras();
            if (action.equals(NetworkUtils.BITCOIN_ZEBPAY)) {


            }
        }
    }

    public HomeFragment() {
    }


    public void setLocale() {
        String languageToLoad = "hi"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config,
                getContext().getResources().getDisplayMetrics());
    }

    // Imports the Google Cloud client library


}
