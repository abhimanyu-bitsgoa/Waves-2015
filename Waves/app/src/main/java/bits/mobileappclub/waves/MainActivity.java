package bits.mobileappclub.waves;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterViewFlipper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] eventTime={"8:00 pm","6:45 pm","7:10 pm","6:00 pm","8:00 pm","6:30 pm"};
        String[] eventName={"Dhinchak","Searock","TimeLapse","Rangmanch","Waves Open Quiz","Indian Rock"};
        String[] eventStage={"Final","Prelims","Semi-Final","Semi-final","Final","Prelims"};
        int[] imageResourceId={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};

        AdapterViewFlipper liveEventsViewFlipper=(AdapterViewFlipper)(findViewById(R.id.liveEventsViewFlipper));
        liveEventsViewFlipper.setAutoStart(true);
        liveEventsViewFlipper.setFlipInterval(5000);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        liveEventsViewFlipper.setInAnimation(getApplicationContext(), R.anim.slide_in_right);
        liveEventsViewFlipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_left);

        liveEventsViewFlipper.setAdapter(new LiveEventViewAdapter(this,eventName,eventTime,eventStage,imageResourceId));

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
}
