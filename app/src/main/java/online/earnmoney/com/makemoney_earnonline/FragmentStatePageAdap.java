package online.earnmoney.com.makemoney_earnonline;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import online.earnmoney.com.makemoney_earnonline.Fragments.BollywoodFragment;
import online.earnmoney.com.makemoney_earnonline.Fragments.Entertainment;
import online.earnmoney.com.makemoney_earnonline.Fragments.GalleryFragment;
import online.earnmoney.com.makemoney_earnonline.Fragments.Glamour;
import online.earnmoney.com.makemoney_earnonline.Fragments.Health;
import online.earnmoney.com.makemoney_earnonline.Fragments.JokeFragment;
import online.earnmoney.com.makemoney_earnonline.Fragments.ManKiBaatFragment;
import online.earnmoney.com.makemoney_earnonline.Fragments.MyCityFragment;
import online.earnmoney.com.makemoney_earnonline.Fragments.National;
import online.earnmoney.com.makemoney_earnonline.Fragments.Technology;
import online.earnmoney.com.makemoney_earnonline.Fragments.Weather;

/**
 * Created by Abhishek on 05/12/2017.
 */

public class FragmentStatePageAdap extends FragmentStatePagerAdapter {
    public FragmentStatePageAdap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MyCityFragment();
            case 2:
                return new National();
            case 3:
                return new Technology();
            case 4:
                return new Health();
            case 5:
                return new Entertainment();
            case 6:
                return new Weather();
            case 7:
                return new Glamour();
            case 8:
                return new BollywoodFragment();
            case 9:
                return new JokeFragment();
            case 10:
                return new GalleryFragment();
            case 11:
                return new ManKiBaatFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "For Me";
            case 1:
                return "My City";
            case 2:
                return "News";
            case 3:
                return "Technology";
            case 4:
                return "Health";
            case 5:
                return "Entertainment";
            case 6:
                return "Weather";
            case 7:
                return "Glamour";
            case 8:
                return "B-Town";
            case 9:
                return "Joke's";
            case 10:
                return "Politics";
            case 11:
                return "Man Ki Baat";

        }
        return super.getPageTitle(position);
    }


}