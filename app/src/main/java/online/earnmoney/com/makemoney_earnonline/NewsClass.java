package online.earnmoney.com.makemoney_earnonline;

/**
 * Created by Abhishek on 08/12/2017.
 */

public class NewsClass {

    //News Detail
    private String mNewsId;
    private String mNewsHeading;
    private String mNewsUrl;
    private String mNewsShot;
    private String mNewsNetwork;
    private String mNewsDescription;

    //publisher Detail

    public String getmNewsId() {
        return mNewsId;
    }

    public void setmNewsId(String mNewsId) {
        this.mNewsId = mNewsId;
    }

    private String mNewsAuthor;
    private String mNewsDate;
    private String mNewsPublisher;
    private String mNewsLocation;
    private String mNewsPublishTime;

    //Image URls
    private String mNewsImageUrl1;
    private String mNewsImageUrl2;
    private String mNewsImageUrl3;

    public String getmNewsDislikes() {
        return mNewsDislikes;
    }

    public void setmNewsDislikes(String mNewsDislikes) {
        this.mNewsDislikes = mNewsDislikes;
    }

    private String mNewsImageUrl4;
    private String mNewsImageUrl5;

    //Unique Publisher Id
    private String mPublisherId;
    //Likes for Specific News
    private String mNewsLikes;
    private String mNewsDislikes;

    public String getmNewsLikes() {
        return mNewsLikes;
    }

    public void setmNewsLikes(String mNewsLikes) {
        this.mNewsLikes = mNewsLikes;
    }

    public String getmPublisherId() {
        return mPublisherId;
    }

    public void setmPublisherId(String mPublisherId) {
        this.mPublisherId = mPublisherId;
    }

    public String getmNewsAuthor() {
        return mNewsAuthor;
    }

    public void setmNewsAuthor(String mNewsAuthor) {
        this.mNewsAuthor = mNewsAuthor;
    }

    public String getmNewsDate() {
        return mNewsDate;
    }

    public void setmNewsDate(String mNewsDate) {
        this.mNewsDate = mNewsDate;
    }

    public String getmNewsPublisher() {
        return mNewsPublisher;
    }

    public void setmNewsPublisher(String mNewsPublisher) {
        this.mNewsPublisher = mNewsPublisher;
    }

    public String getmNewsLocation() {
        return mNewsLocation;
    }

    public void setmNewsLocation(String mNewsLocation) {
        this.mNewsLocation = mNewsLocation;
    }

    public String getmNewsPublishTime() {
        return mNewsPublishTime;
    }

    public void setmNewsPublishTime(String mNewsPublishTime) {
        this.mNewsPublishTime = mNewsPublishTime;
    }

    public String getmNewsImageUrl1() {
        return mNewsImageUrl1;
    }

    public void setmNewsImageUrl1(String mNewsImageUrl1) {
        this.mNewsImageUrl1 = mNewsImageUrl1;
    }

    public String getmNewsImageUrl2() {
        return mNewsImageUrl2;
    }

    public void setmNewsImageUrl2(String mNewsImageUrl2) {
        this.mNewsImageUrl2 = mNewsImageUrl2;
    }

    public String getmNewsImageUrl3() {
        return mNewsImageUrl3;
    }

    public void setmNewsImageUrl3(String mNewsImageUrl3) {
        this.mNewsImageUrl3 = mNewsImageUrl3;
    }

    public String getmNewsImageUrl4() {
        return mNewsImageUrl4;
    }

    public void setmNewsImageUrl4(String mNewsImageUrl4) {
        this.mNewsImageUrl4 = mNewsImageUrl4;
    }

    public String getmNewsImageUrl5() {
        return mNewsImageUrl5;
    }

    public void setmNewsImageUrl5(String mNewsImageUrl5) {
        this.mNewsImageUrl5 = mNewsImageUrl5;
    }

    public NewsClass() {

    }

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

    public NewsClass(String mNewsHeading, String mNewsUrl, String mNewsShot,
                     String mNewsNetwork, String mNewsDescription, String mNewsAuthor,
                     String mNewsDate, String mNewsPublisher, String mNewsLocation,
                     String mNewsPublishTime, String mNewsImageUrl1, String mNewsImageUrl2,
                     String mNewsImageUrl3, String mNewsImageUrl4, String mNewsImageUrl5) {
        this.mNewsHeading = mNewsHeading;
        this.mNewsUrl = mNewsUrl;
        this.mNewsShot = mNewsShot;
        this.mNewsNetwork = mNewsNetwork;
        this.mNewsDescription = mNewsDescription;
        this.mNewsAuthor = mNewsAuthor;
        this.mNewsDate = mNewsDate;
        this.mNewsPublisher = mNewsPublisher;
        this.mNewsLocation = mNewsLocation;
        this.mNewsPublishTime = mNewsPublishTime;
        this.mNewsImageUrl1 = mNewsImageUrl1;
        this.mNewsImageUrl2 = mNewsImageUrl2;
        this.mNewsImageUrl3 = mNewsImageUrl3;
        this.mNewsImageUrl4 = mNewsImageUrl4;
        this.mNewsImageUrl5 = mNewsImageUrl5;
    }
}
