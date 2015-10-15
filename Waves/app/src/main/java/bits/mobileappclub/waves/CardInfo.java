package bits.mobileappclub.waves;

/**
 * Created by Aronzxxx on 03-10-2015.
 */
public class CardInfo {
    private  String mName;
    private String mStage;
    private int mThumbnail;
    private String mType;
    private String mTime;
    private String mVenue;

CardInfo(String mName,String mStage,String mType,String mTime,String mVenue,int mThumbnail){
        this.mName=mName;
        this.mStage=mStage;
        this.mThumbnail=mThumbnail;
        this.mType=mType;
        this.mTime=mTime;
        this.mVenue=mVenue;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getStage() {
        return mStage;
    }

    public void setStage(String stage) {
        this.mStage = stage;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }

    public String getVenue() {
        return mVenue;
    }

    public void setVenue(String venue) {
        this.mVenue = venue;
    }

}
