package online.earnmoney.com.makemoney_earnonline.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import online.earnmoney.com.makemoney_earnonline.Adapters.GridAdapter;
import online.earnmoney.com.makemoney_earnonline.Adapters.NewsRecyclerViewAdapter;
import online.earnmoney.com.makemoney_earnonline.Adapters.NewsRecyclerViewAdapter.OnItemClickListner;
import online.earnmoney.com.makemoney_earnonline.ConstantUtils;
import online.earnmoney.com.makemoney_earnonline.NewsClass;
import online.earnmoney.com.makemoney_earnonline.R;

/**
 * Created by Abhishek on 05/12/2017.
 */

public class National extends Fragment implements OnItemClickListner {

    private RecyclerView news_recycler_view;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference national_news_databaseReference;
    private ArrayList<NewsClass> national_arrayList;
    NewsRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView gridRecyclerView;
    private GridLayoutManager lLayout;

    private ArrayList<NewsClass> gridNewsData;

    GridAdapter gridAdapter;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setLocale();
        View view = inflater.inflate(R.layout.national_fragment, container, false);
        news_recycler_view = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        news_recycler_view.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.getStackFromEnd();
        news_recycler_view.setLayoutManager(linearLayoutManager);

        national_arrayList = new ArrayList<>();
        gridRecyclerView = (RecyclerView) view.findViewById(R.id.news_grid_recycler_view);
        national_news_databaseReference = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.NATION_KEY);
        fetchFirebaseData(national_news_databaseReference);
        gridNewsData = new ArrayList<>();
        gridAdapter = new GridAdapter(getContext(), gridNewsData);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.NEWS_SECTION);
        lLayout = new GridLayoutManager(getContext(), 3);
        gridRecyclerView.setHasFixedSize(true);
        lLayout.setReverseLayout(true);
        gridRecyclerView.setLayoutManager(lLayout);

        getGridNAtionalNews();
        return view;

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


    void fetchFirebaseData(final DatabaseReference databaseReference) {


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                national_arrayList.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                        national_arrayList.add(newsClass);
                    }
                    recyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(), national_arrayList, (OnItemClickListner) National.this);

                    news_recycler_view.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onItemClick(int position) {

    }

    public void getGridNAtionalNews() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gridNewsData.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                        gridNewsData.add(newsClass);
                    }
                    GridAdapter gridAdapter = new GridAdapter(getContext(), gridNewsData);
                    gridRecyclerView.setAdapter(gridAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
