package bits.mobileappclub.waves;

/**
 * Created by HP1 on 09-Oct-15.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Splash extends Activity {

    /**
     * Duration of wait *
     */
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    public boolean connChecker = false;
    SharedPreferences sharedPref;
    String defaultValue;
    private static final String mWalledGardenUrl = "http://clients3.google.com/generate_204";
    private static final int WALLED_GARDEN_SOCKET_TIMEOUT_MS = 10000;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);


        //Check for the first time start!
        sharedPref = getSharedPreferences("Counter", Context.MODE_PRIVATE);
        defaultValue = sharedPref.getString("Key", "");

        Log.d("yaha aa gya0","haa yaha aa gya");
        //if (defaultValue != null && defaultValue.equals(""))
            //new splashConnection().execute("", "", "");



        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    //if(connChecker==true){
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = cm.getActiveNetworkInfo();
                    connChecker = (netInfo != null && netInfo.isConnected());
                    SplashConnection splashConnection=new SplashConnection();
                    try {
                        connChecker=splashConnection.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        connChecker=false;
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        connChecker=false;
                    }

                    if (connChecker == true) {

                        Log.d("yaha aa gya3", "haa yaha aa gya");
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                        ParseQuery<ParseObject> query0 = ParseQuery.getQuery("Day0");
                        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Day1");
                        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Day2");
                        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Day3");

                        try {
                                List<ParseObject> parObjList = query.find();
                                List<ParseObject> parObjList0 = query0.find();
                                List<ParseObject> parObjList1 = query1.find();
                                List<ParseObject> parObjList2 = query2.find();
                                List<ParseObject> parObjList3 = query3.find();
                                if (parObjList.size() != 0) {
                                    ParseObject.pinAll(parObjList);
                                    ParseObject.pinAll(parObjList0);
                                    ParseObject.pinAll(parObjList1);
                                    ParseObject.pinAll(parObjList2);
                                    ParseObject.pinAll(parObjList3);
                                }
                                //Toast.makeText(getBaseContext(), "Connect to the internet and try again", Toast.LENGTH_LONG).show();
                                Log.d("yaha aa gya8", "haa yaha aa gya");
                            sharedPref = getSharedPreferences("Counter", Context.MODE_PRIVATE);
                            defaultValue = sharedPref.getString("Key", "");
                            if (defaultValue.equals("")) {
                                Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("Key", "hi");
                                editor.commit();
                            }

                            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                            Splash.this.startActivity(mainIntent);
                            Splash.this.finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                            sharedPref = getSharedPreferences("Counter", Context.MODE_PRIVATE);
                            defaultValue = sharedPref.getString("Key", "");
                            if (defaultValue.equals("")) {
                                Toast.makeText(getBaseContext(), "Connect to the internet and try again", Toast.LENGTH_LONG).show();
                            } else {
                                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                                Splash.this.startActivity(mainIntent);
                                Splash.this.finish();
                            }

                        }


                    } else {
                        Log.d("yaha aa gya4", "haa yaha aa gya");
                        sharedPref = getSharedPreferences("Counter", Context.MODE_PRIVATE);
                        defaultValue = sharedPref.getString("Key", "");
                        if (defaultValue.equals("")) {
                            Toast.makeText(getBaseContext(), "Connect to the internet and try again", Toast.LENGTH_LONG).show();
                        } else {

                            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                            Splash.this.startActivity(mainIntent);
                            Splash.this.finish();
                        }

                    }

                    //Code for first time usage
                }
                //}
            },SPLASH_DISPLAY_LENGTH);
    }

/*

    private class splashConnection extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... result) {



                if(isOnline()==true){
                    connChecker=true;
                    System.out.println("!!!!!!!!!!!!!!!!!!!CONNECTED!!!!!!!!!!!!!!");
                    return "y";}
                else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!NOT--CONNECTED from task!!!!!!!!!!!!!!");
                    connChecker=false;
                    return "n";

                }


        }


        protected void onProgressUpdate(String... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String  result) {

            if(connChecker==false) {

               System.out.println("!!!!!!!!!!!!!!!!!!!POST EXECUTE!!!!!!!!!!!!!!");
                Toast.makeText(getApplicationContext(), "Please connect to Internet and restart", Toast.LENGTH_LONG).show();


            }
            if(connChecker==true)
            {System.out.println("!!!!!!!!!!!!!!!!!!!POST EXECUTE!!!!!!!!!!!!!!");

            }
        }



    }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            try {
                URL url = new URL("http://in.yahoo.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(4000);
                urlc.connect();
                if (urlc.getResponseCode() == 204||urlc.getResponseCode() == 200) {
                    return true;
                }
                else{System.out.println("Not getting correct response code");}
            } catch (MalformedURLException e1) {
                System.out.println("Error 1");
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error 2");
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }
*/

    private class SplashConnection extends AsyncTask<Void, String, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {/*
            Boolean connectionChecker=false;
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            Log.d("yaha aa gya","haa yaha aa gya");
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                try {

                    if (Inet4Address.getByName("http://www.google.co.in/").isReachable(5000)) {
                        connectionChecker = true;
                        Log.d("yaha aa gya2","haa yaha aa gya");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("yaha aa gya6","haa yaha aa gya");
                    connectionChecker = false;
                }
            }
            return connectionChecker;*/
            //return (netInfo!=null && netInfo.isConnected());
            connChecker=isWalledGardenConnection();
            return connChecker;
        }

        protected void onProgressUpdate(String... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
        }

    }

    private boolean isWalledGardenConnection() {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(mWalledGardenUrl); // "http://clients3.google.com/generate_204"
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setConnectTimeout(WALLED_GARDEN_SOCKET_TIMEOUT_MS);
            urlConnection.setReadTimeout(WALLED_GARDEN_SOCKET_TIMEOUT_MS);
            urlConnection.setUseCaches(false);
            urlConnection.getInputStream();
            // We got a valid response, but not from the real google
            return urlConnection.getResponseCode() == 204;
        } catch (IOException e) {
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

}


