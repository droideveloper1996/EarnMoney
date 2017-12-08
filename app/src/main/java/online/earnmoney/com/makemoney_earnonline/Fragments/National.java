package online.earnmoney.com.makemoney_earnonline.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import online.earnmoney.com.makemoney_earnonline.R;

/**
 * Created by Abhishek on 05/12/2017.
 */

public class National extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

setLocale();
        return inflater.inflate(R.layout.national_fragment,container,false);

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
}
