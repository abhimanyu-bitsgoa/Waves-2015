package bits.mobileappclub.waves;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aronzxxx on 05-10-2015.
 */
public class ParseLinker extends Application {
    String tit;
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "GdQa47PfR8nI3QjzjGoUOMm275Nhjt8gnJld1ovQ", "LmmZv4nwqFK6vhf5hx7OKkBw8yMofOdFNs3KM39I");

        // Code to store all objects from query in database
        ParseQuery<ParseObject> parseQuery0 = ParseQuery.getQuery("Events").whereMatches("testCol","1");
        List<ParseObject> objects = null; // Online ParseQuery results
        try {
            objects = parseQuery0.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ParseObject.pinAllInBackground(objects);

       /* ParseObject  p0;

        try {

            p0 =parseQuery0.getFirst();
            p0.pinInBackground();

           tit= p0.getString("date").toString();
            System.out.println(tit);
            System.out.println(tit);System.out.println(tit);
            System.out.println(tit);System.out.println(tit);
            System.out.println(tit);System.out.println(tit);




        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }



    }



