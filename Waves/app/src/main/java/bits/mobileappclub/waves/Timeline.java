package bits.mobileappclub.waves;




/**
 * Created by Aronzxxx on 03-10-2015.
 */
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Timeline extends ActionBarActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter_Tabs adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"5 NOV","6 NOV","7 NOV","8 NOV"};
    int Numboftabs =4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);



        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Creating The ViewPagerAdapter_Tabs and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter_Tabs(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id== android.R.id.home) {
            onBackPressed();
            return  true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id== R.id.action_map) {
            Intent intent = new Intent(Timeline.this,MapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            return  true;

        }
        if (id== R.id.action_about) {
            Intent intent = new Intent(Timeline.this,AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            return  true;

        }
        if (id == R.id.action_gallery) {
            Intent intent=new Intent(Timeline.this,GalleryActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}