package online.earnmoney.com.makemoney_earnonline;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    TabLayout tabLayout;
    private static final String TAG = "";
    private RewardedVideoAd rewardedVideoAd;
    TextView rewardtextView;
    int totalReward = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    TextView toolbarTitleTextView;
    CircleImageView my_image;
    LayoutInflater layoutInflater;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarTitleTextView = (TextView) findViewById(R.id.toolbartext);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mNavigationView.inflateHeaderView(R.layout.header_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, (R.string.True), R.string.False);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = mNavigationView.getHeaderView(0);
        TextView txt = (TextView) headerView.findViewById(R.id.user_account);
        my_image = (CircleImageView) headerView.findViewById(R.id.my_picture);
        Picasso.with(this).load(R.drawable.my_image).into(my_image);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        FragmentStatePagerAdapter viewPagerAdapter = new FragmentStatePageAdap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rewardtextView = (TextView) findViewById(R.id.reward);
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.rewarded_ad_unit_id));
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        //rewardedVideoAd.setRewardedVideoAdListener(new RewardedAdManager(MainActivity.this, rewardtextView));
        //loadRewardedVideoAd();
        if (rewardedVideoAd.isLoaded()) {
            // rewardedVideoAd.show();
        }


        fragmentTransact();
     /*   FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment()).commit();
*/


        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "getDynamicLink:onFailure", e);
                    }
                });
    }

    private void fragmentTransact() {


        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_wallet:
                        fragmentTransaction.replace(R.id.fragment_container, new WalletFragment()).commit();

                        break;
                    case R.id.action_refer:
                        fragmentTransaction.replace(R.id.fragment_container, new ReferFragment()).commit();

                        break;
                    case R.id.action_offer:
                        fragmentTransaction.replace(R.id.fragment_container, new OfferFragment()).commit();

                        break;
                    case R.id.action_home:
                      //   fragmentTransaction.replace(R.id.fragment_container, new HomeFragment()).commit();

                        break;

                }
                return true;
            }
        });


        SharedPreferences languagepref = getSharedPreferences("LANGUAGE-APP", MODE_PRIVATE);
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("languageToLoad", "hi");
        editor.commit();
        setupTabTitle();

        getSupportFragmentManager().popBackStack();
    }

    private void loadRewardedVideoAd() {
        if (!rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.loadAd(getResources().getString(R.string.rewarded_ad_unit_id),
                    new AdRequest.Builder().build());
        }
    }

    @Override
    protected void onPause() {
        rewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //  Toast.makeText(this, " Video Loaded ", Toast.LENGTH_SHORT).show();
        rewardedVideoAd.show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //  Toast.makeText(this, " Video Opened ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoStarted() {
        //   Toast.makeText(this, " Video Started ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        // Toast.makeText(this, " Video Closed ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Toast.makeText(this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
        totalReward += rewardItem.getAmount();
        rewardtextView.setText(Integer.toString(totalReward));
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.action_notification:
                setLocale();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setLocale() {
        String languageToLoad = "hi"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }


    void setupTabTitle() {

        tabLayout.getTabAt(0).setText(getResources().getText(R.string.for_me));
        tabLayout.getTabAt(1).setText(getResources().getText(R.string.my_city));
        tabLayout.getTabAt(2).setText(getResources().getText(R.string.national_news));
        tabLayout.getTabAt(3).setText(getResources().getText(R.string.technology));
        tabLayout.getTabAt(4).setText(getResources().getText(R.string.health));
        tabLayout.getTabAt(5).setText(getResources().getText(R.string.entertainment));
        tabLayout.getTabAt(6).setText(getResources().getText(R.string.weather));
        tabLayout.getTabAt(7).setText(getResources().getText(R.string.glamour));
        tabLayout.getTabAt(8).setText(getResources().getText(R.string.bollywood));
        tabLayout.getTabAt(9).setText(getResources().getText(R.string.joke));
        tabLayout.getTabAt(10).setText(getResources().getText(R.string.politics));
        tabLayout.getTabAt(11).setText(getResources().getText(R.string.man_ki_baat));


    }

    @Override
    public void onBackPressed() {
        return;
    }
}
