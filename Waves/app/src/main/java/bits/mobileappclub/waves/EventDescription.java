package bits.mobileappclub.waves;

import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
    private ImageView elimIcon,semiIcon,finalIcon;
    String timeElim,timeSemi,timeFinal,venueElim,venueSemi,venueFinal,dateElim,dateSemi,dateFinal,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        Intent intent = getIntent();
        eventName = intent.getStringExtra("eventName");
        elimVenue = (TextView) findViewById(R.id.elimVenue);
        elimDay = (TextView) findViewById(R.id.elimDay);
        elimTime = (TextView) findViewById(R.id.elimTime);
        semiVenue = (TextView) findViewById(R.id.semiVenue);
        semiDay = (TextView) findViewById(R.id.semiDay);
        semiTime = (TextView) findViewById(R.id.semiTime);
        finalVenue = (TextView) findViewById(R.id.finalVenue);
        finalDay = (TextView) findViewById(R.id.finalDay);
        finalTime = (TextView) findViewById(R.id.finalTime);
        elimIcon=(ImageView)findViewById(R.id.elimIcon);
        semiIcon=(ImageView)findViewById(R.id.semiIcon);
        finalIcon=(ImageView)findViewById(R.id.finalIcon);
        if(eventName.equals("DJ Paroma")||eventName.equals("Salim-Sulaiman")||eventName.equals("TVF")||eventName.equals("aKING")||eventName.equals("Lost Stories"))
        {
            elimIcon.setVisibility(View.GONE);
            semiIcon.setVisibility(View.GONE);
            finalIcon.setVisibility(View.GONE);
            semiTime.setVisibility(View.GONE);
            finalTime.setVisibility(View.GONE);
            semiDay.setVisibility(View.GONE);
            finalDay.setVisibility(View.GONE);
            semiVenue.setVisibility(View.GONE);
            finalVenue.setVisibility(View.GONE);
        }

        eventDescRecyclerView= (RecyclerView)findViewById(R.id.eventDescRecyclerView);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayoutManager llmDescView = new LinearLayoutManager(getApplicationContext());
        eventDescRecyclerView.setLayoutManager(llmDescView);

        ParseObject pObj=null;

        //Fetching data from Parse database.

    try {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events").whereMatches("title", eventName);
        query.fromLocalDatastore();
        pObj = query.getFirst();
    } catch (ParseException e) {

        e.printStackTrace();
    }

    timeElim = pObj.get("timings").toString();
    timeSemi = pObj.get("timeSemi").toString();
    timeFinal = pObj.get("timeFinals").toString();
    venueElim = pObj.get("venue").toString();
    venueSemi = pObj.get("venueSemi").toString();
    venueFinal = pObj.get("venueFinals").toString();
    dateElim = pObj.get("date").toString();
        switch(dateElim)
        {
            case "DAY 0":dateElim="5 NOV";break;
            case "DAY 1":dateElim="6 NOV";break;
            case "DAY 2":dateElim="7 NOV";break;
            case "DAY 3":dateElim="8 NOV";break;
        }
    dateSemi = pObj.get("dateSemi").toString();
        switch(dateSemi)
        {
            case "DAY 0":dateSemi="5 NOV";break;
            case "DAY 1":dateSemi="6 NOV";break;
            case "DAY 2":dateSemi="7 NOV";break;
            case "DAY 3":dateSemi="8 NOV";break;
        }
    dateFinal = pObj.get("dateFinals").toString();
        switch(dateFinal)
        {
            case "DAY 0":dateFinal="5 NOV";break;
            case "DAY 1":dateFinal="6 NOV";break;
            case "DAY 2":dateFinal="7 NOV";break;
            case "DAY 3":dateFinal="8 NOV";break;
        }
    about = pObj.get("about").toString();


    String[] eventTimeArray = {timeElim, timeSemi, timeFinal};

    String[] eventDayArray = {dateElim, dateSemi, dateFinal};
    String[] eventVenueArray = {venueElim, venueSemi, venueFinal};
        for(int i=0;i<3;i++){
        if(eventVenueArray[i].equals("-")){
            eventVenueArray[i]="N/A";

        }}
    Event event = new Event(eventName, eventTimeArray, eventDayArray, eventVenueArray, about);
    EventDetails eventDetails = new EventDetails(event);
    descriptionRecyclerViewAdapter = new EventDescriptionRecyclerViewAdapter(eventDetails);
    eventDescRecyclerView.setAdapter(descriptionRecyclerViewAdapter);

    headerEventImageView = (ImageView) findViewById(R.id.eventDescImageView);
    //headerEventImageView.setImageResource(R.drawable.searock);
    setHeaderImage(eventName);

    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_desc);
    collapsingToolbarLayout.setTitle(event.getEventName());
    //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.ColorPrimary));


    elimVenue.setText(event.getEventVenueArray()[0]);
    elimDay.setText(event.getEventDayArray()[0]);
    elimTime.setText(event.getEventTimeArray()[0]);
    semiVenue.setText(event.getEventVenueArray()[1]);
    semiDay.setText(event.getEventDayArray()[1]);
    semiTime.setText(event.getEventTimeArray()[1]);
    finalVenue.setText(event.getEventVenueArray()[2]);
    finalDay.setText(event.getEventDayArray()[2]);
    finalTime.setText(event.getEventTimeArray()[2]);


elimIcon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int newIntTimeElim;
        String newTimeElim = timeElim.replaceAll(":", "");
        try {
            newIntTimeElim = Integer.parseInt(newTimeElim);
        } catch (Exception e) {
            newIntTimeElim = 900;
        }
        int hour = newIntTimeElim / 100;
        int minutes = newIntTimeElim % 100;

        Calendar beginTime = Calendar.getInstance();


        Calendar endTime = Calendar.getInstance();
        try {
            beginTime.set(2015, 10,Integer.parseInt(dateElim.charAt(0) + ""), hour, minutes);
            endTime.set(2015, 10, Integer.parseInt(dateElim.charAt(0) + ""), hour + 1, minutes);
        } catch (Exception e) {
            beginTime.set(2015, 10, 5, hour, minutes);
            endTime.set(2015, 10, 5, hour + 1, minutes);
        }

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, eventName + "(Elims)")
                        //.putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, venueElim)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        startActivity(intent);

    }

});
        semiIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newIntTimeSemi;
                String newTimeSemi=timeSemi.replaceAll(":","");
                try{
                    newIntTimeSemi=Integer.parseInt(newTimeSemi);}
                catch(Exception e){newIntTimeSemi=900;}
                int hour=newIntTimeSemi/100;
                int minutes=newIntTimeSemi%100;

                Calendar beginTime = Calendar.getInstance();


                Calendar endTime = Calendar.getInstance();
                try{
                    beginTime.set(2015, 10, Integer.parseInt(dateSemi.charAt(0) + ""), hour, minutes);
                    endTime.set(2015, 10, Integer.parseInt(dateSemi.charAt(0)+""), hour+1, minutes);
                }
                catch(Exception e){
                    beginTime.set(2015, 10, 5, hour, minutes);
                    endTime.set(2015, 10, 5, hour+1, minutes);
                }

                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, eventName + "(Semis)")
                                //.putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, venueElim)
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

                startActivity(intent);

            }
        });
        finalIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newIntTimeFinal;
                String newTimeFinal=timeFinal.replaceAll(":","");
                try{
                    newIntTimeFinal=Integer.parseInt(newTimeFinal);}
                catch(Exception e){newIntTimeFinal=900;}
                int hour=newIntTimeFinal/100;
                int minutes=newIntTimeFinal%100;

                Calendar beginTime = Calendar.getInstance();


                Calendar endTime = Calendar.getInstance();
                try{
                    beginTime.set(2015, 10, Integer.parseInt(dateFinal.charAt(0) + ""), hour, minutes);
                    endTime.set(2015, 10, Integer.parseInt(dateFinal.charAt(0)+""), hour+1, minutes);
                }
                catch(Exception e){
                    beginTime.set(2015, 10, 5, hour, minutes);
                    endTime.set(2015, 10, 5, hour+1, minutes);
                    Log.d("s","Event time error");
                }

                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, eventName+"(Finals)")
                                //.putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, venueElim)
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

                startActivity(intent);

            }
        });

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

        if (id== android.R.id.home) {
            onBackPressed();
            return  true;
        }
        if (id== R.id.action_map) {
            Intent intent = new Intent(EventDescription.this,MapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            return  true;

        }
        if (id == R.id.action_gallery) {
            Intent intent=new Intent(EventDescription.this,GalleryActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setHeaderImage(String eventName){
        switch (eventName){
            case "Alaap" : headerEventImageView.setImageResource(R.drawable.alaap);
               break;

            case "Artathon" : headerEventImageView.setImageResource(R.drawable.artathon);
                break;

            case "Contention" : headerEventImageView.setImageResource(R.drawable.contention);
                break;

            case "Dhinchak" : headerEventImageView.setImageResource(R.drawable.dhinchak);
                break;


            case "Fash-P" : headerEventImageView.setImageResource(R.drawable.fashp);
                break;



            case "Indian Rock" : headerEventImageView.setImageResource(R.drawable.indinarock);
                break;


            case "JAM" : headerEventImageView.setImageResource(R.drawable.justaminute);
                break;


            case "Juke Box" : headerEventImageView.setImageResource(R.drawable.jukebox);
                break;


            case "Jumelè" : headerEventImageView.setImageResource(R.drawable.jumele);
                break;


            case "Lex Omnia" : headerEventImageView.setImageResource(R.drawable.lexomnia);
                break;


            case "Mezzotint" : headerEventImageView.setImageResource(R.drawable.mezzotint);
                break;


            case "Montage" : headerEventImageView.setImageResource(R.drawable.montagett);
                break;


            case "Mr and Ms Waves" : headerEventImageView.setImageResource(R.drawable.mrmswaves);
                break;


            case "Natyanjali" : headerEventImageView.setImageResource(R.drawable.natyanjali);
                break;


            case "Nukkad Natak" : headerEventImageView.setImageResource(R.drawable.nukkadnatak);
                break;


            case "Panorama" : headerEventImageView.setImageResource(R.drawable.panaroma);
                break;


            case "Portraiture" : headerEventImageView.setImageResource(R.drawable.portraiture);
                break;


            case "Rangmanch" : headerEventImageView.setImageResource(R.drawable.rangmanch);
                break;


            case "Ratatouille" : headerEventImageView.setImageResource(R.drawable.ratattouille);
                break;


            case "Reverse Flash" : headerEventImageView.setImageResource(R.drawable.reverseflash);
                break;


            case "Searock" : headerEventImageView.setImageResource(R.drawable.searock);
                break;


            case "Show Me The Funny" : headerEventImageView.setImageResource(R.drawable.showmethefunny);
                break;


            case "Shutter Island" : headerEventImageView.setImageResource(R.drawable.shutter);
                break;


            case "Silence Of The Amps" : headerEventImageView.setImageResource(R.drawable.silenceoftheamps);
                break;


            case "Sizzle" : headerEventImageView.setImageResource(R.drawable.sizzle);
                break;


            case "Skime" : headerEventImageView.setImageResource(R.drawable.skime);
                break;


            case "Solonote" : headerEventImageView.setImageResource(R.drawable.solonote);
                break;


            case "Entertainment Quiz" : headerEventImageView.setImageResource(R.drawable.entertainment);
                break;


            case "The Lonewolf" : headerEventImageView.setImageResource(R.drawable.lonewolf);
                break;


            case "The Vices Quiz" : headerEventImageView.setImageResource(R.drawable.vicequiz);
                break;


            case "Rubik's Challenge" : headerEventImageView.setImageResource(R.drawable.rubiks);
                break;


            case "Time Lapse" : headerEventImageView.setImageResource(R.drawable.timelapse);
                break;


            case "Wallstreet Fête" : headerEventImageView.setImageResource(R.drawable.wallstreetfete);
                break;


            case "Waves Open Quiz" : headerEventImageView.setImageResource(R.drawable.wavesopen);
                break;


            case "Waves Poetry Slam" : headerEventImageView.setImageResource(R.drawable.poetryslam);
                break;


            case "Word Games" : headerEventImageView.setImageResource(R.drawable.wordgames);
                break;


            case "Mocktalk Show" : headerEventImageView.setImageResource(R.drawable.mocktalk);
                break;

            case "DJ Paroma":headerEventImageView.setImageResource(R.drawable.djparoma);
                break;

            case "Salim-Sulaiman":headerEventImageView.setImageResource(R.drawable.salim);
                break;

            case "TVF":headerEventImageView.setImageResource(R.drawable.tvf);
                break;

            case "aKING":headerEventImageView.setImageResource(R.drawable.aking);
                break;

            case "Lost Stories":headerEventImageView.setImageResource(R.drawable.loststories);
                break;

            default: headerEventImageView.setImageResource(R.drawable.waves );

        }}




}
