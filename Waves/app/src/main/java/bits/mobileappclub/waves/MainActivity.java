package bits.mobileappclub.waves;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;

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
        liveViewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), eventName, eventTime, eventStage, imageResourceId));


        //Music Horizontal Scroll

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Waves");
      //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#000000"));
        mAdapter = new MyRecyclerViewAdapter(getDataSet(0));
        mRecyclerView = (RecyclerView) findViewById(R.id.music_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Dance Horizontal Scroll
        mAdapter = new MyRecyclerViewAdapter(getDataSet(1));
        mRecyclerView = (RecyclerView) findViewById(R.id.dance_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Drama Horizontal Scroll
        mAdapter = new MyRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.drama_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Literary Horizontal Scroll
        mAdapter =  new MyRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.literary_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Quiz Horizontal Scroll
        mAdapter = new MyRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.quiz_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Fine Arts Horizontal Scroll
        mAdapter = new MyRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.finearts_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Film Horizontal Scroll
        mAdapter = new MyRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.film_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Specials Horizontal Scroll
        mAdapter = new MyRecyclerViewAdapter(getDataSet(2));
        mRecyclerView = (RecyclerView) findViewById(R.id.specials_scroll);
        mRecyclerView.setNestedScrollingEnabled(false);
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
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
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

        return super.onOptionsItemSelected(item);
    }
    private ArrayList<DataObject> getDataSet(int id) {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 5; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index, R.drawable.dance1);
            results.add(index, obj);
        }
        return results;
    }



}
