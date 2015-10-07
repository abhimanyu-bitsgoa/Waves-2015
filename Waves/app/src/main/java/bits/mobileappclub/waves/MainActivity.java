package bits.mobileappclub.waves;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView eventImageOVerlay;
    private static String LOG_TAG = "CardViewActivity";
    private ViewPager liveViewPager;
    private float initialX;
    private int currentPage;
    private Handler handler;
    private  Toolbar toolbar;
    Runnable Update;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] eventTime = {"8:00 pm", "6:45 pm", "7:10 pm", "6:00 pm", "8:00 pm", "6:30 pm"};
        String[] eventName = {"Dhinchak", "Searock", "TimeLapse", "Rangmanch", "Waves Open Quiz", "Indian Rock"};
        String[] eventStage = {"Final", "Prelims", "Semi-Final", "Semi-final", "Final", "Prelims"};
        int[] imageResourceId = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6};
        liveViewPager=(ViewPager)findViewById(R.id.liveViewPager);
        liveViewPager.setAdapter(new LiveViewPagerAdapter(getApplicationContext(), eventName, eventTime, eventStage, imageResourceId));


        //Music Horizontal Scroll

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Waves");
      //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.ColorPrimary));
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


//Auto Swiping of Live Preview
         handler = new Handler();
        Timer swipeTimer;
        Update = new Runnable() {
            public void run() {
                if (currentPage == 6- 1) {
                    currentPage = 0;
                }
                if(collapsingToolbarLayout.getHeight()!=toolbar.getHeight())
                {liveViewPager.setCurrentItem(currentPage++, true);}
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 700, 5000);


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
        if (id == R.id.action_map) {
            Intent intent=new Intent(MainActivity.this,MapActivity.class);

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
                results.add(0, new EventDataObjectCardMainActivity("Fash P",R.drawable.fashptt));
                results.add(1, new EventDataObjectCardMainActivity("Natyanjali",R.drawable.natyanjalitt));
                results.add(2, new EventDataObjectCardMainActivity("Mr and Ms Waves",R.drawable.mrmswavestt));
                break;
            case 1:
                results.add(0, new EventDataObjectCardMainActivity("Sizzle",R.drawable.sizzlett));
                results.add(1, new EventDataObjectCardMainActivity("Dhinchak",R.drawable.dhinchaktt));
                results.add(2, new EventDataObjectCardMainActivity("Jumele",R.drawable.jumelett));
                break;
            case 2:
                results.add(0, new EventDataObjectCardMainActivity("Indian Rock",R.drawable.indinarocktt));
                results.add(1, new EventDataObjectCardMainActivity("Silence of the Amps",R.drawable.silenceoftheampstt));
                results.add(2, new EventDataObjectCardMainActivity("The Jukebox",R.drawable.jukeboxtt));
                results.add(3, new EventDataObjectCardMainActivity("Solonote",R.drawable.solonotett));
                results.add(4, new EventDataObjectCardMainActivity("Alaap",R.drawable.alaaptt));
                break;
            case 3:
                results.add(0, new EventDataObjectCardMainActivity("Rangmanch",R.drawable.rangmanchtt));
                results.add(1, new EventDataObjectCardMainActivity("Nukkad Natak",R.drawable.nukkadnataktt));
                results.add(2, new EventDataObjectCardMainActivity("Skime",R.drawable.skimett));
                break;
            case 4:
                results.add(0, new EventDataObjectCardMainActivity("Montage",R.drawable.dance1));
                results.add(1, new EventDataObjectCardMainActivity("Mezzotint",R.drawable.dance1));
                results.add(2, new EventDataObjectCardMainActivity("Reverse Flash",R.drawable.dance1));
                results.add(3, new EventDataObjectCardMainActivity("Time Lapse",R.drawable.dance1));
                break;
            case 5:
                results.add(0, new EventDataObjectCardMainActivity("Portraiture",R.drawable.dance1));
                results.add(1, new EventDataObjectCardMainActivity("Panaroma",R.drawable.dance1));
                results.add(2, new EventDataObjectCardMainActivity("Artathon",R.drawable.dance1));
                results.add(3, new EventDataObjectCardMainActivity("Shutter Island",R.drawable.dance1));


                break;
            case 6:
                results.add(0, new EventDataObjectCardMainActivity("Just a Minute",R.drawable.dance1));
                results.add(1, new EventDataObjectCardMainActivity("Waves Poetry Slam",R.drawable.dance1));
                results.add(2, new EventDataObjectCardMainActivity("Word Games",R.drawable.dance1));

                break;
            case 7:
                results.add(0, new EventDataObjectCardMainActivity("Waves Open Quiz",R.drawable.dance1));
                results.add(1, new EventDataObjectCardMainActivity("Entertainment Quiz",R.drawable.dance1));
                results.add(2, new EventDataObjectCardMainActivity("The Vices Quiz",R.drawable.dance1));
                results.add(3, new EventDataObjectCardMainActivity("The Lonewolf",R.drawable.dance1));
                break;

            case 8:
                results.add(0, new EventDataObjectCardMainActivity("Searock",R.drawable.searocktt));
                results.add(1, new EventDataObjectCardMainActivity("Lex Omnia",R.drawable.lexomniatt));
                results.add(2, new EventDataObjectCardMainActivity("Contention",R.drawable.contentiontt));
                results.add(3, new EventDataObjectCardMainActivity("Wallstreet Fete",R.drawable.wallstreetfetett));
                results.add(4, new EventDataObjectCardMainActivity("Show me the Funny",R.drawable.dance1));
                results.add(5, new EventDataObjectCardMainActivity("Ratatouille",R.drawable.dance1));
                results.add(6, new EventDataObjectCardMainActivity("Rubik's Challenge",R.drawable.dance1));

                break;
        }

        return results;
    }
   public void fabClicked(View v){
       Intent intent=new Intent(MainActivity.this,Timeline.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }




}
