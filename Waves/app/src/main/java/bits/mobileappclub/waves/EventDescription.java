package bits.mobileappclub.waves;

import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class EventDescription extends AppCompatActivity {
    ImageView headerEventImageView;
    TextView eventDescTextView;
    String eventName;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private int originalHeight = 0;
    private boolean isViewExpanded = false;
    private CardView cardView;
    private RecyclerView eventDescRecyclerView;
    private EventDescriptionRecyclerViewAdapter descriptionRecyclerViewAdapter;
    private TextView elimVenue;
    private TextView elimDay;
    private TextView elimTime;
    private TextView finalVenue;
    private TextView finalDay;
    private TextView finalTime;
    private TextView semiVenue;
    private TextView semiDay;
    private TextView semiTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        Intent intent = getIntent();
        eventName = intent.getStringExtra("eventName");
        eventDescRecyclerView= (RecyclerView)findViewById(R.id.eventDescRecyclerView);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayoutManager llmDescView = new LinearLayoutManager(getApplicationContext());
        eventDescRecyclerView.setLayoutManager(llmDescView);
        String[] eventTimeArray={"9:30 am","10:15 pm","5:30 pm"};
        String[] eventDayArray={"Day 0","Day 1","Day 2"};
        String[] eventVenueArray={"CC","Library Lawns","Auditorium"};
        Event event=new Event("Dhinchak",eventTimeArray,eventDayArray,eventVenueArray,"soighosidhgoiahdfoaihsfiahsgadf","adgadfhSOFAS AFADFAEGGAFDADSS","","");
        EventDetails eventDetails=new EventDetails(event);
        descriptionRecyclerViewAdapter=new EventDescriptionRecyclerViewAdapter(eventDetails);
        eventDescRecyclerView.setAdapter(descriptionRecyclerViewAdapter);

        headerEventImageView=(ImageView)findViewById(R.id.eventDescImageView);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_desc);
        collapsingToolbarLayout.setTitle(event.getEventName());
        //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.ColorPrimary));
        headerEventImageView.setImageResource(R.drawable.img1);
        elimVenue=(TextView)findViewById(R.id.elimVenue);
        elimDay=(TextView)findViewById(R.id.elimDay);
        elimTime=(TextView)findViewById(R.id.elimTime);
        semiVenue=(TextView)findViewById(R.id.semiVenue);
        semiDay=(TextView)findViewById(R.id.semiDay);
        semiTime=(TextView)findViewById(R.id.semiTime);
        finalVenue=(TextView)findViewById(R.id.finalVenue);
        finalDay=(TextView)findViewById(R.id.finalDay);
        finalTime=(TextView)findViewById(R.id.finalTime);

     elimVenue.setText(event.getEventVenueArray()[0]);
        elimDay.setText(event.getEventDayArray()[0]);
        elimTime.setText(event.getEventTimeArray()[0]);
        semiVenue.setText(event.getEventVenueArray()[1]);
        semiDay.setText(event.getEventDayArray()[1]);
        semiTime.setText(event.getEventTimeArray()[1]);
        finalVenue.setText(event.getEventVenueArray()[2]);
        finalDay.setText(event.getEventDayArray()[2]);
        finalTime.setText(event.getEventTimeArray()[2]);

    }
    @Override
    protected void onResume() {
        super.onResume();
        ((EventDescriptionRecyclerViewAdapter) descriptionRecyclerViewAdapter).setOnItemClickListener(new EventDescriptionRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {


                Log.i("Clicked on Desc", " Clicked on Item " + position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_description, menu);
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
        if (id== android.R.id.home) {
            onBackPressed();
        }
        if (id== R.id.action_map) {

        }


        return super.onOptionsItemSelected(item);
    }
    public  void fabClickedDescription(View v)
    {


    }



}
