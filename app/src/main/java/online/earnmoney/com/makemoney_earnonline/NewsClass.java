package online.earnmoney.com.makemoney_earnonline;

/**
 * Created by Abhishek on 08/12/2017.
 */

public class NewsClass {

    private String mNewsHeading;
    private String mNewsUrl;
    private String mNewsShot;
    private String mNewsNetwork;
    private String mNewsDescription;
public NewsClass(){}
    public NewsClass(String mNewsHeading, String mNewsUrl, String mNewsShot, String mNewsNetwork, String mNewsDescription) {
        this.mNewsHeading = mNewsHeading;
        this.mNewsUrl = mNewsUrl;
        this.mNewsShot = mNewsShot;
        this.mNewsNetwork = mNewsNetwork;
        this.mNewsDescription = mNewsDescription;
    }

    public NewsClass(String mNewsHeading, String mNewsUrl) {
        this.mNewsHeading = mNewsHeading;
        this.mNewsUrl = mNewsUrl;
        this.mNewsShot = mNewsShot;
        this.mNewsNetwork = mNewsNetwork;
        this.mNewsDescription = mNewsDescription;
    }

    public String getmNewsHeading() {
        return mNewsHeading;
    }

    public void setmNewsHeading(String mNewsHeading) {
        this.mNewsHeading = mNewsHeading;
    }

    public String getmNewsUrl() {
        return mNewsUrl;
    }

    public void setmNewsUrl(String mNewsUrl) {
        this.mNewsUrl = mNewsUrl;
    }

    public String getmNewsShot() {
        return mNewsShot;
    }

    public void setmNewsShot(String mNewsShot) {
        this.mNewsShot = mNewsShot;
    }

    public String getmNewsNetwork() {
        return mNewsNetwork;
    }

    public void setmNewsNetwork(String mNewsNetwork) {
        this.mNewsNetwork = mNewsNetwork;
    }

    public String getmNewsDescription() {
        return mNewsDescription;
    }

    public void setmNewsDescription(String mNewsDescription) {
        this.mNewsDescription = mNewsDescription;
    }
}
