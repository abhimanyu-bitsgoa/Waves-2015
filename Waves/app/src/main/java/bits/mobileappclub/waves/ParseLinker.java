package bits.mobileappclub.waves;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;

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

    @Override
    public void onCreate() {
        super.onCreate();

        //Check Internet Connection


        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ) {

            // notify user you are online




            System.out.println("You are connected");

        }
        else if ( conMgr.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {

            // notify user you are not online

            System.out.println("You are  NOT connected");
        }


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



