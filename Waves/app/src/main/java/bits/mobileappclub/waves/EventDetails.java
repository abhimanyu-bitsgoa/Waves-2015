package bits.mobileappclub.waves;

import java.util.ArrayList;

/**
 * Created by HP1 on 04-Oct-15.
 */
public class EventDetails {
    ArrayList<String> header=new ArrayList<String>();
    ArrayList<String> content=new ArrayList<String>();
    public  EventDetails(Event event)
    {
        header.add("About");
        content.add(event.getEventDescription());
       /* header.add("Rules");
        content.add(event.getRules());
        header.add("Elimination Round Details");
        content.add(event.getEventEliminationRoundDetails());
        header.add("Final Round Details");
        content.add(event.getEventFinalRoundDetails());*/

    }
    public int getCardViewCount()
    {
        return 1;
    }
}
