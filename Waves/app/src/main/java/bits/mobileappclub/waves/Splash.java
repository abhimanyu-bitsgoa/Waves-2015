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

import com.parse.FindCallback;
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
    private final int SPLASH_DISPLAY_LENGTH = 4000;
    public boolean connChecker = false;
    SharedPreferences sharedPref;
    String defaultValue;
    private static final String mWalledGardenUrl = "http://clients3.google.com/generate_204";
    private static final int WALLED_GARDEN_SOCKET_TIMEOUT_MS = 5000;


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
                SplashConnection splashConnection = new SplashConnection();
                try {
                    connChecker = splashConnection.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    connChecker = false;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    connChecker = false;
                }

                if (connChecker == true) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
                    ParseQuery<ParseObject> query0 = ParseQuery.getQuery("Day0");
                    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Day1");
                    ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Day2");
                    ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Day3");

                    try {
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                                ParseObject.pinAllInBackground(parseObjects);
                            }
                        });

                        query0.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects0, com.parse.ParseException e) {
                                ParseObject.pinAllInBackground(parseObjects0);
                            }
                        });

                        query1.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects1, com.parse.ParseException e) {
                                ParseObject.pinAllInBackground(parseObjects1);
                            }
                        });

                        query2.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects2, com.parse.ParseException e) {
                                ParseObject.pinAllInBackground(parseObjects2);
                            }
                        });

                        query3.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects3, com.parse.ParseException e) {
                                ParseObject.pinAllInBackground(parseObjects3);
                            }
                        });
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
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    private class SplashConnection extends AsyncTask<Void, String, Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {
            connChecker = isWalledGardenConnection();
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


