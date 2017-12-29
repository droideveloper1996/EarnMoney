package online.earnmoney.com.makemoney_earnonline;

/**
 * Created by Abhishek on 20/12/2017.
 */

public class PublisherClass {
    public String getAdhaarLink() {
        return AdhaarLink;
    }

    public void setAdhaarLink(String adhaarLink) {
        AdhaarLink = adhaarLink;
    }

    private String mPublisherName;
    private String mPublisherPhone;
    private String AdhaarLink;

    public PublisherClass(String mPublisherName, String mPublisherPhone,
                          String adhaarLink, String mPublisherEmail, String mPublisherAdhaarNo,
                          String mPublisherRevenue, String mPanCardNumber, String mPublisherRating) {
        this.mPublisherName = mPublisherName;
        this.mPublisherPhone = mPublisherPhone;
        AdhaarLink = adhaarLink;
        this.mPublisherEmail = mPublisherEmail;
        this.mPublisherAdhaarNo = mPublisherAdhaarNo;
        this.mPublisherRevenue = mPublisherRevenue;
        this.mPanCardNumber = mPanCardNumber;
        this.mPublisherRating = mPublisherRating;
    }

    private String mPublisherEmail;
    private String mPublisherAdhaarNo;
    private String mPublisherRevenue;
    private String mPanCardNumber;

    public String getmPanCardNumber() {
        return mPanCardNumber;
    }

    public void setmPanCardNumber(String mPanCardNumber) {
        this.mPanCardNumber = mPanCardNumber;
    }

    private String mPublisherRating;

    public String getmPublisherName() {
        return mPublisherName;
    }

    public void setmPublisherName(String mPublisherName) {
        this.mPublisherName = mPublisherName;
    }

    public String getmPublisherPhone() {
        return mPublisherPhone;
    }

    public void setmPublisherPhone(String mPublisherPhone) {
        this.mPublisherPhone = mPublisherPhone;
    }

    public String getmPublisherEmail() {
        return mPublisherEmail;
    }

    public void setmPublisherEmail(String mPublisherEmail) {
        this.mPublisherEmail = mPublisherEmail;
    }

    public String getmPublisherAdhaarNo() {
        return mPublisherAdhaarNo;
    }

    public void setmPublisherAdhaarNo(String mPublisherAdhaarNo) {
        this.mPublisherAdhaarNo = mPublisherAdhaarNo;
    }

    public String getmPublisherRevenue() {
        return mPublisherRevenue;
    }

    public void setmPublisherRevenue(String mPublisherRevenue) {
        this.mPublisherRevenue = mPublisherRevenue;
    }

    public String getmPublisherRating() {
        return mPublisherRating;
    }

    public void setmPublisherRating(String mPublisherRating) {
        this.mPublisherRating = mPublisherRating;
    }

//-----------------------------------------------------------------------------------------------
    /**
     * Publisher Information Table
     *
     *    -publisherId
     *        -  Name
     *        -  Email
     *        -  Phone
     *        -  Rating
     *        -  AdhaarNo.
     *        -  AdhaarLink.
     *        -  PanNo.
     */

    /**
     * -publisherData
     *           pushId
     *           mNewsHeading
     *           mNewsDescription
     *           mNewsUrl  ---->ContentUrl
     *           mNewsImageUrl1
     *           mNewsImageUrl2
     *           mNewsImageUrl3
     *           mNewsImageUrl4
     *           mNewsImageUrl5
     *           mNewsDate
     *           mNewsTime
     *           mNewsLocation
     *           mNewsLikes
     *           mNewsDislikes
     */

}
