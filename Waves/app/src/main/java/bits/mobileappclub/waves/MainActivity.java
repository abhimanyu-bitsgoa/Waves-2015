package bits.mobileappclub.waves;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements AppBarLayout.OnOffsetChangedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView eventImageOVerlay;
    private static String LOG_TAG = "CardViewActivity";
    private ViewPager liveViewPager;
    private float initialX;
    private int currentPage;
    private Handler handler;

    Runnable Update;
    CollapsingToolbarLayout collapsingToolbarLayout;
    boolean autoChange=true;
    Toolbar toolbar;
    Timer swipeTimer;
    //Parse Variables
    List<ParseObject> pObj0;
    ParseQuery<ParseObject> query0;
    Date d1;
    //Declaring all the string arrays of live adapter
    String [] eventTime;
    String[] eventName;
    String[] eventStage;
    int[] imageResourceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // int[] imageResourceId = { R.drawable.waves,R.drawable.salim, R.drawable.tvf};
        imageResourceId=new int[10];




        //Music Horizontal Scroll

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Waves");

      //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.ColorPrimary));
        //Pro Nights Scroll horizontal
        mAdapter = new EventCategoryBigRecyclerViewAdapter(getDataSet(9));
        mRecyclerView = (RecyclerView) findViewById(R.id.nights_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Big 3 scroll horizontal

        mAdapter = new EventCategoryBigRecyclerViewAdapter(getDataSet(0));
        mRecyclerView = (RecyclerView) findViewById(R.id.big3_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Dance Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(1));
        mRecyclerView = (RecyclerView) findViewById(R.id.dance_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Music horizontal scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.music_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Drama Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(3));
        mRecyclerView = (RecyclerView) findViewById(R.id.drama_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Film Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(4));
        mRecyclerView = (RecyclerView) findViewById(R.id.film_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Fine Arts Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(5));
        mRecyclerView = (RecyclerView) findViewById(R.id.finearts_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Literary Horizontal Scroll
        mAdapter =  new EventCategoryRecyclerViewAdapter(getDataSet(6));
        mRecyclerView = (RecyclerView) findViewById(R.id.literary_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Quiz Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(7));
        mRecyclerView = (RecyclerView) findViewById(R.id.quiz_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        //Specials Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(8));
        mRecyclerView = (RecyclerView) findViewById(R.id.specials_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(this);



            //Auto Swiping of Live Preview
            handler = new Handler();

            Update = new Runnable() {
                public void run() {
                    Log.d("Size",""+pObj0.size());
                    if (currentPage == pObj0.size() - 1) {
                        currentPage = 0;
                    }
                    else{

                        liveViewPager.setCurrentItem(currentPage++, true);}


                }
            };

            swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 700, 5000);
            //opened

        d1=new Date();
        //Get current system time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            /*System.out.println( sdf.format(cal.getTime()) );*/
        String sTime= sdf.format(cal.getTime());

        //Now live pager selecting date and calling respective day
        if(d1.getDate()==5){
        //Queries
            query0 = ParseQuery.getQuery("Day0");
            query0.fromLocalDatastore();
            query0.orderByAscending("time");
            query0.setLimit(10);
            query0.whereGreaterThanOrEqualTo("time", sTime);
            //Now you have results 10 in a sorted order more having time greater than this

        }

       else  if(d1.getDate()==6){
            //Queries
            query0 = ParseQuery.getQuery("Day1");
            query0.fromLocalDatastore();
            query0.orderByAscending("time");
            query0.setLimit(10);
            query0.whereGreaterThanOrEqualTo("time", sTime);
            //Now you have results 10 in a sorted order more having time greater than this

        }

       else  if(d1.getDate()==7){
            //Queries
            query0 = ParseQuery.getQuery("Day2");
            query0.fromLocalDatastore();
            query0.orderByAscending("time");
            query0.setLimit(10);
            query0.whereGreaterThanOrEqualTo("time", sTime);
            //Now you have results 10 in a sorted order more having time greater than this

        }

       else if(d1.getDate()==8){
            //Queries
            query0 = ParseQuery.getQuery("Day3");
            query0.fromLocalDatastore();
            query0.orderByAscending("time");
            query0.setLimit(10);
            query0.whereGreaterThanOrEqualTo("time", sTime);
            //Now you have results 10 in a sorted order more having time greater than this

        }

        else{
            //Queries
          for(int i=0;i<10;i++){
              eventName[i]="";
              eventStage[i]="";
              eventTime[i]="";


          }

            imageResourceId[0] = R.drawable.waves;
            imageResourceId[1] = R.drawable.salim;
            imageResourceId[2] = R.drawable.tvf;
            imageResourceId[3] = R.drawable.aking;


        }

        // Passing all the results to an Object List
        try {
            pObj0 = query0.find();
            int size=pObj0.size();
            eventTime = new String[size];
            eventName = new String[size];
            eventStage = new String[size];
        }catch(Exception e){}
        //Now lets fill the arrays with the required results
        try{
        Iterator<ParseObject> litr=pObj0.iterator();
        int i=0;
        while(litr.hasNext()) {

            ParseObject p = litr.next();
            eventName[i] = p.getString("title");
            eventTime[i] = p.getString("time");
            eventStage[i] = p.getString("venue");
            imageResourceId[i] = getThumbnail(eventName[i]);
        }
        }catch(Exception e){}
        liveViewPager=(ViewPager)findViewById(R.id.liveViewPager);
        liveViewPager.setAdapter(new LiveViewPagerAdapter(getApplicationContext(), eventName, eventTime, eventStage, imageResourceId));

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id== R.id.action_about) {
            Intent intent = new Intent(MainActivity.this,AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            return  true;

        }
        if (id == R.id.action_map) {
            Intent intent=new Intent(MainActivity.this,MapActivity.class);

            startActivity(intent);
            return true;
        }
        if (id == R.id.action_gallery) {
            Intent intent=new Intent(MainActivity.this,GalleryActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ArrayList<EventDataObjectCardMainActivity> getDataSet(int id) {
        ArrayList results = new ArrayList<EventDataObjectCardMainActivity>();
        switch (id)
        {
            case 0:
                results.add(0, new EventDataObjectCardMainActivity("Fash-P",R.drawable.fashptt));
                results.add(1, new EventDataObjectCardMainActivity("Natyanjali",R.drawable.natyanjalitt));
                results.add(2, new EventDataObjectCardMainActivity("Mr and Ms Waves",R.drawable.mrmswavestt));
                break;
            case 1:
                results.add(0, new EventDataObjectCardMainActivity("Sizzle",R.drawable.sizzlett));
                results.add(1, new EventDataObjectCardMainActivity("Dhinchak",R.drawable.dhinchaktt));
                results.add(2, new EventDataObjectCardMainActivity("Jumelè",R.drawable.jumelett));
                break;
            case 2:
                results.add(0, new EventDataObjectCardMainActivity("Indian Rock",R.drawable.indinarocktt));
                results.add(1, new EventDataObjectCardMainActivity("Silence Of The Amps",R.drawable.silenceoftheampstt));
                results.add(2, new EventDataObjectCardMainActivity("Juke Box",R.drawable.jukeboxtt));
                results.add(3, new EventDataObjectCardMainActivity("Solonote",R.drawable.solonotett));
                results.add(4, new EventDataObjectCardMainActivity("Alaap",R.drawable.alaaptt));
                break;
            case 3:
                results.add(0, new EventDataObjectCardMainActivity("Rangmanch",R.drawable.rangmanchtt));
                results.add(1, new EventDataObjectCardMainActivity("Nukkad Natak",R.drawable.nukkadnataktt));
                results.add(2, new EventDataObjectCardMainActivity("Skime",R.drawable.skimett));
                break;
            case 4:
                results.add(0, new EventDataObjectCardMainActivity("Montage",R.drawable.montagett));
                results.add(1, new EventDataObjectCardMainActivity("Mezzotint",R.drawable.mezzotint));
                results.add(2, new EventDataObjectCardMainActivity("Reverse Flash",R.drawable.reverseflashtt));
                results.add(3, new EventDataObjectCardMainActivity("Time Lapse",R.drawable.timelapsett));
                break;
            case 5:
                results.add(0, new EventDataObjectCardMainActivity("Portraiture",R.drawable.portraiturett));
                results.add(1, new EventDataObjectCardMainActivity("Panorama",R.drawable.panaromatt));
                results.add(2, new EventDataObjectCardMainActivity("Artathon",R.drawable.artathontt));
                results.add(3, new EventDataObjectCardMainActivity("Shutter Island",R.drawable.shuttertt));


                break;
            case 6:
                results.add(0, new EventDataObjectCardMainActivity("JAM",R.drawable.justaminutett));
                results.add(1, new EventDataObjectCardMainActivity("Waves Poetry Slam",R.drawable.poetryslamtt));
                results.add(2, new EventDataObjectCardMainActivity("Word Games",R.drawable.wordgamestt));

                break;
            case 7:
                results.add(0, new EventDataObjectCardMainActivity("Waves Open Quiz",R.drawable.wavesopentt));
                results.add(1, new EventDataObjectCardMainActivity("Entertainment Quiz",R.drawable.entertainmenttt));
                results.add(2, new EventDataObjectCardMainActivity("The Vices Quiz",R.drawable.vicequiztt));
                results.add(3, new EventDataObjectCardMainActivity("The Lonewolf",R.drawable.lonewolftt));
                break;

            case 8:
                results.add(0, new EventDataObjectCardMainActivity("Searock",R.drawable.searocktt));
                results.add(1, new EventDataObjectCardMainActivity("Lex Omnia",R.drawable.lexomniatt));
                results.add(2, new EventDataObjectCardMainActivity("Contention",R.drawable.contentiontt));
                results.add(3, new EventDataObjectCardMainActivity("Wallstreet Fête",R.drawable.wallstreetfetett));
                results.add(4, new EventDataObjectCardMainActivity("Show Me The Funny",R.drawable.showmethefunnytt));
                results.add(5, new EventDataObjectCardMainActivity("Ratatouille",R.drawable.ratattouillett));
                results.add(6, new EventDataObjectCardMainActivity("Rubik's Challenge",R.drawable.rubikstt));
                results.add(7, new EventDataObjectCardMainActivity("Mocktalk Show",R.drawable.mocktalktt));
                break;
            case 9:
                results.add(0, new EventDataObjectCardMainActivity("DJ Paroma",R.drawable.djparomatt));
                results.add(1, new EventDataObjectCardMainActivity("Lost Stories",R.drawable.loststoriestt));
                results.add(2, new EventDataObjectCardMainActivity("aKING",R.drawable.akingtt));
                results.add(3, new EventDataObjectCardMainActivity("TVF",R.drawable.tvf1tt));
                results.add(4, new EventDataObjectCardMainActivity("Salim-Sulaiman",R.drawable.salimtt));

                break;
        }

        return results;
    }
   public void fabClicked(View v){
       /*final View.OnClickListener clickListener = new View.OnClickListener() {
           public void onClick(View v) {

           }
       };*/

       Intent intent=new Intent(this,Timeline.class);
       startActivity(intent);

       final View coordinatorLayoutView = findViewById(R.id.snackbarPosition);

       /*Snackbar
               .make(coordinatorLayoutView,"Coming Soon", Snackbar.LENGTH_LONG)
               .setAction("OK", clickListener)
               .show();*/
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (offset == 0)
        {

        }
        else{}


           /* Update = new Runnable() {
                public void run() {
                    if (currentPage == 5 - 1) {
                        currentPage = 0;
                    }

                    liveViewPager.setCurrentItem(currentPage++, true);

                    collapsingToolbarLayout.setTitle("Wavepoopos");

                }
            };

            swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 700, 5000);}
            //opened
*/
            // Not collapsed


    }

    //Method to select image in liveView

    public int getThumbnail(String eventName){
        switch (eventName){
            case "Alaap" : return (R.drawable.alaap);


            case "Artathon" : return (R.drawable.artathon);


            case "Contention" : return (R.drawable.contention);


            case "Dhinchak" : return (R.drawable.dhinchak);



            case "Fash-P" : return (R.drawable.fashp);




            case "Indian Rock" : return (R.drawable.indinarock);



            case "JAM" : return (R.drawable.justaminute);



            case "Juke Box" : return (R.drawable.jukebox);



            case "Jumelè" : return (R.drawable.jumele);



            case "Lex Omnia" : return (R.drawable.lexomnia);



            case "Mezzotint" : return (R.drawable.mezzotint);



            case "Montage" : return (R.drawable.montagett);



            case "Mr and Ms Waves" : return (R.drawable.mrmswaves);



            case "Natyanjali" : return (R.drawable.natyanjali);



            case "Nukkad Natak" : return (R.drawable.nukkadnatak);



            case "Panorama" : return (R.drawable.panaroma);



            case "Portraiture" : return (R.drawable.portraiture);



            case "Rangmanch" : return (R.drawable.rangmanch);



            case "Ratatouille" : return (R.drawable.ratattouille);



            case "Reverse Flash" : return (R.drawable.reverseflash);



            case "Searock" : return (R.drawable.searock);



            case "Show Me The Funny" : return (R.drawable.showmethefunny);



            case "Shutter Island" : return (R.drawable.shutter);



            case "Silence Of The Amps" : return (R.drawable.silenceoftheamps);



            case "Sizzle" : return (R.drawable.sizzle);



            case "Skime" : return (R.drawable.skime);



            case "Solonote" : return (R.drawable.solonote);



            case "Entertainment Quiz" : return (R.drawable.entertainment);



            case "The Lonewolf" : return (R.drawable.lonewolf);



            case "The Vices Quiz" : return (R.drawable.vicequiz);



            case "Rubik's Challenge" : return (R.drawable.rubiks);



            case "Time Lapse" : return (R.drawable.timelapse);



            case "Wallstreet Fête" : return (R.drawable.wallstreetfete);



            case "Waves Open Quiz" : return (R.drawable.wavesopen);



            case "Waves Poetry Slam" : return (R.drawable.poetryslam);



            case "Word Games" : return (R.drawable.wordgames);

            case "Mocktalk Show" : return (R.drawable.mocktalk);


            default: return (R.drawable.waves);

        }}

}
