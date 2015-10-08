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
import com.parse.SaveCallback;

import java.lang.reflect.Array;
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

        new Thread(new ParseThread()).start();





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

    public class ParseThread implements Runnable{


        @Override
        public void run() {


                ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                try {
                    List<ParseObject> parObjList=query.find();
                    ParseObject.pinAllInBackground(parObjList);


                    for(int i=0;i<36;i++) {



                        System.out.println(parObjList.get(i).get("title"));

                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
               /* try {
                    ParseObject p0=query.getFirst();
                    p0.pinInBackground();
                    System.out.println(p0.getString("title").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/



        }
    }


    }



