package bits.mobileappclub.waves;

/**
 * Created by HP1 on 03-Oct-15.
 */

public class DataObject {
    private String eventName;
    private int imageId;

    DataObject (String text1, int text2){
        eventName = text1;
        imageId= text2;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String mText1) {
        this.eventName = mText1;
    }

    public int getImageID() {
        return imageId;
    }

    public void setImageId(int mText2) {
        this.imageId = mText2;
    }
}