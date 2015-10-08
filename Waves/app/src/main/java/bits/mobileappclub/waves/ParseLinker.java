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

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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




        // Enable Local Datastore.


        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "GdQa47PfR8nI3QjzjGoUOMm275Nhjt8gnJld1ovQ", "LmmZv4nwqFK6vhf5hx7OKkBw8yMofOdFNs3KM39I");

        new Thread(new ParseThread()).start();





    }

    public class ParseThread implements Runnable{


        @Override
        public void run() {

            if(isOnline()==true)
                System.out.println("!!!!!!!!!!!!!!!!!!!CONNECTED!!!!!!!!!!!!!!");
            else
                System.out.println("!!!!!!!!!!!!!!!!!!!NOT--CONNECTED!!!!!!!!!!!!!!");

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                ParseQuery<ParseObject> query0 = ParseQuery.getQuery("Day0");
                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Day1");
                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Day2");
                ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Day3");

                try {
                    List<ParseObject> parObjList=query.find();
                    List<ParseObject> parObjList0=query0.find();
                    List<ParseObject> parObjList1=query1.find();
                    List<ParseObject> parObjList2=query2.find();
                    List<ParseObject> parObjList3=query3.find();
                    ParseObject.pinAllInBackground(parObjList);
                    ParseObject.pinAllInBackground(parObjList0);
                    ParseObject.pinAllInBackground(parObjList1);
                    ParseObject.pinAllInBackground(parObjList2);
                    ParseObject.pinAllInBackground(parObjList3);

                    for(int i=0;i<36;i++) {

                        System.out.println(parObjList.get(i).get("title"));

                    }

                    System.out.println("All loading done");
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
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            try {
                URL url = new URL("http://www.google.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(3000);
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return new Boolean(true);
                }
            } catch (MalformedURLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }


}



