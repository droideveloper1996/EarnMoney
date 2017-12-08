package online.earnmoney.com.makemoney_earnonline;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by Abhishek on 23/11/2017.
 */

public class RewardedAdManager implements RewardedVideoAdListener {
    Context mCtx;
    TextView textView;

    public RewardedAdManager(Context ctx, View view) {
        this.mCtx = ctx;
        textView = (TextView) view;
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Toast.makeText(mCtx, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
                rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
        int reward = rewardItem.getAmount();
        textView.setText(Integer.toString(reward));
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
