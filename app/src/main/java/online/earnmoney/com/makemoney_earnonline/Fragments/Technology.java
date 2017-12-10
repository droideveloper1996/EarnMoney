package online.earnmoney.com.makemoney_earnonline.Fragments;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import online.earnmoney.com.makemoney_earnonline.Adapters.RecyclerViewAdapter;
import online.earnmoney.com.makemoney_earnonline.Adapters.RecyclerViewAdapter.ItemOnClickListner;
import online.earnmoney.com.makemoney_earnonline.ConstantUtils;
import online.earnmoney.com.makemoney_earnonline.NewsClass;
import online.earnmoney.com.makemoney_earnonline.R;

/**
 * Created by Abhishek on 05/12/2017.
 */

public class Technology extends Fragment implements ItemOnClickListner {
    RecyclerView technologyRecyclerView;
    ArrayList<NewsClass> technology;
    RecyclerViewAdapter recyclerViewAdapter;
    private DatabaseReference technologyDatabaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLocale();
        View view = inflater.inflate(R.layout.technology_fagment, container, false);
        technology = new ArrayList<>();
        technologyRecyclerView = (RecyclerView) view.findViewById(R.id.technology_recycler_view);
        technologyRecyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        technologyRecyclerView.setHasFixedSize(true);
        technologyRecyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), technology, Technology.this);
        technologyDatabaseReference = FirebaseDatabase.getInstance().getReference().child(ConstantUtils.TECHNOLOGY_KEY);


        technologyDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                technology.clear();
                if (dataSnapshot != null) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        try {
                            NewsClass newsClass = dataSnapshot1.getValue(NewsClass.class);
                            technology.add(newsClass);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    recyclerViewAdapter=new RecyclerViewAdapter(getContext(),technology,Technology.this);
                    technologyRecyclerView.setAdapter(recyclerViewAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
    }
}
