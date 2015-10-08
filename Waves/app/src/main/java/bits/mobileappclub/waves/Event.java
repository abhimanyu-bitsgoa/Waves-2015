package bits.mobileappclub.waves;

/**
 * Created by HP1 on 04-Oct-15.
 */
public class Event {

    String eventName;
    String[] eventTime;
    String[] eventVenue;
    String[] eventDay;
    String eventRules;
    String eventDescription;
    String eventEliminationRoundDetails;
    String eventFinalRoundDetails;


    public Event(String name,String[] time,String[] day,String[] venue,String description,String elimdDetails,String finalDetails,String rules)

    {
        eventName=name;
        eventTime=time;
        eventDay=day;
        eventVenue=venue;
        eventRules=rules;
        eventDescription=description;
        eventEliminationRoundDetails=elimdDetails;
        eventFinalRoundDetails=finalDetails;

    }
    public String getEventName()
    {
        return eventName;
    }
    public String getEventDescription()
    {
        return eventDescription;
    }
    public void setEventName(String mText1) {
        this.eventName = mText1;
    }
    public void setEventDescription(String mText2) {
        this.eventDescription = mText2;
    }
    public int getEventDescriptionCardViewCount()
    {
        int result=2;
        if(eventEliminationRoundDetails!=null){
            result++;
        }
        if(eventFinalRoundDetails!=null){
            result++;
        }
        return result;
    }
    public String getRules()
    {
        return eventRules;
    }
    public String getEventEliminationRoundDetails()
    {
        return eventEliminationRoundDetails;
    }
    public String getEventFinalRoundDetails()
    {
        return eventFinalRoundDetails;
    }
    public String[] getEventVenueArray()
    {
        return eventVenue;
    }
    public String[] getEventTimeArray()
    {
        return eventTime;
    }
    public String[] getEventDayArray()
    {
        return eventDay;
    }

}
