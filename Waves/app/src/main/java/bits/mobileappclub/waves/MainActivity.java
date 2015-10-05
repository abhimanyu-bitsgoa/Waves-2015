package bits.mobileappclub.waves;

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
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(0));
        mRecyclerView = (RecyclerView) findViewById(R.id.music_scroll);
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
        //Drama Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.drama_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Literary Horizontal Scroll
        mAdapter =  new EventCategoryRecyclerViewAdapter(getDataSet(3));
        mRecyclerView = (RecyclerView) findViewById(R.id.literary_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Quiz Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(4));
        mRecyclerView = (RecyclerView) findViewById(R.id.quiz_scroll);

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
        //Film Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(6));
        mRecyclerView = (RecyclerView) findViewById(R.id.film_scroll);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Specials Horizontal Scroll
        mAdapter = new EventCategoryRecyclerViewAdapter(getDataSet(7));
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
            Intent intent=new Intent(this,MapActivity.class);
            this.startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private ArrayList<EventDataObjectCardMainActivity> getDataSet(int id) {
        ArrayList results = new ArrayList<EventDataObjectCardMainActivity>();
        switch (id)
        {
            case 0: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Music" + index,R.drawable.music1);
                results.add(index, obj);
            }
                break;
            case 1: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Dance" + index,R.drawable.dance1);
                results.add(index, obj);
            }
                break;
            case 2: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Drama" + index,R.drawable.drama1);
                results.add(index, obj);
            }
                break;
            case 3: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Literary" + index,R.drawable.literary1);
                results.add(index, obj);
            }
                break;
            case 4: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Quiz" + index,R.drawable.quiz1);
                results.add(index, obj);
            }
                break;
            case 5: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Fine Arts" + index,R.drawable.finarts1);
                results.add(index, obj);
            }
                break;
            case 6: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Film" + index,R.drawable.film1);
                results.add(index, obj);
            }
                break;
            case 7: for (int index = 0; index < 5; index++) {
                EventDataObjectCardMainActivity obj = new EventDataObjectCardMainActivity("Specials" + index,R.drawable.specials1);
                results.add(index, obj);
            }
                break;


        }

        return results;
    }
   public void fabClicked(View v){
       Intent intent=new Intent(MainActivity.this,Timeline.class);
        this.startActivity(intent);
    }


}
