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
import android.widget.ProgressBar;
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

import online.earnmoney.com.makemoney_earnonline.Adapters.NewsRecyclerViewAdapter;
import online.earnmoney.com.makemoney_earnonline.Adapters.NewsRecyclerViewAdapter.OnItemClickListner;
import online.earnmoney.com.makemoney_earnonline.Adapters.RecyclerViewAdapter;
import online.earnmoney.com.makemoney_earnonline.Adapters.RecyclerViewAdapter.ItemOnClickListner;

/**
 * Created by Abhishek on 25/11/2017.
 */

public class HomeFragment extends Fragment implements ItemOnClickListner, OnItemClickListner {

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
    RecyclerView sportsRecyclerViw;
    RecyclerView polticsRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private View view;

    private DatabaseReference entertainment;

    private RecyclerView entertainmentRecyclerView;

    private ArrayList<NewsClass> sportsArrayList;
    private ArrayList<NewsClass> politicsArrayList;
    private ArrayList<NewsClass> technologyArrayList;
    private ArrayList<NewsClass> healthArrayList;
    private ArrayList<NewsClass> entertainmentArrayList;
    private ArrayList<NewsClass> weatherArrayList;
    private ArrayList<NewsClass> glamourArrayList;
    private ArrayList<NewsClass> bollywoodArrayList;
    private ArrayList<NewsClass> jokesArrayList;

    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private DatabaseReference sportsDatabaseReference;
    private DatabaseReference politicsDatabaseReference;

    private ArrayList<String> newsId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLocale();
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        imageUrl = new ArrayList<>();
        sportsArrayList = new ArrayList<>();
        politicsArrayList = new ArrayList<>();
        technologyArrayList = new ArrayList<>();
        healthArrayList = new ArrayList<>();
        entertainmentArrayList = new ArrayList<>();
        weatherArrayList = new ArrayList<>();
        glamourArrayList = new ArrayList<>();
        bollywoodArrayList = new ArrayList<>();
        jokesArrayList = new ArrayList<>();
        newsId = new ArrayList<>();

        newsHeading = new ArrayList<>();
        newsBundle = new ArrayList<>();

        sportsRecyclerViw = (RecyclerView) v.findViewById(R.id.sports_recycler_view);
        polticsRecyclerView = (RecyclerView) v.findViewById(R.id.politics_recycler_view);
        entertainment = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.ENTERTAINMENT_SECTION);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        entertainmentRecyclerView = (RecyclerView) v.findViewById(R.id.entertainmentRecyclerView);

        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(), entertainmentArrayList, HomeFragment.this);
        entertainmentRecyclerView.setHasFixedSize(true);

        entertainmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        entertainmentRecyclerView.setAdapter(newsRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_drawable));
        mInterstitialAd = new InterstitialAd(getContext());

        LinearLayoutManager sportsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        sportsLayoutManager.setReverseLayout(true);
        sportsLayoutManager.setStackFromEnd(true);
        sportsRecyclerViw.setHasFixedSize(true);
        sportsRecyclerViw.setLayoutManager(sportsLayoutManager);

        LinearLayoutManager politicsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        politicsLayoutManager.setReverseLayout(true);
        politicsLayoutManager.setStackFromEnd(true);
        polticsRecyclerView.setHasFixedSize(true);
        polticsRecyclerView.setLayoutManager(politicsLayoutManager);

        getSportsNews();
        getTopHeadLines();
        getPoliticsNews();
        getEntertainmentNews();


        mCtx = getContext();
        mInterstitialAd.setAdUnitId(getString(R.string.offer_interstitial));

        AdRequest adRequest = new Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        view = v;
        return v;
    }


    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onClick(int position, int index) {


        Bundle bundle = new Bundle();
        switch (index) {
            case ConstantUtils.RANDOM_VIEW_TYPE:

                bundle.putString(ConstantUtils.NEWS_HEADING, newsBundle.get(position).getmNewsHeading());
                bundle.putString(ConstantUtils.NEWS_IMAGE_URL, newsBundle.get(position).getmNewsUrl());
                bundle.putString(ConstantUtils.NEWS_DESCRIPTION, newsBundle.get(position).getmNewsDescription());
                bundle.putString(ConstantUtils.NEWS_URL, newsBundle.get(position).getmNewsShot());
                bundle.putString(ConstantUtils.SECTION, ConstantUtils.RANDOM_SECTION);
                bundle.putString(ConstantUtils.NEWS_ID,newsBundle.get(position).getmNewsId());

                break;
            case ConstantUtils.SPORTS_VIEW_TYPE:

                bundle.putString(ConstantUtils.NEWS_HEADING, sportsArrayList.get(position).getmNewsHeading());
                bundle.putString(ConstantUtils.NEWS_IMAGE_URL, sportsArrayList.get(position).getmNewsUrl());
                bundle.putString(ConstantUtils.NEWS_DESCRIPTION, sportsArrayList.get(position).getmNewsDescription());
                bundle.putString(ConstantUtils.NEWS_URL, sportsArrayList.get(position).getmNewsShot());
                bundle.putString(ConstantUtils.SECTION, ConstantUtils.SPORTS_SECTION);

                break;
            case ConstantUtils.POLITICS_VIEW_TYPE:

                bundle.putString(ConstantUtils.NEWS_HEADING, politicsArrayList.get(position).getmNewsHeading());
                bundle.putString(ConstantUtils.NEWS_IMAGE_URL, politicsArrayList.get(position).getmNewsUrl());
                bundle.putString(ConstantUtils.NEWS_DESCRIPTION, politicsArrayList.get(position).getmNewsDescription());
                bundle.putString(ConstantUtils.NEWS_URL, politicsArrayList.get(position).getmNewsShot());
                bundle.putString(ConstantUtils.SECTION, ConstantUtils.POLITICS_SECTION);

                break;

        }


        Intent intent = new Intent(getContext(), FullNewsActivity.class);
        //   ActivityOptionsCompat activityCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getA, view.findViewById(R.id.full_news_image), "myImage");

        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onItemClick(int position) {

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

    public void getTopHeadLines() {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.RANDOM_SECTION);

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
                    recyclerViewAdapter = new RecyclerViewAdapter(getContext(), newsBundle, HomeFragment.this, ConstantUtils.RANDOM_VIEW_TYPE);

                    recyclerView.setAdapter(recyclerViewAdapter);

                    if (recyclerViewAdapter.getItemCount() > 0) {
                        progressBar.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(mCtx, "Empty Datasnapshot Object", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getSportsNews() {

        sportsDatabaseReference = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.SPORTS_SECTION);

        sportsDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sportsArrayList.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        try {
                            NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                            sportsArrayList.add(newsClass);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    RecyclerViewAdapter sportsAdapter = new RecyclerViewAdapter(getContext(), sportsArrayList, HomeFragment.this, ConstantUtils.SPORTS_VIEW_TYPE);

                    sportsRecyclerViw.setAdapter(sportsAdapter);

                    if (sportsAdapter.getItemCount() > 0) {
                        progressBar.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(mCtx, "Empty Data snapshot Object", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getPoliticsNews() {
        politicsDatabaseReference = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.POLITICS_SECTION);

        politicsDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                politicsArrayList.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        try {
                            NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                            politicsArrayList.add(newsClass);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    RecyclerViewAdapter politics = new RecyclerViewAdapter(getContext(), politicsArrayList, HomeFragment.this, ConstantUtils.POLITICS_VIEW_TYPE);

                    polticsRecyclerView.setAdapter(politics);

                    if (politics.getItemCount() > 0) {
                        progressBar.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(mCtx, "Empty Data snapshot Object", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getEntertainmentNews() {


        entertainment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entertainmentArrayList.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        try {
                            NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                            entertainmentArrayList.add(newsClass);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    newsRecyclerViewAdapter =
                            new NewsRecyclerViewAdapter(getContext(), entertainmentArrayList, HomeFragment.this);

                    entertainmentRecyclerView.setAdapter(newsRecyclerViewAdapter);

                    if (newsRecyclerViewAdapter.getItemCount() > 0) {
                        progressBar.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(mCtx, "Empty Datasnapshot Object", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}