package online.earnmoney.com.makemoney_earnonline;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FullNewsActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView full_news_source_info;

    String NewsURL;
    int like, unlike = 0;
    String NewsImageUrl = "";
    String NewsDescription = "";
    private Bundle mBundle;
    private String NewsHeading;
    TextView full_news_heading;
    TextView first_news_descriptionn;
    TextView second_news_descriptionn;
    TextView third_news_descriptionn;
    TextView forth_news_descriptionn;
    private AdView mAdView1;
    private AdView mAdView2;
    private AdView mAdView3;
    private Boolean likeClick = false;
    private Boolean unLikeClick = false;
    private ImageView likeButtonImage;
    private ImageView unLikeButtonImage;
    private TextView likeTextView;
    private TextView unLikeTextView;
    private String mNewsSection;

    private String mNewsId = "";

    DatabaseReference publisherDatabaseReference;
    DatabaseReference newsDataBaseReference;


    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        mAdView1 = (AdView) findViewById(R.id.adView1);
        mAdView2 = (AdView) findViewById(R.id.adView2);
        mAdView3 = (AdView) findViewById(R.id.adView3);
        likeButtonImage = (ImageView) findViewById(R.id.likeButton);
        unLikeButtonImage = (ImageView) findViewById(R.id.unlikeButton);
        likeTextView = (TextView) findViewById(R.id.likeTextView);
        unLikeTextView = (TextView) findViewById(R.id.dislikeTextView);

        like = Integer.parseInt(likeTextView.getText().toString());
        unlike = Integer.parseInt(unLikeTextView.getText().toString());

        Toast.makeText(getApplicationContext(), String.valueOf(like), Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);
        mAdView2.loadAd(adRequest);
        mAdView3.loadAd(adRequest);

        forth_news_descriptionn = (TextView) findViewById(R.id.forth_news_descriptionn);
        full_news_source_info = (TextView) findViewById(R.id.full_news_source_info);
        first_news_descriptionn = (TextView) findViewById(R.id.first_news_descriptionn);
        second_news_descriptionn = (TextView) findViewById(R.id.second_news_descriptionn);
        third_news_descriptionn = (TextView) findViewById(R.id.third_news_descriptionn);
        mImageView = (ImageView) findViewById(R.id.full_news_image);
        NewsDescription = "";
        NewsImageUrl = "";
        NewsURL = "";
        NewsHeading = "";
        full_news_heading = (TextView) findViewById(R.id.full_news_heading);
        if (getIntent() != null) {
            mBundle = getIntent().getExtras();

            try {

                NewsDescription = mBundle.getString(ConstantUtils.NEWS_DESCRIPTION);
                NewsImageUrl = mBundle.getString(ConstantUtils.NEWS_URL);
                NewsURL = mBundle.getString(ConstantUtils.NEWS_IMAGE_URL);
                NewsHeading = mBundle.getString(ConstantUtils.NEWS_HEADING);
                mNewsSection = mBundle.getString(ConstantUtils.SECTION);
                mNewsId = mBundle.getString(ConstantUtils.NEWS_ID);
                Toast.makeText(getApplicationContext(), mNewsId, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * General Database Reference for news Section;
         */
       // newsDataBaseReference = FirebaseDatabase.getInstance().getReference().child(mNewsSection).child(mNewsId);


        int length = NewsDescription.length();
        int div1 = length / 4;
        Picasso.with(this).load(NewsURL).into(mImageView);
        //  full_news_source_info.setText(NewsDescription);
        full_news_heading.setText(NewsHeading);
        first_news_descriptionn.setText(NewsDescription.substring(0, div1));
        second_news_descriptionn.setText(NewsDescription.substring(div1, 2 * div1));
        third_news_descriptionn.setText(NewsDescription.substring(2 * div1, 3 * div1));
        forth_news_descriptionn.setText(NewsDescription.substring(3 * div1));

        collapsingToolbarLayout.setTitle(NewsHeading);

        likeButtonImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update The likes for news in publisher db and also in news db

                if (!likeClick) {
                    likeButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.like));
                    unLikeButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_down_black_24dp));
                    ++like;
                    --unlike;
                    likeTextView.setText(String.valueOf(like));
                    unLikeTextView.setText(String.valueOf(unlike));
                    unLikeClick = false;
                    likeClick = true;

                      hitLikeButton();
                    /**
                     * IF Like Btn is CLicked update the no of Like in News and Publisher database;
                     */
                }

            }
        });
        unLikeButtonImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!unLikeClick) {
                    unLikeButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.dislike));
                    likeButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_up_black_24dp));

                    --like;
                    ++unlike;
                    //update the unlike for news in publisher db and also in news db

                    likeTextView.setText(String.valueOf(like));
                    unLikeTextView.setText(String.valueOf(unlike));
                    unLikeClick = true;
                    likeClick = false;
                    // hitUnLikeButton();
                    /**
                     * IF Like Btn is CLicked update the no of disLike in News and Publisher database;
                     */
                }
            }
        });
    }


    void hitLikeButton() {
        newsDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    NewsClass newsClass = dataSnapshot.getValue(NewsClass.class);
                    Log.d("FullNewsActivity", newsClass.getmNewsDescription());
                    Log.d("FullNewsActivity", newsClass.getmNewsHeading());
                    Log.d("FullNewsActivity", newsClass.getmPublisherId());
                    Log.d("FullNewsActivity", newsClass.getmNewsId());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void hitUnLikeButton() {
        newsDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
