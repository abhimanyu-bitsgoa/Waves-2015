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
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH =4000;
    public boolean connChecker=false;
    SharedPreferences sharedPref;
    String defaultValue;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);


        //Check for the first time start!
        sharedPref = getSharedPreferences("Counter", Context.MODE_PRIVATE);
        defaultValue = sharedPref.getString("Key", "");

        if(defaultValue!=null&&defaultValue.equals(""))
        new splashConnection().execute("", "", "");


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(connChecker==true){

                    //Code for first time usage

                     sharedPref = getSharedPreferences("Counter", Context.MODE_PRIVATE);
                     defaultValue = sharedPref.getString("Key", "");
                    if(defaultValue!=null&&defaultValue.equals("")){
                        Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Key", "hi");
                    editor.commit();}

                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();}
                }
        }, SPLASH_DISPLAY_LENGTH);
    }


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
                URL url = new URL("http://www.facebook.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(4000);
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
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



}

